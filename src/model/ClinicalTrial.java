package model;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DBConnector;

public class ClinicalTrial {
	private static final String NORM = "jdbc:mysql://localhost/norm";
	private static final String USER = "root";
	private static final String PASS = "root";
	
	private URL url;
	private String nctId;
	private String title;
	private String briefSummary;
	private String startDate;
	private String studyType;
	// eligibility
	private String studyPop;
	private String samplingMethod;
	private String criteria;
	private String minimumAge;
	private String maximumAge;
	
	public ClinicalTrial() {
		nctId = "-";
		title = "-";
		briefSummary = "-";
		startDate = "-";
		studyType = "-";
		studyPop = "-";
		samplingMethod = "-";
		criteria = "-";
		minimumAge = "-";
		maximumAge = "-";
	}
	
	public void persistClinicalTrial(){
		DBConnector db = new DBConnector(NORM,USER,PASS);
		String sql = "INSERT INTO clinical_trial (id,title,studytype) VALUES (?,?,?) ON DUPLICATE KEY UPDATE"
				+ " title=VALUES(title), studytype=VALUES(studytype)";
		PreparedStatement ps = db.prepareInsert(sql);
		if(ps != null){
			try {
				ps.setString(1, nctId);
				ps.setString(2, title);
				ps.setString(3, studyType);
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		db.endConnector();
	}
	
	public void print(){
    	System.out.format(
				"%17s:\t%s\n" +
				"%17s:\t%s\n" +
				"%17s:\t%s\n" +
				"%17s:\t%s\n" +
				"%17s:\t%s\n" +
				//"%17s:\t%s\n" +
				"%17s:\t%s\n",
				"CT Title",title,
				"NCT ID",nctId,
				"URL",url,
				"Start date",startDate,
				"Study type",studyType,
				"Brief Summary",briefSummary
				//"Criteria", criteria
				);
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public String getNctId() {
		return nctId;
	}

	public void setNctId(String nctId) {
		this.nctId = nctId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBriefSummary() {
		return briefSummary;
	}

	public void setBriefSummary(String briefSummary) {
		this.briefSummary = briefSummary;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStudyType() {
		return studyType;
	}

	public void setStudyType(String studyType) {
		this.studyType = studyType;
	}

	public String getStudyPop() {
		return studyPop;
	}

	public void setStudyPop(String studyPop) {
		this.studyPop = studyPop;
	}

	public String getSamplingMethod() {
		return samplingMethod;
	}

	public void setSamplingMethod(String samplingMethod) {
		this.samplingMethod = samplingMethod;
	}

	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}

	public String getMinimumAge() {
		return minimumAge;
	}

	public void setMinimumAge(String minimumAge) {
		this.minimumAge = minimumAge;
	}

	public String getMaximumAge() {
		return maximumAge;
	}

	public void setMaximumAge(String maximumAge) {
		this.maximumAge = maximumAge;
	}
	
	

}
