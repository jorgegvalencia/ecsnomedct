package model;

import java.util.List;

public class Concept {
	private String cui;
	private String sctid;
	private String name;
	private String preferedName;
	private String phrase;
	private List<String> semtypes;	

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
	
	
}
