package app;

import model.ClinicalTrial;

public class App {

	public static void main(String[] args) {
		CTManager ctm = new CTManager();
		String nctid = "NCT00579214";
		ClinicalTrial ct = ctm.buildClinicalTrial(nctid);
	}

}
