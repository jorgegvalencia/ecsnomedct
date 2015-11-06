package nlp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.parser.nndep.DependencyParser;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.PennTreebankLanguagePack;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.trees.TypedDependency;
import edu.stanford.nlp.util.CoreMap;*/

public final class TextProcessor {
	//private static MaxentTagger tagger = new MaxentTagger("edu/stanford/nlp/models/pos-tagger/english-left3words/english-left3words-distsim.tagger");
	//private static DependencyParser parser = DependencyParser.loadFromModelFile(DependencyParser.DEFAULT_MODEL);
	
	private static final List<String> STOP_WORDS = new ArrayList<String>(Arrays.asList("and","by","for","in","of","or","the","to","with","no"));
	private static final List<String> EXCLUSION_WORDS = new ArrayList<String>(Arrays.asList("about","alongside","an","anything","around","as","at",
			"because","before","being","both","cannot","chronically","consists","covered","does",
			"during","every","find","from","instead","into","more","must","no","not","only","or",
			"properly","side","sided","some","something","specific","than","that","things","this",
			"throughout","up","using","usually","when","while"));
	private static final List<String> UNCOVERED_WORDS = new ArrayList<String>(Arrays.asList("or",":","-","\\)","and","be","must","\\(","have",">","is",
			"are","that",",",";","<","who","of","since","and/or","as","would","if","for",").",
			"will","may","e.g.","has","with","OR","been","but","had","either","unless","should",
			"Must","in","of the","any","than","by","which","If","Have","they","to","can","Has"));


	public static String ProcessEligibilityCriteria(String text){
		String refinedText;
		// Elimina los puntos de contenido numericos
		refinedText = text.replaceAll("([0-9]+\\.(?=\\s))+\\s", " - ");
		// Elimina los guiones de puntos de contenido
		refinedText = refinedText.replaceAll("-\\s+(?=[A-Z])", "");
		// A las oraciones sin punto final y con salto de linea se le anade un punto final
		refinedText = refinedText.replaceAll("(?<=.)\n\\s+(?=[A-Z]{1}[a-z])", ". ");
		refinedText = refinedText.replaceAll("\\.{2}", ". ");
		//refinedText = refinedText.replaceAll("-", " ");
		refinedText = refinedText.replaceAll("\\s+", " ");
		refinedText = refinedText.replaceAll("\\\"", "");
		//refinedText = refinedText.replaceAll("(?<=\\p{Punct})\n\\s+(?=[A-Z])", "\n");
		//refinedText = refinedText.replaceAll(" [a-z]. ","");
		return refinedText;
	}

	public static String removeStopWords(String text){
		String refinedText = text;
		for(String sw: STOP_WORDS){
			refinedText = refinedText.replaceAll("\\s"+sw+"\\s", " ");
		}
		return refinedText;
	}

	public static String removeExcludedWords(String text){
		String refinedText = text;
		for(String ew: EXCLUSION_WORDS){
			refinedText = refinedText.replaceAll("\\s"+ew+"\\s", " ");
		}
		return refinedText;
	}

	public static String removeUncoveredWords(String text){
		String refinedText = text;
		for(String uw: UNCOVERED_WORDS){
			refinedText = refinedText.replaceAll(uw, " ");
		}
		return refinedText;
	}
	
	/**
	 * Returns the the list of sentences that form the given text.
	 * @param text
	 * @return
	 */
	public static List<String> getSentencesFromText(String text){
		NLPSentenceDetector sd = new NLPSentenceDetector("resources/en-sent.bin");
		return sd.detectSentences(text);
	}

	/**
	 * Returns a POS tag list for a given phrase.
	 * @param np
	 * @return
	 */
	public static List<String> getPOSTagsAsList(String np){
		NLPTokenizer tokenizer = new NLPTokenizer("resources/en-token.bin");
		NLPTagger tagger = new NLPTagger("resources/en-pos-maxent.bin");
		return tagger.posTag(tokenizer.tokenize(np));
	}

	/**
	 * Returns a POS tag array for a given phrase.
	 * @param np
	 * @return
	 */
	public static String[] getPOSTagsAsArray(String np){
		NLPTokenizer tokenizer = new NLPTokenizer("resources/en-token.bin");
		NLPTagger tagger = new NLPTagger("resources/en-pos-maxent.bin");
		return tagger.posTag(tokenizer.tokenizeArray(np));
	}

	/*public static String getPOSTagsAsString(String np){
		String tagged = tagger.tagString(np);
		tagged = tagged.replace("_", "/");
		return tagged;
	}*/
	
/*	public static List<String> getChunksAsList(String sentence){
		NLPTokenizer tokenizer = new NLPTokenizer("resources/en-token.bin");
		NLPChunker chunker = new NLPChunker("resources/en-chunker.bin");
		NLPTagger tagger = new NLPTagger("resources/en-pos-maxent.bin");
		return chunker.chunk(tokenizer.tokenize(sentence),tagger.posTag(tokenizer.tokenize(sentence)));
	}*/
	
/*	public static void getSentences(String text){
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, parse, pos");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		Annotation annotation = new Annotation(text);
		pipeline.annotate(annotation);
		List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
		for(CoreMap sentence: sentences){
			System.out.println("["+sentence+"]");
			getDependencies(sentence.toString());
		}
	}*/

/*	public static List<String> getDependencies(String text){
		List<String> list = new ArrayList<>();
		DocumentPreprocessor tokenizer = new DocumentPreprocessor(new StringReader(text));
		for (List<HasWord> sentence : tokenizer) {
			List<TaggedWord> tagged = tagger.tagSentence(sentence);
			GrammaticalStructure gs = parser.predict(tagged);
			for(TypedDependency dependency: gs.typedDependenciesCCprocessed()){
				//filter
				if(!dependency.toString().contains("punct") &&
						!dependency.toString().contains("case")){
					//list.add(dependency.toString());
					list.add(dependency.toString().replaceAll("\\(.*\\)", ""));
				}
			}
		}
		for(String l: list){
			System.out.println(l);
		}
		return list;
		Properties props = new Properties();
		
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		Annotation annotation = new Annotation("This is an easy sentence. And this is another.");
		pipeline.annotate(annotation);
		List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
		for(CoreMap sentence: sentences){
			SemanticGraph graph = sentence.get(CollapsedDependenciesAnnotation.class);
			graph.prettyPrint();
		}
		
		
	}*/
	
/*	public static void getDependencies2(){
		LexicalizedParser lp = LexicalizedParser.loadModel(
				"edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz",
				"-maxLength", "80", "-retainTmpSubcategories");
		TreebankLanguagePack tlp = new PennTreebankLanguagePack();
		// Uncomment the following line to obtain original Stanford Dependencies
		tlp.setGenerateOriginalDependencies(true);
		GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
		String[] sent = {"This", "is", "an", "easy", "sentence", "."};
		Tree parse = lp.apply(Sentence.toWordList(sent));
		GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
		Collection<TypedDependency> tdl = gs.typedDependenciesCollapsedTree();
		System.out.println(tdl);
	}*/
}
