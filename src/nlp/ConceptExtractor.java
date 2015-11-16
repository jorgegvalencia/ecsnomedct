package nlp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import coredataset.CoreDatasetServiceClient;
import app.SnomedWebAPIClient;
import db.DBConnector;
import exceptions.ServiceNotAvailable;
import gov.nih.nlm.nls.metamap.AcronymsAbbrevs;
import gov.nih.nlm.nls.metamap.ConceptPair;
import gov.nih.nlm.nls.metamap.Ev;
import gov.nih.nlm.nls.metamap.Mapping;
import gov.nih.nlm.nls.metamap.MetaMapApi;
import gov.nih.nlm.nls.metamap.MetaMapApiImpl;
import gov.nih.nlm.nls.metamap.Negation;
import gov.nih.nlm.nls.metamap.PCM;
import gov.nih.nlm.nls.metamap.Position;
import gov.nih.nlm.nls.metamap.Result;
import gov.nih.nlm.nls.metamap.Utterance;
import model.Concept;
import model.EligibilityCriteria;

public class ConceptExtractor {
	// JDBC driver name and database URL
	//private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static final String METATHESAURUS = "jdbc:mysql://kandel.dia.fi.upm.es/metathesaurus";
	private static final String NORM = "jdbc:mysql://localhost/norm";
	//  Database credentials
	private static final String USER = "umls";
	private static final String PASS = "terminology_service";
	private static final String MMSERVER14 = "C:\\Users\\Jorge\\public_mm\\bin\\mmserver14.bat"; // -UDA C:\\Users\\Jorge\\UDAfile
	private static final String SKRMEDPOSTCTL = "C:\\Users\\Jorge\\public_mm\\bin\\skrmedpostctl_start.bat";
	// Index for status of concepts
	private static HashMap<String,Integer> index = new HashMap<>(); // scui,status
	private static HashSet<String> excluded = new HashSet<String>(); // CUI
	// Runtime
	private static Runtime rt = null;
	private static Process mmserver = null;
	private static Process tagger = null;
	/*
	 * cell - Cell
	 * fish - Fish
	 * ftcn - Functional Concept
	 * idcn - Idea or Concept
	 * inpr - Intellectual Product
	 * menp - Mental Process
	 * mnob - Manufactured Object
	 * podg - Patient or Disabled Group
	 * qlco - Qualitative Concept
	 * qnco - Quantitative Concept
	 * spco - Spatial Concept
	 * tmco - Temporal Concept
	 */
	private static MetaMapApi mmapi;
	private static String options = "-Q 2 -i -k cell,fish,ftcn,idcn,inpr,menp,mnob,podg,qlco,qnco,spco,tmco -R SNOMEDCT_US";
	private SnomedWebAPIClient api;

