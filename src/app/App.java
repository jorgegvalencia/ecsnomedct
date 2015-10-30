package app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import coredataset.CoreDatasetServiceClient;
import exceptions.ServiceNotAvailable;
import nlp.ConceptExtractor;
import model.ClinicalTrial;
import model.Concept;
import model.EligibilityCriteria;

public class App {
	private static final String[] TRIALS = {"NCT01358877","NCT00148876","NCT02102490","NCT01633060","NCT01700257"};
	
	public static void main(String[] args) {
		long startTime = System.nanoTime();
			//normalizationTest();
			metamapTest();
			//statusTest();
			//clusterConcepts();
		long endTime = System.nanoTime();
		System.out.format("Total: %.2f s",(endTime - startTime)/Math.pow(10, 9));
	}
	
	// Test de la API SNOMEDCT https://rxnav.nlm.nih.gov/SnomedCTAPI.html
	public static void statusTest(){
		SnomedWebAPIClient api = new SnomedWebAPIClient();
		System.out.println(api.getStatus("154432008"));
	}

	// Test del servicio de normalizaci�n CoreDataset de kandel.dia.fi.upm.es
	public static void normalizationTest(){
		String sctid = "154432008";
		CoreDatasetServiceClient normalize;
		try {
			normalize = new CoreDatasetServiceClient();
			System.out.println(normalize.getNormalFormAsString(sctid,true));
		} catch (ServiceNotAvailable e) {
			System.exit(1);
		}
	}

	// Test del procesamiento de un ensayo cl�nico con la API metamap + normalizaci�n 
	public static void metamapTest(){
		try {
			String nctid = TRIALS[0];
			CTManager ctm = new CTManager();
			ConceptExtractor ce = new ConceptExtractor();
			CoreDatasetServiceClient normalizer = new CoreDatasetServiceClient();
			ClinicalTrial ct = ctm.buildClinicalTrial(nctid);
			String criteria = ct.getCriteria();
			List<EligibilityCriteria> ecList = ce.getEligibilityCriteriaFromText(criteria);
			for(EligibilityCriteria ec: ecList){
				if(!ec.getConcepts().isEmpty()){
					for(Concept c: ec.getConcepts()){
						c.setNormalForm(normalizer.getNormalFormAsString(c.getSctid(),true));
						c.print2();
					}
				}
			}
		} catch (ServiceNotAvailable e) {
			System.exit(1);
		}
	}

