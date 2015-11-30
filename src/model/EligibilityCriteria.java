package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import db.DBConnector;

public class EligibilityCriteria {
	private static final String NORM = "jdbc:mysql://localhost/norm";
	private static final String USER = "dbuser";
	private static final String PASS = "1234";
	
	private String utterance;
	private List<Concept> concepts;
	private int criteriaType;

	public EligibilityCriteria(String utterance, List<Concept> concepts, int criteriaType) {
		this.utterance = utterance;
		this.concepts = concepts;
		this.criteriaType = criteriaType;
	}
	
	public void persistEligibilityCriteria(String nctid, int n){
		DBConnector db = new DBConnector(NORM,USER,PASS);
		String sql2 = "INSERT INTO eligibility_criteria (id, clinical_trial_id, inc_exc, utterance) VALUES(?,?,?,?) ON DUPLICATE KEY UPDATE"
				+ " inc_exc=VALUES(inc_exc), utterance=VALUES(utterance)";
		PreparedStatement ps = db.prepareInsert(sql2);
		if(ps != null){
			try {
				ps.setInt(1, n);
				ps.setString(2, nctid);
				ps.setInt(3, criteriaType);
				ps.setString(4, utterance);
				ps.executeUpdate();
				ps.close();
				db.endConnector();
				for(Concept c: concepts)
					c.persistConcept(nctid,n);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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
			concept.print();
		}
		System.out.println("+----------------------------------------------------------------------------------------------------------------------------------+");
	}
	
}
