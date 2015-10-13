package nlp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nlp.NLPTagger;
import nlp.NLPTokenizer;

final class TextProcessor {

	private static final List<String> stopWords = new ArrayList<String>(Arrays.asList("and","by","for","in","of","or","the","to","with","no"));
	private static final List<String> exclusionWords = new ArrayList<String>(Arrays.asList("about","alongside","an","anything","around","as","at",
			"because","before","being","both","cannot","chronically","consists","covered","does",
			"during","every","find","from","instead","into","more","must","no","not","only","or",
			"properly","side","sided","some","something","specific","than","that","things","this",
			"throughout","up","using","usually","when","while"));
	private static final List<String> uncoveredWords = new ArrayList<String>(Arrays.asList("or",":","-","\\)","and","be","must","\\(","have",">","is",
			"are","that",",",";","<","who","of","since","and/or","as","would","if","for",").",
			"will","may","e.g.","has","with","OR","been","but","had","either","unless","should",
			"Must","in","of the","any","than","by","which","If","Have","they","to","can","Has"));

	TextProcessor() {
		// TODO Auto-generated constructor stub
	}

	static String ProcessEligibilityCriteria(String text){
		String refinedText;
		// Elimina los guiones de puntos de contenido
		refinedText = text.replaceAll("-\\s+(?=[A-Z])", "");
		// Elimina los puntos de contenido numericos
		//refinedText = refinedText.replaceAll("([0-9]+\\.)+\\s", "");
		// A las oraciones sin punto final y con salto de linea se le anade un punto final
		refinedText = refinedText.replaceAll("(?<=.)\n\\s+(?=[A-Z]{1}[a-z])", ". ");
		refinedText = refinedText.replaceAll("\\.{2}", ". ");
		//refinedText = refinedText.replaceAll("-", " ");
		refinedText = refinedText.replaceAll("\\s+", " ");
		//refinedText = refinedText.replaceAll("(?<=\\p{Punct})\n\\s+(?=[A-Z])", "\n");
		//refinedText = refinedText.replaceAll(" [a-z]. ","");
		return refinedText;
	}

	static String removeSW(String text){
		String refinedText = text;
		for(String sw: stopWords){
			refinedText = refinedText.replaceAll("\\s"+sw+"\\s", " ");
		}
		return refinedText;
	}

	static String removeEW(String text){
		String refinedText = text;
		for(String ew: exclusionWords){
			refinedText = refinedText.replaceAll("\\s"+ew+"\\s", " ");
		}
		return refinedText;
	}
	
	static String removeUW(String text){
		String refinedText = text;
		for(String uw: uncoveredWords){
			refinedText = refinedText.replaceAll(uw, " ");
		}
		return refinedText;
	}
	
	static String processNounPhrase(String phrase){
		String refinedText = phrase;
		return refinedText;
	}
	
	static List<String> getPOSTagsAsList(String np){
    	NLPTokenizer tokenizer = new NLPTokenizer("resources/en-token.bin");
    	NLPTagger tagger = new NLPTagger("resources/en-pos-maxent.bin");
		return tagger.posTag(tokenizer.tokenize(np));
    }
    
    static String[] getPOSTagsAsArray(String np){
    	NLPTokenizer tokenizer = new NLPTokenizer("resources/en-token.bin");
    	NLPTagger tagger = new NLPTagger("resources/en-pos-maxent.bin");
    	return tagger.posTag(tokenizer.tokenizeArray(np));
    }
}
