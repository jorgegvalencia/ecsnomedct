package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import model.ClinicalTrial;

public class CTManager {

	public CTManager(){

	}

	/**
	 * Checks if the clinical trial with the specified nctid is already in the local files.
	 * @param nctid
	 * @return true if the XML file does exists, false otherwise.
	 */
	private boolean checkLocalFile(String nctid){
		String filePath = "resources/"+nctid+".xml";
		File f = new File(filePath);
		return f.exists();
	}

	/**
	 * Downloads the clinical trial specified as an XML file.
	 * @param nctid
	 */
	private void downloadClinicalTrial(String nctid){
		String path = "https://clinicaltrials.gov/show/"+nctid+"?displayxml=true";
		String filePath = "resources/"+nctid+".xml";
		try {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "text/xml");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream()),"utf-8"));
			File trial = new File(filePath);
			BufferedWriter bw = new BufferedWriter(new FileWriter(trial));
			String line;
			while((line = br.readLine()) != null){
				bw.write(line);
				bw.newLine();
			}
			bw.close();
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Builds a ClinicalTrial object from the XML file of the trial specified by the ntcid.
	 * @param nctid
	 * @return
	 */
	public ClinicalTrial buildClinicalTrial(String nctid){
		ClinicalTrial ct = new ClinicalTrial();
		String filePath = "resources/"+nctid+".xml";
		//System.out.println("Checking local files...");
		if(!checkLocalFile(nctid)){
			//System.out.println("Sending request to clinicaltrials.gov...");
			downloadClinicalTrial(nctid);
		}
		try{
			File file = new File(filePath);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLStreamReader streamReader = factory.createXMLStreamReader(br);
			String currentElement = null;
			while(streamReader.hasNext()){
				int event = streamReader.next();
				switch(event){
				case XMLStreamConstants.START_ELEMENT:
					currentElement = streamReader.getLocalName();
					switch(currentElement){
					case "url":
						ct.setUrl(new URL(streamReader.getElementText()));
						break;
					case "nct_id":
						ct.setNctId(streamReader.getElementText());
						break;
					case "brief_title":
						ct.setTitle(streamReader.getElementText());
						break;
						/*					case "overall_status":
						ct.setOverall_status(overall_status);
						break;*/
					case "start_date":
						ct.setStartDate(streamReader.getElementText());
						break;
					case "study_type":
						ct.setStudyType(streamReader.getElementText());
						break;
					case "sampling_method":
						ct.setSamplingMethod(streamReader.getElementText());
						break;
					default:
					}
					break;
				case XMLStreamConstants.CHARACTERS:
					if(currentElement.equals("brief_summary")){
						streamReader.next();
						if(streamReader.getEventType() == XMLStreamReader.START_ELEMENT){
							currentElement = streamReader.getLocalName();
							ct.setBriefSummary(streamReader.getElementText());
						}
					}
					else if(currentElement.equals("criteria")){
						streamReader.next();
						if(streamReader.getEventType() == XMLStreamReader.START_ELEMENT){
							currentElement = streamReader.getLocalName();
							ct.setCriteria((streamReader.getElementText()));
						}
					}
					else if(currentElement.equals("study_pop")){
						streamReader.next();
						if(streamReader.getEventType() == XMLStreamReader.START_ELEMENT){
							currentElement = streamReader.getLocalName();
							ct.setStudyPop(((streamReader.getElementText())));
						}
					}
					break;
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ct;
	}

	public List<ClinicalTrial> buildTrialsSet(){
		String path="resources/";
		List<ClinicalTrial> list = new ArrayList<ClinicalTrial>();
		File[] files = new File(path).listFiles();
		for(File f: files){
			if(f.getName().contains("NCT")){
				ClinicalTrial ct = buildClinicalTrial(f.getName().replace(".xml", ""));
				list.add(ct);
			}
		}
		return list;
	}

	public void showTrialsFiles(){
		String path="resources/";
		File[] files = new File(path).listFiles();
		for (File file : files) {
			if(file.getName().contains("NCT")){
				if (file.isDirectory()) {
					System.out.println("Directory: " + file.getName());
				} else {
					System.out.println("File: " + file.getName());
				}
			}
		}
	}
}
