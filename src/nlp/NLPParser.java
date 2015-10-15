package nlp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;

public class NLPParser {
	String model_route;

	public NLPParser(String model){
		model_route = model;
	}

	public List<Parse> parse(List<String> utterance){
		InputStream modelIn = null;
		List<Parse> parseList = new ArrayList<Parse>();
		String[] utterances = new String[utterance.size()];
		utterance.toArray(utterances);
		try {
			modelIn = new FileInputStream(model_route);
			ParserModel model = new ParserModel(modelIn);
			Parser parser = ParserFactory.create(model);
			for(String utt : utterances){
				Parse parses[] = ParserTool.parseLine(utt, parser, 1);
				for(Parse parse: parses){
					parseList.add(parse);
				}
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
		return parseList;

	}
}
