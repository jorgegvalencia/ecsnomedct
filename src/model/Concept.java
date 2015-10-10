package model;

import java.util.List;

public class Concept {
	private String cui;
	private String sctid;
	private String name;
	private String preferedName;
	private List<String> semtypes;	

	public Concept() {
	}

	public String getCui() {
		return cui;
	}

	public void setCui(String cui) {
		this.cui = cui;
	}

	public String getSctid() {
		return sctid;
	}

	public void setSctid(String sctid) {
		this.sctid = sctid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPreferedName() {
		return preferedName;
	}

	public void setPreferedName(String preferedName) {
		this.preferedName = preferedName;
	}

	public List<String> getSemtypes() {
		return semtypes;
	}

	public void setSemtypes(List<String> semtypes) {
		this.semtypes = semtypes;
	}
}
