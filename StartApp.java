import java.awt.font.GraphicAttribute;
import java.io.IOException;
import java.util.ArrayList;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.json.simple.parser.ParseException;

import entities.*;

public class StartApp {

	/**
	 * @param args
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, ParseException {
		
		
		ArrayList<TweetObj> mytweets1 = new ArrayList<TweetObj>(HelperFunc.sortTweets(TweetProcessor.getAllTweets()));
		System.out.println("Start Graph Building ");
		SimpleWeightedGraph <String, DefaultWeightedEdge> input = HelperFunc.buildHashtagsGraph(mytweets1);
		System.out.println("Finish Graph Building ");
		
		System.out.println("Graph Printing");
		ExtractDenseSubgraphs extractor = new ExtractDenseSubgraphs();
		//output = extractor.densestSubgraphAlgorithmLinear(output);
		SimpleWeightedGraph <String, DefaultWeightedEdge> output = extractor.densestSubgraphAlgorithmLinearBtwn210((SimpleWeightedGraph<String, DefaultWeightedEdge>) input.clone());
		HelperFunc.printGraphToFile(output, "Subgraph");
		
		SimpleWeightedGraph <String, DefaultWeightedEdge> UpdatedGraph = (SimpleWeightedGraph<String, DefaultWeightedEdge>) input.clone();
		SimpleWeightedGraph <String, DefaultWeightedEdge> output2 = output;
		
		int i = 1;
		while(i != 11){
		UpdatedGraph = extractor.deleteSubgraphEdges(output2, UpdatedGraph);
		output2 = extractor.densestSubgraphAlgorithmLinearBtwn210((SimpleWeightedGraph<String, DefaultWeightedEdge>) UpdatedGraph.clone());
		HelperFunc.printGraphToFile(output2, "Subgraph"+i);
		i++;
		}
		
		UpdatedGraph = (SimpleWeightedGraph<String, DefaultWeightedEdge>) input.clone();
		output2 = output;
		int j = 0;
		while(j != 10){
		UpdatedGraph = extractor.deleteSubgraphVertexes(output2, UpdatedGraph);	
		output2 = extractor.densestSubgraphAlgorithmLinearBtwn210((SimpleWeightedGraph<String, DefaultWeightedEdge>) UpdatedGraph.clone());
		HelperFunc.printGraphToFile(output2, "Subgraph"+ (j+i) );
		j++;
		}
		
	}
	

}
