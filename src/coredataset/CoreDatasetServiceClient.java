package coredataset;

import java.util.List;

import exceptions.ServiceNotAvailable;

public class CoreDatasetServiceClient{
	private CoreDatasetService service;
	private CoreDatasetServicePortType port;
	
	public CoreDatasetServiceClient() throws ServiceNotAvailable{
		try{
			service = new CoreDatasetService();
			port = service.getCoreDatasetServiceHttpsSoap12Endpoint();
		} catch (Exception e){
			throw new ServiceNotAvailable();
		}
	}
	
	public String getNormalFormAsString(String term){
		return getNormalFormAsString(term,false);
	}
	
	public String getNormalFormAsString(String term, boolean onlyCode){
		NormalizedExpression expression = port.getShortNormalForm(term);
		StringBuilder sb = new StringBuilder();
		String focusConceptCode = expression.getFocusConcept().getValue();
		String focusConcept = expression.getFocusConceptTitle().getValue();
		if(focusConcept != null){
			if(onlyCode==false){
				sb.append(focusConceptCode+"|"+focusConcept+"|");
			}
			else
				sb.append(focusConceptCode);
			List<SnomedRelationship> relationshipList = expression.getRelationships();
			if(!relationshipList.isEmpty()){
				sb.append(":");
				for(int i=0;i<relationshipList.size();i++){
					sb.append(getSnomedRelationshipAsString(relationshipList.get(i),onlyCode));
					if(i<relationshipList.size()-1) sb.append(",");
				}
			}
		}
		else{
			sb.append("-");
		}
		return sb.toString();
	}
	
	private String getSnomedRelationshipAsString(SnomedRelationship snrel, boolean onlyCode){
		String relCode = snrel.getRelationship().getValue();
		String relTerm = snrel.getRelationshipTitle().getValue();
		String relValue = getNormalizedExpressionAsString(snrel.getRelationshipValue().getValue(),onlyCode);
		String result;
		if(onlyCode==false){
			result = relCode+"|"+relTerm+"|="+relValue;	
		}
		else{
			result = relCode+"="+relValue;	
		}
		return result;
	}

	private String getNormalizedExpressionAsString(NormalizedExpression expression, boolean onlyCode){
		String focusConceptCode = expression.getFocusConcept().getValue();
		String focusConcept = expression.getFocusConceptTitle().getValue();
		String result;
		if(onlyCode==false){
			result = focusConceptCode+"|"+focusConcept+"|";
		}
		else
			result = focusConceptCode;
		List<SnomedRelationship> relationshipList = expression.getRelationships();
		if(!relationshipList.isEmpty()){
			for(int i=0;i<relationshipList.size();i++){
				result+=getSnomedRelationshipAsString(relationshipList.get(i),onlyCode);
				if(i<relationshipList.size()-1) result+=",";
			}
		}
		return result;
	}
}
