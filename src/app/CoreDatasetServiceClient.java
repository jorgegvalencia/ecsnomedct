package app;

import java.util.List;

import norm.CoreDatasetService;
import norm.CoreDatasetServicePortType;
import norm.NormalizedExpression;
import norm.SnomedRelationship;

public class CoreDatasetServiceClient{
	private CoreDatasetService service;
	CoreDatasetServicePortType port;
	
	public CoreDatasetServiceClient(){
		service = new CoreDatasetService();
		port = service.getCoreDatasetServiceHttpsSoap12Endpoint();
	}
	
	public String getNormalFormAsString(String term){
		NormalizedExpression expression = port.getShortNormalForm(term);
		StringBuilder sb = new StringBuilder();
		String focusConceptCode = expression.getFocusConcept().getValue();
		String focusConcept = expression.getFocusConceptTitle().getValue();
		if(focusConcept != null){
			sb.append(focusConceptCode+"|"+focusConcept+"|");
			List<SnomedRelationship> relationshipList = expression.getRelationships();
			if(!relationshipList.isEmpty()){
				sb.append(":");
				for(int i=0;i<relationshipList.size();i++){
					sb.append(getSnomedRelationshipAsString(relationshipList.get(i)));
					if(i<relationshipList.size()-1) sb.append(",");
				}
			}
		}
		else{
			sb.append("El concepto con este sctid no esta definido");
		}
		return sb.toString();
	}
	
	public void prettyPrintNormalForm(String term){
		NormalizedExpression expression = port.getShortNormalForm(term);
		String focusConceptCode = expression.getFocusConcept().getValue();
		String focusConcept = expression.getFocusConceptTitle().getValue();
		if(focusConcept != null){
			List<SnomedRelationship> relationshipList = expression.getRelationships();
			System.out.print(focusConceptCode+"|"+focusConcept+"|");
			if(!relationshipList.isEmpty()){
				System.out.print(":\n");
				for(SnomedRelationship snrel: relationshipList){
					printSnomedRelationship(snrel);
				}
			}
			else
				System.out.println("\n");
		}
	}
	
	private String getSnomedRelationshipAsString(SnomedRelationship snrel){
		String relCode = snrel.getRelationship().getValue();
		String relTerm = snrel.getRelationshipTitle().getValue();
		String relValue = getNormalizedExpressionAsString(snrel.getRelationshipValue().getValue());
		String result = relCode+"|"+relTerm+"|="+relValue;
		return result;
	}

	private String getNormalizedExpressionAsString(NormalizedExpression expression){
		String focusConceptCode = expression.getFocusConcept().getValue();
		String focusConcept = expression.getFocusConceptTitle().getValue();
		String result = focusConceptCode+"|"+focusConcept+"|";
		List<SnomedRelationship> relationshipList = expression.getRelationships();
		if(!relationshipList.isEmpty()){
			for(int i=0;i<relationshipList.size();i++){
				result+=getSnomedRelationshipAsString(relationshipList.get(i));
				if(i<relationshipList.size()-1) result+=",";
			}
		}
		return result;
	}

	private void printNormalizedExpression(NormalizedExpression expression){
		String focusConceptCode = expression.getFocusConcept().getValue();
		String focusConcept = expression.getFocusConceptTitle().getValue();
		List<SnomedRelationship> relationshipList = expression.getRelationships();
		System.out.print(focusConceptCode+"|"+focusConcept+"|");
		if(!relationshipList.isEmpty()){
			for(SnomedRelationship snrel: relationshipList){
				printSnomedRelationship(snrel);
			}
		}
	}
	
	private void printSnomedRelationship(SnomedRelationship snrel){
		String relCode = snrel.getRelationship().getValue();
		String relTerm = snrel.getRelationshipTitle().getValue();
		System.out.print("\t"+relCode+"|"+relTerm+"|=");
		printNormalizedExpression(snrel.getRelationshipValue().getValue());
		System.out.print("\n");
	}
}
