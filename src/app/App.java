package app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nlp.ConceptExtractor;
import nlp.TextProcessor;
import model.ClinicalTrial;
import model.Concept;
import model.EligibilityCriteria;

public class App {

	public static void main(String[] args) {
		long startTime = System.nanoTime();
		//metamap();
		//clusterConcepts();
		clusterConceptsBeta();
		//clusterDependencies();
		//System.out.println("Pathologically confirmed carcinoma of the breast.");
		//System.out.println(TextProcessor.getChunksAsList("Pathologically confirmed carcinoma of the breast."));
		long endTime = System.nanoTime();
		System.out.format("Total: %.2f s",(endTime - startTime)/Math.pow(10, 9));
	}
	
	public static void metamap(){
		// NCT01358877
		// NCT00148876
		// NCT02102490
		// NCT01633060
		// NCT01700257
		CTManager ctm = new CTManager();
		String nctid = "NCT01358877";
		ClinicalTrial ct = ctm.buildClinicalTrial(nctid);
		//ct.print();
		String criteria = ct.getCriteria();
		ConceptExtractor ce = new ConceptExtractor();
		//ctm.showTrialsFiles();
		List<EligibilityCriteria> ecList = ce.getEligibilityCriteriaFromText(criteria);
		for(EligibilityCriteria ec: ecList){
			ec.print();
		}
	}

	public static void clusterConcepts(){
		CTManager ctm = new CTManager();
		ConceptExtractor ce = new ConceptExtractor();
		Map<String,Integer> map = new HashMap<String,Integer>();
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
			if(map.containsKey(concept.semTypesString()))
				map.put(concept.semTypesString(), map.get(concept.semTypesString())+1);
			else
				map.put(concept.semTypesString(), 1);
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
		System.out.format("%55s | %15s | %5s \n","SemType","Appearances","Frecuency");
		for(int i = 0; i < 50 && i < entries.size(); i++){
			double frecuency = ((double)entries.get(entries.size() - i - 1).getValue()/(double)nConcepts);
			System.out.format("%-55s | %-15s | %-5.4f %%\n",
					"["+entries.get(entries.size() - i - 1).getKey()+"]",
					+entries.get(entries.size() - i - 1).getValue(),
					frecuency*100);
		}
		try{
			FileWriter writer = new FileWriter("frecuencies_semtypes.csv");
			writer.append("SemType ; Appearances ; Frecuency \n");
			for(int i = 0; i < entries.size(); i++){
				double frecuency = ((double)entries.get(i).getValue()/nConcepts);
				writer.append(String.format("%s ; %s ; %.4f\n",entries.get(i).getKey(),entries.get(i).getValue().toString(),frecuency));
			}
			writer.flush();
			writer.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void clusterConceptsBeta(){
		CTManager ctm = new CTManager();
		ConceptExtractor ce = new ConceptExtractor();
		Map<String,Integer> map = new HashMap<String,Integer>();
		Map<String,String> semmap = new HashMap<>();
		//Map<String,Integer> patternmap = new HashMap<String,Integer>();
		int nConcepts = 0;
		//int nPatterns = 0;
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
		/*System.out.println("Building map...");
		for(Concept concept: conceptList){
			if(patternmap.containsKey(concept.getPhrase()))
				patternmap.put(concept.getPhrase(), patternmap.get(concept.getPhrase())+1);
			else
				patternmap.put(concept.getPhrase(), 1);
			nPatterns++;
		}
		System.out.println("Sorting map...");
		ArrayList<Map.Entry<String, Integer>> entries2 = new ArrayList<>(patternmap.entrySet());
		Collections.sort(entries2, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
				return a.getValue().compareTo(b.getValue());
			}
		});*/
		
		System.out.println("Total trials: "+j);
		System.out.println("Total concepts: "+nConcepts);
		System.out.println("Total distinct concepts: "+entries.size());
		System.out.println("Top 50:");
		System.out.format("%30s | %15s | %5s | %55s \n","Concept","Appearances","Frecuency","Semantic Type");
		for(int i = 0; i < 50 && i < entries.size(); i++){
			double frecuency = ((double)entries.get(entries.size() - i - 1).getValue()/(double)nConcepts);
			System.out.format("%-30s | %-15s | %-5.4f %%  | %55s \n",
					entries.get(entries.size() - i - 1).getKey(),
					+entries.get(entries.size() - i - 1).getValue(),
					frecuency*100,
					semmap.get(entries.get(entries.size() - i - 1).getKey()));
		}
		
		/*System.out.println("Total distinct patterns:"+entries2.size());
		System.out.format("%30s | %-15s | %-30s\n","Frecuency","Appearances","Phrase");
		for(int i = 0; i < 50 && i<entries2.size(); i++){
			double frecuency = ((double)entries2.get(entries2.size() - i - 1).getValue()/(double)nPatterns);
			System.out.format("%28.4f%% | %-15s | %-30s\n",
					frecuency*100,
					entries2.get(entries2.size() - i - 1).getValue(),
					entries2.get(entries2.size() - i - 1).getKey());
		}*/
		
		try{
			FileWriter writer = new FileWriter("frecuencies.csv");
			writer.append("Concept;Appearances;Frecuency;SemanticType\n");
			for(int i = 0; i < entries.size(); i++){
				double frecuency = ((double)entries.get(i).getValue()/nConcepts);
				writer.append(String.format("%s;%s;%.4f;%s\n",entries.get(i).getKey(),entries.get(i).getValue().toString(),frecuency,semmap.get(entries.get(i).getKey())));
			}
			writer.flush();
			writer.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void clusterDependencies(){
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
	}
}
