package model;

import java.util.List;

public class EligibilityCriteria {
	private String utterance;
	private List<Concept> concepts;
	private int criteriaType;

	public EligibilityCriteria(String utterance, List<Concept> concepts, int criteriaType) {
		this.utterance = utterance;
		this.concepts = concepts;
		this.criteriaType = criteriaType;
	}

	public String getUtterance() {
		return utterance;
	}

	public List<Concept> getConcepts() {
		return concepts;
	}

	public int getCriteriaType() {
		return criteriaType;
	}
	
	public void print(){
		System.out.println("EC: "+utterance);
		switch(criteriaType){
		case 1:
			System.out.println("Type: Inclusion");
			break;
		case 2:
			System.out.println("Type: Exclusion");
			break;
		default:
			System.out.println("Type: N/A");
		}
		for(Concept concept: concepts){
			//System.out.println("Noun Phrase: "+phrase);
			System.out.format("%s|%-30s|%40s\t%s\n",
					concept.getCui(),concept.getPreferedName(),"["+concept.semTypesString()+"]","Phrase: "+concept.getPhrase());
		}
		System.out.println("\n");
	}
	
}
