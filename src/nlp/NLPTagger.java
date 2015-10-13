package nlp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

public class NLPTagger {
	String model_route;

	public NLPTagger(String model){
		model_route = model;
	}

	public List<String> posTag(List<String> tokenList){
		InputStream modelIn = null;
		List<String> tags = new ArrayList<String>();
		String[] tokens = new String[tokenList.size()];
		tokens = tokenList.toArray(tokens);
		try {
			modelIn = new FileInputStream(model_route);
			POSModel model = new POSModel(modelIn);
			POSTaggerME tagger = new POSTaggerME(model);
			for(String posTag : tagger.tag(tokens)){
				tags.add(posTag);
			}
		}
		catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (modelIn != null) {
				try {
					modelIn.close();
				}
				catch (IOException e) {
				}
			}
		}
		return tags;

	}
	
	public String[] posTag(String[] tokens){
		InputStream modelIn = null;
		String[] tags = null;
		try {
			modelIn = new FileInputStream(model_route);
			POSModel model = new POSModel(modelIn);
			POSTaggerME tagger = new POSTaggerME(model);
			tags = tagger.tag(tokens);
		}
		catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (modelIn != null) {
				try {
					modelIn.close();
				}
				catch (IOException e) {
				}
			}
		}
		return tags;
	}
	
	
}
