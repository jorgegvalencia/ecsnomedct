package nlp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;

public class NLPChunker {
	String model_route;

	public NLPChunker(String model){
		model_route = model;
	}

	public List<String> chunk(List<String> tokenList, List<String> tagList){
		InputStream modelIn = null;
		ChunkerModel model = null;
		List<String> chunkTags = new ArrayList<String>();
		String[] tokens = new String[tokenList.size()];
		tokens = tokenList.toArray(tokens);
		String[] posTags = new String[tagList.size()];
		tokens = tagList.toArray(posTags);
		try {
			modelIn = new FileInputStream(model_route);
			model = new ChunkerModel(modelIn);
			ChunkerME chunker = new ChunkerME(model);
			for(String chunk : chunker.chunk(tokens, posTags))
				chunkTags.add(chunk);
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
		return chunkTags;

	}
}