	public ConceptExtractor() {
		this.api = new SnomedWebAPIClient();
		// Run servers if not initialized
		initServers();
		// Load index of excluded concepts
		String path="resources/excluded_concepts";
		try {
			String line;
			BufferedReader exclude = new BufferedReader(new FileReader(path));
			while((line = exclude.readLine())!= null){
				excluded.add(line);
			}
			exclude.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initServers(){
		if(rt == null){
			rt = Runtime.getRuntime();
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		//if(tagger == null || !tagger.isAlive()){ // || !tagger.isAlive()
		try {
			// Start tagger server first
			tagger = rt.exec(SKRMEDPOSTCTL);
			System.out.print("skrmedpostctl service running: ");
			System.out.print(tagger.isAlive()+"\n"); // Tagger server status
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		//}
		//if(mmserver == null || !mmserver.isAlive()){
		try {
			// Start the metamap server
			mmserver = rt.exec(MMSERVER14);
			System.out.print("mmserver14 service running: ");
			System.out.print(mmserver.isAlive()+"\n"); // Metamap server status
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		//}
		mmapi = new MetaMapApiImpl();
		mmapi.setOptions(options);
	}

	/*	public void endServers(){
		if(tagger != null && tagger.isAlive()){
			tagger.destroyForcibly();
			System.out.println("skrmedpostctl service ended.");
		}
		if(mmserver != null && mmserver.isAlive()){
			mmserver.destroyForcibly();
			System.out.println("mmserver14 service ended.");
		}
	}*/

	/*	public boolean serversUp(){
		boolean tag = tagger.isAlive();
		boolean mm = mmserver.isAlive();
			return tagger != null && mmserver !=null && tag && mm;
	}*/
	/**
	 * @param text
	 * @return Returns a set of EligibilityCriteria objects that contains a list of concepts contained in the elegibility criteria text.
	 */
	public List<EligibilityCriteria> getEligibilityCriteriaFromText(String text){
		List<EligibilityCriteria> ecList = new ArrayList<EligibilityCriteria>();
		int type = 0;
		// Process raw criteria
		String criteria = TextProcessor.ProcessEligibilityCriteria(text);
		// Get the utterances for each EC
		List<String> uttList = getUtterancesFromText(criteria);
		// for each utterance
		for(String utt: uttList){
			if(utt.contains("Inclusion") || utt.contains("inclusion")){
				type = 1;
			}
			else if(utt.contains("Exclusion") || utt.contains("exclusion")){
				type = 2;
			}
			// get the concepts from the utterance
			List<Concept> concepts = getConceptsFromText(utt);
			// !! REVIEW CONCEPTS
			List<Concept> rs = removeRedundancies(concepts);
			// create EligibilityCriteria object
			EligibilityCriteria ec = new EligibilityCriteria(utt, rs, type);
			ecList.add(ec);
		}
		ecList = filterConcepts(ecList);
		return ecList;
	}
	
	public void persistEC(EligibilityCriteria ec){
		DBConnector db = new DBConnector(NORM,USER,PASS);
		//String sql = "INSERT INTO eligibilitycriteria (clinicaltrial_id,type,text) VALUES ("+ec.;
		db.endConnector();
	}

	private List<Result> queryFromString(String text) throws IOException{
		List<Result> resultList = new ArrayList<>();
		try{
			resultList = mmapi.processCitationsFromString(text);
		} catch (Exception e){
			//try {
			System.err.println("Restarting servers...");
			//Thread.sleep(5000);
			initServers();
			//Thread.sleep(5000);
			//} catch (InterruptedException e1) {
			//e1.printStackTrace();
			//}
		}
		return resultList;
	}

	/*private void printAcronymsAbbrevs(Result result) {
		List<AcronymsAbbrevs> aaList;
		try {
			aaList = result.getAcronymsAbbrevs();
			if (aaList.size() > 0) {
				System.out.println("Acronyms and Abbreviations:");
				for (AcronymsAbbrevs e: aaList) {
					System.out.println("Acronym: " + e.getAcronym());
					System.out.println("Expansion: " + e.getExpansion());
					System.out.println("Count list: " + e.getCountList());
					System.out.println("CUI list: " + e.getCUIList());
				}
			} else {
				System.out.println(" None.");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void printNegations(Result result) {
		List<Negation> negList;
		try {
			negList = result.getNegations();
			if (negList.size() > 0) {
				System.out.println("Negations:");
				for (Negation e: negList) {
					System.out.println("type: " + e.getType());
					System.out.print("Trigger: " + e.getTrigger() + ": [");
					for (Position pos: e.getTriggerPositionList()) {
						System.out.print(pos  + ",");
					}
					System.out.println("]");
					System.out.print("ConceptPairs: [");
					for (ConceptPair pair: e.getConceptPairList()) {
						System.out.print(pair + ",");
					}
					System.out.println("]");
					System.out.print("ConceptPositionList: [");
					for (Position pos: e.getConceptPositionList()) {
						System.out.print(pos + ",");
					}
					System.out.println("]");
				}
			} else {
				System.out.println(" None.");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void printUtterances(Result result) {
		try {
			for (Utterance utterance: result.getUtteranceList()) {
				System.out.println("Utterance:");
				System.out.println(" Id: " + utterance.getId());
				System.out.println(" Utterance text: " + utterance.getString());
				System.out.println(" Position: " + utterance.getPosition());
				for (PCM pcm: utterance.getPCMList()) {
					System.out.println("Phrase:");
					System.out.println(" text: " + pcm.getPhrase().getPhraseText());
					System.out.println(" Minimal Commitment Parse: " + pcm.getPhrase().getMincoManAsString());
					System.out.println("Candidates:");
					for (Ev ev: pcm.getCandidateList()) {
						System.out.println(" Candidate:");
						System.out.println("  Score: " + ev.getScore());
						System.out.println("  Concept Id: " + ev.getConceptId());
						System.out.println("  Concept Name: " + ev.getConceptName());
						System.out.println("  Preferred Name: " + ev.getPreferredName());
						System.out.println("  Matched Words: " + ev.getMatchedWords());
						System.out.println("  Semantic Types: " + ev.getSemanticTypes());
						System.out.println("  MatchMap: " + ev.getMatchMap());
						System.out.println("  MatchMap alt. repr.: " + ev.getMatchMapList());
						System.out.println("  is Head?: " + ev.isHead());
						System.out.println("  is Overmatch?: " + ev.isOvermatch());
						System.out.println("  Sources: " + ev.getSources());
						System.out.println("  Positional Info: " + ev.getPositionalInfo());
					}
					System.out.println("Mappings:");
					for (Mapping map: pcm.getMappingList()) {
						System.out.println(" Map Score: " + map.getScore());
						for (Ev mapEv: map.getEvList()) {
							System.out.println("   Score: " + mapEv.getScore());
							System.out.println("   Concept Id: " + mapEv.getConceptId());
							System.out.println("   Concept Name: " + mapEv.getConceptName());
							System.out.println("   Preferred Name: " + mapEv.getPreferredName());
							System.out.println("   Matched Words: " + mapEv.getMatchedWords());
							System.out.println("   Semantic Types: " + mapEv.getSemanticTypes());
							System.out.println("   MatchMap: " + mapEv.getMatchMap());
							System.out.println("   MatchMap alt. repr.: " + mapEv.getMatchMapList());
							System.out.println("   is Head?: " + mapEv.isHead());
							System.out.println("   is Overmatch?: " + mapEv.isOvermatch());
							System.out.println("   Sources: " + mapEv.getSources());
							System.out.println("   Positional Info: " + mapEv.getPositionalInfo());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/



	/*	public void endServers(){
			if(tagger != null && tagger.isAlive()){
				tagger.destroyForcibly();
				System.out.println("skrmedpostctl service ended.");
			}
			if(mmserver != null && mmserver.isAlive()){
				mmserver.destroyForcibly();
				System.out.println("mmserver14 service ended.");
			}
		}*/

	/*	public boolean serversUp(){
			boolean tag = tagger.isAlive();
			boolean mm = mmserver.isAlive();
				return tagger != null && mmserver !=null && tag && mm;
		}*/

	public List<String> getUtterancesFromText(String text){
		// do needed process
		List<String> utterances = TextProcessor.getSentencesFromText(text);
		return utterances;
	}

	// Use Metamap parser to get the noun phrases of the sentence
	private List<String> getNounPhrasesFromText(String text){
		List<String> np = new ArrayList<String>();
		try{
			List<Result> result = queryFromString(text);
			for(Result res: result){
				for(Utterance uttr: res.getUtteranceList()){
					for(PCM pcm: uttr.getPCMList()){
						String nounphrase = pcm.getPhrase().getPhraseText();
						np.add(nounphrase);
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return np;
	}

	private List<Concept> getConceptsFromText(String text){
		List<Concept> concepts = new ArrayList<Concept>();
		Pattern p = Pattern.compile("\\([a-z\\s/]+\\)\\z");
		try{
			//CoreDatasetServiceClient normalizer = new CoreDatasetServiceClient();
			List<String> np = getNounPhrasesFromText(text);
			for(String nounp: np){
				// !!! FILTER: search for patterns to manually map frequent concepts
				concepts.addAll(getPatternConcepts(nounp));
				// !!! PROCESS NOUN PHRASE BEFORE CALLING METAMAP
				List<Result> result = queryFromString(TextProcessor.removeStopWords(nounp).toLowerCase());
				for(Result res: result){
					for(Utterance uttr: res.getUtteranceList()){
						for (PCM pcm: uttr.getPCMList()){
							if(!pcm.getMappingList().isEmpty()){
								// only best mapping
								Mapping map = pcm.getMappingList().get(0);
								for (Ev mapEv: map.getEvList()){
									// !!! FILTER: if concept fulfill some rules, ignore it
									String sctid = "-";
									String fsn;
									List<String> scuil = getSCUI(mapEv.getConceptId()); // getProperSCUI
									if(!scuil.isEmpty()){
										sctid=scuil.get(0);
										//fsn = normalizer.getFSN(sctid);
									}
									if((fsn = api.getFSN(sctid)) == null)
										continue;
									//fsn = mapEv.getPreferredName();
									Concept concept = new Concept(mapEv.getConceptId(),
											sctid,
											mapEv.getConceptName(),
											fsn,
											nounp,
											mapEv.getSemanticTypes());
									Matcher m = p.matcher(fsn);
									String hierarchy;
									if(m.find()){
										hierarchy = m.group(0).replaceAll("\\p{Punct}", "");
										concept.setHierarchy(hierarchy);
									}	
									concepts.add(concept);
								}
							}	
						}
					}
				}
			}
			// !!! REVIEW CONCEPTS
			//rs = removeRedundancies(concepts);
		}
		catch (ServiceNotAvailable e){
			System.exit(1);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return concepts;
	}

	private List<Concept> getPatternConcepts(String nounp) {
		List<String> tokens = TextProcessor.getTokensAsList(nounp);
		List<Concept> result = new ArrayList<Concept>();
		Concept c;
		if(nounp.toLowerCase().contains("eastern cooperative oncology group")){
			c = new Concept("C1520224",
					"423740007",
					"ECOG performance status",
					"Eastern Cooperative Oncology Group performance status (observable entity)",
					nounp, new ArrayList<String>(Arrays.asList("clna")));
			c.setHierarchy("observable entity");
			result.add(c);
		}
		else if(nounp.toLowerCase().contains("informed consent")){
			c = new Concept("C0567423",
					"182771004",
					"Informed consent for procedure",
					"Informed consent for procedure (procedure)",
					nounp, new ArrayList<String>(Arrays.asList("topp")));
			c.setHierarchy("procedure");
			result.add(c);
		}
		else if(nounp.toLowerCase().contains("breast feeding")){
			c = new Concept("C1623040",
					"69840006",
					"Normal breast feeding",
					"Normal breast feeding (finding)",
					nounp, new ArrayList<String>(Arrays.asList("fndg")));
			c.setHierarchy("finding");
			result.add(c);
		}
		else if(nounp.toLowerCase().contains("contraception") 
				|| nounp.toLowerCase().contains("contraception care")
				|| nounp.toLowerCase().contains("avoid pregnancy")){
			c = new Concept("C1171186",
					"389095005",
					"Contraception care",
					"Contraception care (regime/therapy)",
					nounp, new ArrayList<String>(Arrays.asList("hlca")));
			c.setHierarchy("regime/therapy");
			result.add(c);
		}
		//nounp.toLowerCase().contains(" age ") || nounp.toLowerCase().contains(" age.")
		else if(tokens.contains("age")){
			c = new Concept("C0001779",
					"424144002",
					"Age",
					"Current chronological age (observable entity)",
					nounp, new ArrayList<String>(Arrays.asList("orga")));
			c.setHierarchy("observable entity");
			result.add(c);
		}
		return result;
	}

	private List<Concept> removeRedundancies(List<Concept> concepts){
		List<Concept> result = new ArrayList<>();
		Map<String,Concept> index = new HashMap<String,Concept>();
		for(Concept c: concepts){
			if(!index.containsKey(c.getCui())){
				index.put(c.getCui(), c);
			}
			else{
				String ph1 = c.getPhrase();
				String ph2 = index.get(c.getCui()).getPhrase();
				if(ph1.equals(ph2))
					continue;
				else
					index.get(c.getCui()).setPhrase(ph1 + " + " + ph2);
			}
		}
		result.addAll(index.values());
		return result;
	}

	private List<EligibilityCriteria> filterConcepts(List<EligibilityCriteria> list){
		List<EligibilityCriteria> filteredList = new ArrayList<>(list);
		for(EligibilityCriteria ec: filteredList){
			Iterator<Concept> it =  ec.getConcepts().iterator();
			while(it.hasNext()){
				Concept c = it.next();
				if(excluded.contains(c.getCui())){
					it.remove();
				}
			}
		}
		return filteredList;
	}

	private List<String> getSCUI(String id){
		DBConnector db = new DBConnector(METATHESAURUS, USER, PASS);
		List<String> idlist = new ArrayList<String>();
		String sql = "SELECT SCUI FROM metathesaurus.mrconso WHERE CUI='"+id+"' AND ISPREF='Y' AND SAB='SNOMEDCT_US' GROUP BY SCUI";
		try {
			ResultSet rs = db.performQuery(sql);
			if(rs!=null){
				while(rs.next()){
					String scui = rs.getString("SCUI");
					if(!index.containsKey(scui)){
						// API call to check actual status of the concept
						index.put(scui, api.getStatusFromDB(scui));
					}
					if(index.get(scui)==1) // If it is active, add it to the result list
						idlist.add(rs.getString("SCUI"));
				}
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.endConnector();
		return idlist;
	}

	private List<String> getProperSCUI(String cui){
		CoreDatasetServiceClient normalizer;
		List<String> idlist = getSCUI(cui);
		try {
			normalizer = new CoreDatasetServiceClient();
			if(idlist.size()>2){
				List<String> aux = new CopyOnWriteArrayList<>(idlist);
				idlist.clear();
				Iterator<String> it = aux.iterator();
				while(it.hasNext()){
					String id = it.next();
					if(normalizer.getRootConcept(id).equals("Unknown"))
						aux.remove(id);
				}
				if(idlist.size()>2){
					idlist.addAll(normalizer.getBestMatches(aux));
				}
				else
					idlist.addAll(aux);
			}
		} catch (ServiceNotAvailable e) {
			e.printStackTrace();
			System.exit(1);
		}
		return idlist;
	}
}
