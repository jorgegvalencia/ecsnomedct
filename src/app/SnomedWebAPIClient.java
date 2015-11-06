package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import db.DBConnector;

public class SnomedWebAPIClient {

	// https://rxnav.nlm.nih.gov/SnomedCTAPI.html
	public SnomedWebAPIClient(){
	}
	/*
	 * Status codes:
	 * -1 (unknown/invalid id)
	 *  0 (retired)
	 *  1 (active)
	 *  2 (moved)
	 */
	public int getStatus(String sctid){
		String path = "https://rxnav.nlm.nih.gov/REST/SnomedCT/status?id="+sctid;
		URL url;
		int status = 0;
		try {
			url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream()),"utf-8"));
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLStreamReader streamReader = factory.createXMLStreamReader(br);
			String currentElement = null;
			while(streamReader.hasNext()){
				int event = streamReader.next();
				switch(event){
				case XMLStreamConstants.START_ELEMENT:
					currentElement = streamReader.getLocalName();
					switch(currentElement){
					case "status":
						status=Integer.parseInt(streamReader.getElementText());
						break;
					default:
					}
				default:
				}
			}
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public int getStatusFromDB(String sctid){
		DBConnector db = new DBConnector("jdbc:mysql://localhost/snomedct", "root", "root");
		String sql = "SELECT DISTINCT active FROM curr_concept_s WHERE id='"+sctid+"'";
		int status = 0;
		ResultSet rs = db.performQuery(sql);
		if(rs != null){
			try {
				while(rs.next())
					status = Integer.parseInt(rs.getString("active"));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		db.endConnector();
		return status;
	}
	
	public String getFSN(String sctid){
		DBConnector db = new DBConnector("jdbc:mysql://localhost/snomedct", "root", "root");
		String result = null;
		String sql ="SELECT term FROM curr_concept_s, curr_description_s WHERE curr_concept_s.id = curr_description_s.conceptid "
				+ "AND curr_concept_s.id='"+sctid+"'"
				+ "AND curr_description_s.term LIKE '%(%)'"
				+ "AND curr_description_s.term NOT LIKE '%(qualifier value)'"
				+ "AND curr_concept_s.active='1'"
				+ "AND curr_description_s.active='1';";
		ResultSet rs = db.performQuery(sql);
		try {
			while(rs.next())
				result = rs.getString("term");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
