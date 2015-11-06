package nlp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class NLPTokenizer {
	String model_route;

	public NLPTokenizer(String model){
		model_route = model;
	}
	public NLPTokenizer(){
		model_route = "resources/en-token.bin";
	}
	public List<String> tokenize(String sentence){
		InputStream modelIn = null;
		List<String> tokens = new ArrayList<String>();
		try {
			modelIn = new FileInputStream(model_route);
			TokenizerModel model = new TokenizerModel(modelIn);
			Tokenizer tokenizer = new TokenizerME(model);
			for(String token: tokenizer.tokenize(sentence)){
				tokens.add(token);
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
		return tokens;
	}
	
	public String[] tokenizeArray(String sentence){
		InputStream modelIn = null;
		String[] tokens = null;
		try {
			modelIn = new FileInputStream(model_route);
			TokenizerModel model = new TokenizerModel(modelIn);
			Tokenizer tokenizer = new TokenizerME(model);
			tokens = tokenizer.tokenize(sentence);
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
		return tokens;
	}
}
