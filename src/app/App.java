package app;

import java.util.List;

import nlp.ConceptExtractor;
import model.ClinicalTrial;
import model.EligibilityCriteria;

public class App {

	public static void main(String[] args) {
		// NCT01358877
		// NCT00148876
		// NCT02102490
		// NCT01633060
		// NCT01700257
		CTManager ctm = new CTManager();
		String nctid = "NCT01358877";
		ClinicalTrial ct = ctm.buildClinicalTrial(nctid);
		ct.print();
		String criteria = ct.getCriteria();
		ConceptExtractor ce = new ConceptExtractor();
		List<EligibilityCriteria> ecList = ce.getEligibilityCriteriaFromText(criteria);
		for(EligibilityCriteria ec: ecList){
			ec.print();
		}
	}
}
