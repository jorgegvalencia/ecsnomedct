package app;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nlp.ConceptExtractor;
import model.ClinicalTrial;
import model.Concept;
import model.EligibilityCriteria;

public class App {

	public static void main(String[] args) {
		// NCT01358877
		// NCT00148876
		// NCT02102490
		// NCT01633060
		// NCT01700257
		/*
		CTManager ctm = new CTManager();
		String nctid = "NCT02102490";
		ClinicalTrial ct = ctm.buildClinicalTrial(nctid);
		ct.print();
		String criteria = ct.getCriteria();
		ConceptExtractor ce = new ConceptExtractor();
		ctm.showTrialsFiles();
		List<EligibilityCriteria> ecList = ce.getEligibilityCriteriaFromText(criteria);
		for(EligibilityCriteria ec: ecList){
			ec.print();
		}*/
		clusterConcepts();
	}
	
	public static void clusterConcepts(){
		CTManager ctm = new CTManager();
		Map<String,Integer> map = new HashMap<String,Integer>();
		List<ClinicalTrial> trials = ctm.buildTrialsSet();
		int nConcepts = 0;
		List<Concept> conceptList = new ArrayList<Concept>();
		for(ClinicalTrial ct: trials){
			String criteria = ct.getCriteria();
			ConceptExtractor ce = new ConceptExtractor();
			List<EligibilityCriteria> ecList = ce.getEligibilityCriteriaFromText(criteria);
			for(EligibilityCriteria ec: ecList){
				 conceptList.addAll(ec.getConcepts());
				/*for(Concept concept: ec.getConcepts()){
					if(map.containsKey(concept.getName()))
						map.put(concept.getName(), map.get(concept.getName())+1);
					else
						map.put(concept.getName(), 0);
					nConcepts++;
				}*/
			}
		}
		System.out.println("Building map...");
		for(Concept concept: conceptList){
			if(map.containsKey(concept.getName()))
				map.put(concept.getName(), map.get(concept.getName())+1);
			else
				map.put(concept.getName(), 0);
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
	    System.out.println("Total concepts: "+nConcepts);
	    System.out.println("Total distinct concepts: "+entries.size());
	    System.out.println("Top 10:");
	    System.out.format("%30s | %15s | %5s \n","Concept","Appearances","Frecuency");
	    for(int i = 0; i < 10; i++){
	    	double frecuency = ((double)entries.get(entries.size() - i - 1).getValue()/(double)nConcepts);
	    	System.out.format("%-30s | %-15s | %-5.4f \\%\n",
	    			entries.get(entries.size() - i - 1).getKey(),
	    			+entries.get(entries.size() - i - 1).getValue(),
	    			frecuency*100);
	    }
	    try{
			FileWriter writer = new FileWriter("frecuencies.csv");
			writer.append("Concept ; Appearances ; Frecuency \n");
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
}
