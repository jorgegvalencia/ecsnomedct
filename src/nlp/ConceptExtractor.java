package nlp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBAvailabilityException;
import db.DBConnector;
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
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://kandel.dia.fi.upm.es/metathesaurus";
	//  Database credentials
	static final String USER = "umls";
	static final String PASS = "terminology_service";
	private MetaMapApi mmapi;
	private String options;
	private DBConnector db;

	public ConceptExtractor() {
		this.mmapi = new MetaMapApiImpl();
		this.options = "-Q 2 -k cell,fish,ftcn,idcn,inpr,menp,mnob,podg,qlco,qnco,spco,tmco -R SNOMEDCT_US";
		mmapi.setOptions(options);
	}
	
	public List<String> getUtterancesFromText(String text){
		// do needed process
		List<String> utterances = TextProcessor.getSentencesFromText(text);
		return utterances;
	}

	public List<EligibilityCriteria> getEligibilityCriteriaFromText(String text){
		List<EligibilityCriteria> ecList = new ArrayList<EligibilityCriteria>();
		try {
			db = new DBConnector(DB_URL, USER, PASS);
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
				// create EligibilityCriteria object
				EligibilityCriteria ec = new EligibilityCriteria(utt, concepts, type);
				ecList.add(ec);
			}
			db.endConnector();
		} catch (DBAvailabilityException e) {
			System.err.println("Metathesaurus databse is not available.");
		}
		return ecList;
	}
	
	public List<EligibilityCriteria> getEligibilityCriteriaFromTextBeta(String text){
		List<EligibilityCriteria> ecList = new ArrayList<EligibilityCriteria>();
		try {
			db = new DBConnector(DB_URL, USER, PASS);
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
				List<Concept> concepts = getConceptsFromTextBeta(utt);
				// create EligibilityCriteria object
				EligibilityCriteria ec = new EligibilityCriteria(utt, concepts, type);
				ecList.add(ec);
			}
			db.endConnector();
		} catch (DBAvailabilityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ecList;
	}
	
	public List<EligibilityCriteria> filterConcepts(List<EligibilityCriteria> list){
		List<EligibilityCriteria> filteredList = new ArrayList<>(list);
		return filteredList;
	}

	public void printAcronymsAbbrevs(Result result) {
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

	public void printNegations(Result result) {
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

	public void printUtterances(Result result) {
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
	}
	
	private List<Result> queryFromString(String text) {
		List<Result> resultList = mmapi.processCitationsFromString(text);
		return resultList;
	}

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
		try{
			List<String> np = getNounPhrasesFromText(text);
			for(String nounp: np){
				// !!! PROCESS NOUN PHRASE BEFORE CALLING METAMAP
				List<Result> result = queryFromString(TextProcessor.removeSW(nounp).toLowerCase());
				for(Result res: result){
					for(Utterance uttr: res.getUtteranceList()){
						for (PCM pcm: uttr.getPCMList()){
							if(!pcm.getMappingList().isEmpty()){
								// only best mapping
								Mapping map = pcm.getMappingList().get(0);
								for (Ev mapEv: map.getEvList()){
									Concept concept = new Concept(mapEv.getConceptId(),
											getSCTId(mapEv.getConceptId()),
											mapEv.getConceptName(),
											mapEv.getPreferredName(),
											nounp /*pcm.getPhrase().getPhraseText()*/,
											mapEv.getSemanticTypes());
									concepts.add(concept);
								}
							}	
						}
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return concepts;
	}
	
	private List<Concept> getConceptsFromTextBeta(String text){
		List<Concept> concepts = new ArrayList<Concept>();
		try{
				// !!! PROCESS NOUN PHRASE BEFORE CALLING METAMAP
				List<Result> result = queryFromString(text);
				for(Result res: result){
					for(Utterance uttr: res.getUtteranceList()){
						for (PCM pcm: uttr.getPCMList()) {
							for (Mapping map: pcm.getMappingList()) {
								for (Ev mapEv: map.getEvList()) {
									Concept concept = new Concept(mapEv.getConceptId(),
											getSCTId(mapEv.getConceptId()),
											mapEv.getConceptName(),
											mapEv.getPreferredName(),
											TextProcessor.getPOSTagsAsString(pcm.getPhrase().getPhraseText()).replaceAll("(\\w|-|\\,|\\.|\\d|\\^)*/(?=.)", "")/*pcm.getPhrase().getPhraseText()*/,
											mapEv.getSemanticTypes());
									concepts.add(concept);
								}
							}
						}
					}
				}

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return concepts;
	}

	

	private String getSCTId(String id){
		String sql = "SELECT SCUI FROM metathesaurus.mrconso WHERE CUI='"+id+"' AND ISPREF='Y' AND SAB='SNOMEDCT_US'";
		String sctid = "-";
		if(db==null)
			return sctid;
		try {
			ResultSet rs = db.performQuery(sql);
			if(rs!=null){
				while(rs.next()){
					sctid = rs.getString("SCUI");
				}
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sctid;
	}
}
