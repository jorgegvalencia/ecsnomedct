package model;

import java.net.URL;

public class ClinicalTrial {
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
