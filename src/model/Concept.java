package model;

import java.util.List;

public class Concept {
	private String cui;
	private String sctid;
	private String name;
	private String preferedName;
	private String phrase;
	private List<String> semtypes;	
	private String normalForm;

	public Concept(String cui, String sctid, String name, String preferedName, String phrase, List<String> semtypes) {
		this.cui = cui;
		this.sctid = sctid;
		this.name = name;
		this.preferedName = preferedName;
		this.phrase = phrase;
		this.semtypes = semtypes;
	}

	public String getCui() {
		return cui;
	}

	public String getSctid() {
		return sctid;
	}

	public String getName() {
		return name;
	}

	public String getPreferedName() {
		return preferedName;
	}

	public String getPhrase() {
		return phrase;
	}

	public List<String> getSemtypes() {
		return semtypes;
	}

	public String semTypesString(){
		String r = "";
		for(String s: semtypes){
			if(semtypes.size()>0 && semtypes.get(semtypes.size()-1).equals(s))
				r=r+resolveST(s);
			else
				r=r+resolveST(s)+",";
		}
		return r;
	}
	
	public String getNormalForm() {
		return normalForm;
	}

	public void setNormalForm(String normalForm) {
		this.normalForm = normalForm;
	}

	private String resolveST(String st){
		String result="";
		switch(st){
		case "acab": 
			result="Acquired Abnormality";
			break;
		case "acty": 
			result="Activity";
			break;
		case "aggp": 
			result="Age Group";
			break;
		case "alga": 
			result="Alga";
			break;
		case "amas": 
			result="Amino Acid Sequence";
			break;
		case "aapp": 
			result="Amino Acid, Peptide, or Protein";
			break;
		case "amph": 
			result="Amphibian";
			break;
		case "anab": 
			result="Anatomical Abnormality";
			break;
		case "anst": 
			result="Anatomical Structure";
			break;
		case "anim": 
			result="Animal";
			break;
		case "antb": 
			result="Antibiotic";
			break;
		case "arch": 
			result="Archaeon";
			break;
		case "bact": 
			result="Bacterium";
			break;
		case "bhvr": 
			result="Behavior";
			break;
		case "biof": 
			result="Biologic Function";
			break;
		case "bacs": 
			result="Biologically Active Substance";
			break;
		case "bmod": 
			result="Biomedical Occupation or Discipline";
			break;
		case "bodm": 
			result="Biomedical or Dental Material";
			break;
		case "bird": 
			result="Bird";
			break;
		case "blor": 
			result="Body Location or Region";
			break;
		case "bpoc": 
			result="Body Part, Organ, or Organ Component";
			break;
		case "bsoj": 
			result="Body Space or Junction";
			break;
		case "bdsu": 
			result="Body Substance";
			break;
		case "bdsy": 
			result="Body System";
			break;
		case "carb": 
			result="Carbohydrate";
			break;
		case "crbs": 
			result="Carbohydrate Sequence";
			break;
		case "cell": 
			result="Cell";
			break;
		case "celc": 
			result="Cell Component";
			break;
		case "celf": 
			result="Cell Function";
			break;
		case "comd": 
			result="Cell or Molecular Dysfunction";
			break;
		case "chem": 
			result="Chemical";
			break;
		case "chvf": 
			result="Chemical Viewed Functionally";
			break;
		case "chvs": 
			result="Chemical Viewed Structurally";
			break;
		case "clas": 
			result="Classification";
			break;
		case "clna": 
			result="Clinical Attribute";
			break;
		case "clnd": 
			result="Clinical Drug";
			break;
		case "cnce": 
			result="Conceptual Entity";
			break;
		case "cgab": 
			result="Congenital Abnormality";
			break;
		case "dora": 
			result="Daily or Recreational Activity";
			break;
		case "diap": 
			result="Diagnostic Procedure";
			break;
		case "dsyn": 
			result="Disease or Syndrome";
			break;
		case "drdd": 
			result="Drug Delivery Device";
			break;
		case "edac": 
			result="Educational Activity";
			break;
		case "eico": 
			result="Eicosanoid";
			break;
		case "elii": 
			result="Element, Ion, or Isotope";
			break;
		case "emst": 
			result="Embryonic Structure";
			break;
		case "enty": 
			result="Entity";
			break;
		case "eehu": 
			result="Environmental Effect of Humans";
			break;
		case "enzy": 
			result="Enzyme";
			break;
		case "evnt": 
			result="Event";
			break;
		case "emod": 
			result="Experimental Model of Disease";
			break;
		case "famg": 
			result="Family Group";
			break;
		case "fndg": 
			result="Finding";
			break;
		case "fish": 
			result="Fish";
			break;
		case "food": 
			result="Food";
			break;
		case "ffas": 
			result="Fully Formed Anatomical Structure";
			break;
		case "ftcn": 
			result="Functional Concept";
			break;
		case "fngs": 
			result="Fungus";
			break;
		case "gngp": 
			result="Gene or Gene Product (pseudo ST for gene terminology)";
			break;
		case "gngm": 
			result="Gene or Genome";
			break;
		case "genf": 
			result="Genetic Function";
			break;
		case "geoa": 
			result="Geographic Area";
			break;
		case "gora": 
			result="Governmental or Regulatory Activity";
			break;
		case "grup": 
			result="Group";
			break;
		case "grpa": 
			result="Group Attribute";
			break;
		case "hops": 
			result="Hazardous or Poisonous Substance";
			break;
		case "hlca": 
			result="Health Care Activity";
			break;
		case "hcro": 
			result="Health Care Related Organization";
			break;
		case "horm": 
			result="Hormone";
			break;
		case "humn": 
			result="Human";
			break;
		case "hcpp": 
			result="Human-caused Phenomenon or Process";
			break;
		case "idcn": 
			result="Idea or Concept";
			break;
		case "imft": 
			result="Immunologic Factor";
			break;
		case "irda": 
			result="Indicator, Reagent, or Diagnostic Aid";
			break;
		case "inbe": 
			result="Individual Behavior";
			break;
		case "inpo": 
			result="Injury or Poisoning";
			break;
		case "inch": 
			result="Inorganic Chemical";
			break;
		case "inpr": 
			result="Intellectual Product";
			break;
		case "invt": 
			result="Invertebrate";
			break;
		case "lbpr": 
			result="Laboratory Procedure";
			break;
		case "lbtr": 
			result="Laboratory or Test Result";
			break;
		case "lang": 
			result="Language";
			break;
		case "lipd": 
			result="Lipid";
			break;
		case "mcha": 
			result="Machine Activity";
			break;
		case "mamm": 
			result="Mammal";
			break;
		case "mnob": 
			result="Manufactured Object";
			break;
		case "medd": 
			result="Medical Device";
			break;
		case "menp": 
			result="Mental Process";
			break;
		case "mobd": 
			result="Mental or Behavioral Dysfunction";
			break;
		case "mbrt": 
			result="Molecular Biology Research Technique";
			break;
		case "moft": 
			result="Molecular Function";
			break;
		case "mosq": 
			result="Molecular Sequence";
			break;
		case "npop": 
			result="Natural Phenomenon or Process";
			break;
		case "neop": 
			result="Neoplastic Process";
			break;
		case "nsba": 
			result="Neuroreactive Substance or Biogenic Amine";
			break;
		case "nnon": 
			result="Nucleic Acid, Nucleoside, or Nucleotide";
			break;
		case "nusq": 
			result="Nucleotide Sequence";
			break;
		case "ocdi": 
			result="Occupation or Discipline";
			break;
		case "ocac": 
			result="Occupational Activity";
			break;
		case "ortf": 
			result="Organ or Tissue Function";
			break;
		case "orch": 
			result="Organic Chemical";
			break;
		case "orgm": 
			result="Organism";
			break;
		case "orga": 
			result="Organism Attribute";
			break;
		case "orgf": 
			result="Organism Function";
			break;
		case "orgt": 
			result="Organization";
			break;
		case "opco": 
			result="Organophosphorus Compound";
			break;
		case "patf": 
			result="Pathologic Function";
			break;
		case "podg": 
			result="Patient or Disabled Group";
			break;
		case "phsu": 
			result="Pharmacologic Substance";
			break;
		case "phpr": 
			result="Phenomenon or Process";
			break;
		case "phob": 
			result="Physical Object";
			break;
		case "phsf": 
			result="Physiologic Function";
			break;
		case "plnt": 
			result="Plant";
			break;
		case "popg": 
			result="Population Group";
			break;
		case "pros": 
			result="Professional Society";
			break;
		case "prog": 
			result="Professional or Occupational Group";
			break;
		case "qlco": 
			result="Qualitative Concept";
			break;
		case "qnco": 
			result="Quantitative Concept";
			break;
		case "rcpt": 
			result="Receptor";
			break;
		case "rnlw": 
			result="Regulation or Law";
			break;
		case "rept": 
			result="Reptile";
			break;
		case "resa": 
			result="Research Activity";
			break;
		case "resd": 
			result="Research Device";
			break;
		case "rich": 
			result="Rickettsia or Chlamydia";
			break;
		case "shro": 
			result="Self-help or Relief Organization";
			break;
		case "sosy": 
			result="Sign or Symptom";
			break;
		case "socb": 
			result="Social Behavior";
			break;
		case "spco": 
			result="Spatial Concept";
			break;
		case "strd": 
			result="Steroid";
			break;
		case "sbst": 
			result="Substance";
			break;
		case "tmco": 
			result="Temporal Concept";
			break;
		case "topp": 
			result="Therapeutic or Preventive Procedure";
			break;
		case "tisu": 
			result="Tissue";
			break;
		case "vtbt": 
			result="Vertebrate";
			break;
		case "virs": 
			result="Virus";
			break;
		case "vita": 
			result="Vitamin";
			break;
		default:
			;
		}
		return result;
	}
}