	// M�todo que crea un CSV con los conceptos de un conjunto de ensayos cl�nicos
	public static void clusterConcepts(){
		CTManager ctm = new CTManager();
		ConceptExtractor ce = new ConceptExtractor();
		Map<String,Integer> map = new HashMap<String,Integer>();
		Map<String,String> semmap = new HashMap<>();
		Map<String,Tuple<String,String>> codesmap = new HashMap<>();
		int nConcepts = 0;
		List<Concept> conceptList = new ArrayList<Concept>();
		String path="resources/trials/";
		File[] files = new File(path).listFiles();
		int j=0;
		for(File f: files){
			if(f.getName().contains("NCT")){
				j++;
				long startTime = System.nanoTime();
				ClinicalTrial ct = ctm.buildClinicalTrial(f.getName().replace(".xml", ""));
				String criteria = ct.getCriteria();
				List<EligibilityCriteria> ecList = ce.getEligibilityCriteriaFromText(criteria);
				for(EligibilityCriteria ec: ecList){
					conceptList.addAll(ec.getConcepts());
				}
				long endTime = System.nanoTime();
				System.out.print("["+j+"] ");
				System.out.format("%.2f s\n",(endTime - startTime)/Math.pow(10, 9));
			}
		}
		System.out.println("Building map...");
		for(Concept concept: conceptList){
			if(map.containsKey(concept.getName()))
				map.put(concept.getName(), map.get(concept.getName())+1);
			else
				map.put(concept.getName(), 1);
			semmap.put(concept.getName(),concept.semTypesString());
			codesmap.put(concept.getName(), new Tuple<String,String>(concept.getCui(),concept.getSctid()));
			nConcepts++;
		}
		System.out.println("Sorting map...");
		ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
		Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
				return a.getValue().compareTo(b.getValue());
			}
		});
		System.out.println("Total trials: "+j);
		System.out.println("Total concepts: "+nConcepts);
		System.out.println("Total distinct concepts: "+entries.size());
		System.out.println("Top 50:");
		System.out.format("%15s | %15s | %30s | %15s | %5s | %55s \n","CUI","SCTID","Concept","Appearances","Frecuency","Semantic Type");
		for(int i = 0; i < 50 && i < entries.size(); i++){
			double frecuency = ((double)entries.get(entries.size() - i - 1).getValue()/(double)nConcepts);
			System.out.format("%15s | %15s | %-30s | %-15s | %-5.4f %%  | %55s \n",
					codesmap.get(entries.get(entries.size() - i - 1).getKey()).item1,
					codesmap.get(entries.get(entries.size() - i - 1).getKey()).item2,
					entries.get(entries.size() - i - 1).getKey(),
					+entries.get(entries.size() - i - 1).getValue(),
					frecuency*100,
					semmap.get(entries.get(entries.size() - i - 1).getKey()));
		}
		try{
			FileWriter writer = new FileWriter("frecuencies.csv");
			writer.append("CUI;SCTID;Concept;Appearances;Frecuency;SemanticType\n");
			for(int i = 0; i < entries.size(); i++){
				double frecuency = ((double)entries.get(i).getValue()/nConcepts);
				writer.append(String.format("%s;%s;%s;%s;%.4f;%s\n",
						codesmap.get(entries.get(entries.size() - i - 1).getKey()).item1,
						codesmap.get(entries.get(entries.size() - i - 1).getKey()).item2,
						entries.get(i).getKey(),
						entries.get(i).getValue().toString(),
						frecuency,
						semmap.get(entries.get(i).getKey())));
			}
			writer.flush();
			writer.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	/*public static void clusterDependencies(){
		CTManager ctm = new CTManager();
		Map<String,Integer> patternmap = new HashMap<String,Integer>();
		int nPatterns = 0;
		String path="resources/trials/";
		File[] files = new File(path).listFiles();
		List<File> flist = Arrays.asList(files);
		int j=1;
		for(File f: flist){
			if(f.getName().contains("NCT")){
				long startTime = System.nanoTime();
				ClinicalTrial ct = ctm.buildClinicalTrial(f.getName().replace(".xml", ""));
				ConceptExtractor ce = new ConceptExtractor();
				String criteria = ct.getCriteria();
				List<String> utterances = ce.getUtterancesFromText(criteria);
				for(String utt: utterances){
					for(String dependency: TextProcessor.getDependencies(utt)){
						if(patternmap.containsKey(dependency))
							patternmap.put(dependency, patternmap.get(dependency)+1);
						else
							patternmap.put(dependency, 1);
						nPatterns++;
					}
				}
				long endTime = System.nanoTime();
				System.out.print("["+j+"] ");
				System.out.format("%.2f s\n",(endTime - startTime)/Math.pow(10, 9));
				j++;
			}
		}
		System.out.println("Sorting map...");
		ArrayList<Map.Entry<String, Integer>> entries2 = new ArrayList<>(patternmap.entrySet());
		Collections.sort(entries2, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
				return a.getValue().compareTo(b.getValue());
			}
		});
		System.out.println("Total trials: "+j);
		System.out.println("Top 50:");
		System.out.format("%30s | %15s | %5s \n","Dependency","Appearances","Frecuency");
		for(int i = 0; i < 50 && i < entries2.size(); i++){
			double frecuency = ((double)entries2.get(entries2.size() - i - 1).getValue()/(double)nPatterns);
			System.out.format("%-30s | %-15s | %-5.4f %%\n",
					entries2.get(entries2.size() - i - 1).getKey(),
					+entries2.get(entries2.size() - i - 1).getValue(),
					frecuency*100);
		}
	}*/
}
