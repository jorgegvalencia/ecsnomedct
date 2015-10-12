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
}
