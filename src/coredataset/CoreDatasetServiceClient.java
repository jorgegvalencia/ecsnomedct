package coredataset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import exceptions.ServiceNotAvailable;

public class CoreDatasetServiceClient{
	private CoreDatasetService service;
	private CoreDatasetServicePortType port;
	private static HashMap<String,String> index = new HashMap<>(); // scui,normalForm
	private static HashMap<String,String> indexFull = new HashMap<>(); // scui,normalForm

	public CoreDatasetServiceClient() throws ServiceNotAvailable{
		try{
			service = new CoreDatasetService();
			port = service.getCoreDatasetServiceHttpsSoap12Endpoint();
		} catch (Exception e){
			throw new ServiceNotAvailable();
		}
	}

	public String getNormalFormAsString(String scui){
		return getNormalFormAsString(scui,true);
	}

	public String getNormalFormAsString(String scui, boolean term){
		if(term){
			if(indexFull.containsKey(scui)){
				return indexFull.get(scui);
			}
		}
		else{
			if(index.containsKey(scui)){
				return index.get(scui);
			}
		}
		NormalizedExpression expression = port.getShortNormalForm(scui);
		StringBuilder sb = new StringBuilder();
		String focusConceptCode = expression.getFocusConcept().getValue();
		String focusConcept = expression.getFocusConceptTitle().getValue();
		if(focusConcept != null){
			if(term){
				sb.append(focusConceptCode+"|"+focusConcept+"|");
			}
			else
				sb.append(focusConceptCode);
			List<SnomedRelationship> relationshipList = expression.getRelationships();
			if(!relationshipList.isEmpty()){
				sb.append(":");
				for(int i=0;i<relationshipList.size();i++){
					sb.append(getSnomedRelationshipAsString(relationshipList.get(i),term));
					if(i<relationshipList.size()-1) sb.append(",");
				}
			}
		}
		else{
			sb.append("-");
		}
		if(term){
			indexFull.put(scui, sb.toString());
		}
		else{
			index.put(scui, sb.toString());
		}
		return sb.toString();
	}

	private String getSnomedRelationshipAsString(SnomedRelationship snrel, boolean term){
		String relCode = snrel.getRelationship().getValue();
		String relTerm = snrel.getRelationshipTitle().getValue();
		String relValue = getNormalizedExpressionAsString(snrel.getRelationshipValue().getValue(),term);
		String result;
		if(term){
			result = relCode+"|"+relTerm+"|="+relValue;	
		}
		else{
			result = relCode+"="+relValue;	
		}
		return result;
	}

	private String getNormalizedExpressionAsString(NormalizedExpression expression, boolean term){
		String focusConceptCode = expression.getFocusConcept().getValue();
		String focusConcept = expression.getFocusConceptTitle().getValue();
		String result;
		if(term){
			result = focusConceptCode+"|"+focusConcept+"|";
		}
		else
			result = focusConceptCode;
		List<SnomedRelationship> relationshipList = expression.getRelationships();
		if(!relationshipList.isEmpty()){
			for(int i=0;i<relationshipList.size();i++){
				result+=getSnomedRelationshipAsString(relationshipList.get(i),term);
				if(i<relationshipList.size()-1) result+=",";
			}
		}
		return result;
	}

	public String getRootConcept(String term){
		return port.getRootConcept(term);
	}

	public List<String> getParents(String term){
		return port.getParents(term);
	}

	public boolean isParent(String parent, String child){
		return getParents(child).contains(parent);	
	}

	public List<String> getBestMatches(List<String> terms){
		List<String> l = new ArrayList<>();
		List<String> aux = new CopyOnWriteArrayList<>(terms);
		for(String parent: terms){
			Iterator<String> it = aux.iterator();
			while(it.hasNext()){
				String child = it.next();
				if(isParent(parent,child)){
					l.add(parent);
					aux.remove(child);
				}
			}
		}
		return l;
	}

}
