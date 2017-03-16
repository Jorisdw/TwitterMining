import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultEdge;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.graph.UndirectedWeightedSubgraph;

import entities.Hashtag;
import entities.HashtagTweets;
import entities.HashtagsGraph;
import entities.TweetObj;


public class HelperFunc {
	
	public static ArrayList<TweetObj> tweetsFilter(ArrayList <TweetObj> tweetsInput)
	{
		System.out.println("Filtering out double tweets.");
		ArrayList<TweetObj> output = new ArrayList<TweetObj>();
		boolean found = false;
		for (TweetObj currTweet : tweetsInput)
		{
			found = false;
			for (TweetObj basTweet : output)
			{
				if(currTweet.containsSameHashtags(basTweet))
				{
					found = true;
					break;
				}
			}
			if(!found)
			{
				output.add(currTweet);
			}
		}
		
		return output;
	}
	
	public static SimpleWeightedGraph <String, DefaultWeightedEdge> buildHashtagsGraph(ArrayList<TweetObj> tweets)
	{
		SimpleWeightedGraph <String, DefaultWeightedEdge> output = new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		for(TweetObj currTween : tweets)
		{
			currTween.addHashtagsToGraph(output);
		}
		return output;
	}
	
	/////
	
	public static ArrayList<TweetObj> sortTweets(ArrayList <TweetObj> tweetsInput){
		HashSet<TweetObj> tweets = new HashSet<TweetObj>(tweetsInput);
		ArrayList<TweetObj> result = new ArrayList<TweetObj>(tweets);
		return result;
	}
	
	public static void printGraph(SimpleWeightedGraph<String, DefaultWeightedEdge> graph)
	{
		System.out.println("Stqrt Printing"); 
		Set<DefaultWeightedEdge > edgs = graph.edgeSet();
		for (DefaultWeightedEdge edg : edgs)
		{
			System.out.println(graph.getEdgeSource(edg) + "--------->" + graph.getEdgeTarget(edg) + "     -- Weight: " + graph.getEdgeWeight(edg) + "" );
		}
	}
	
	public static void printGraph1(SimpleWeightedGraph<String, DefaultWeightedEdge> graph)
	{
		Set<DefaultWeightedEdge > edgs = graph.edgeSet();
		for (DefaultWeightedEdge edg : edgs)
		{
			if(graph.getEdgeWeight(edg) > 5)
				System.out.println(graph.getEdgeSource(edg) + "--------->" + graph.getEdgeTarget(edg) + "     -- Weight: " + graph.getEdgeWeight(edg) + "" );
		}
	}
	
	public static void printGraphToFile(SimpleWeightedGraph<String, DefaultWeightedEdge> graph, String fileName) throws IOException{
		PrintWriter writer = new PrintWriter(System.getProperty("user.dir") + "/Subgraphs/" + fileName, "UTF-8");
		String[] str = getStatisticsInfo((SimpleWeightedGraph<String, DefaultWeightedEdge>) graph.clone());
		writer.println("Density: " + str[0]);
		writer.println("Number of distinct users: " + str[1]);
		writer.println("Number of distinct tweets: " + str[2]);
		for (DefaultWeightedEdge edg : graph.edgeSet())
		{
			if(graph.getEdgeWeight(edg) > 5)
				writer.println(graph.getEdgeSource(edg) + "-->" + graph.getEdgeTarget(edg) + "   --   With weight: " + graph.getEdgeWeight(edg) + "" );
		}
		writer.close();
	}
	
	public static String [] getStatisticsInfo(SimpleWeightedGraph<String, DefaultWeightedEdge> graph)
	{
		String [] out = new String [3];
		Set<String> nodes = graph.vertexSet();
		double deg = 0;
		Set<DefaultWeightedEdge > edgs = graph.edgeSet();
		for (DefaultWeightedEdge edg : edgs)
		{
			deg += graph.getEdgeWeight(edg);
		}
		out[0] = "" + deg/nodes.size();
		
		//
		HashMap<String, Integer> distinctUsers = new HashMap<String, Integer>();
		HashMap<String, Integer> distinctTweets = new HashMap<String, Integer>();
		
		Hashtag hashTg = null;
		int counter = 0;
		for(String vertx : nodes)
		{
			hashTg = HashtagTweets.hashtagTweetsHashmap.getHashtagTweets().get(vertx);
			for(TweetObj twet : hashTg.getTweets())
			{
				if(distinctTweets.get(twet.getTweetId()) == null)
					distinctTweets.put(twet.getTweetId(), 1);
				else
				{
					counter = distinctTweets.get(twet.getTweetId());
					distinctTweets.remove(twet.getTweetId());
					distinctTweets.put(twet.getTweetId(), counter + 1);
				}
				
				if(distinctUsers.get(twet.getUserId()) == null)
					distinctUsers.put(twet.getUserId(), 1);
				else
				{
					counter = distinctUsers.get(twet.getUserId());
					distinctUsers.remove(twet.getUserId());
					distinctUsers.put(twet.getUserId(), counter + 1);
				}
					
			}
			
			
		}
		out[1] = "" + distinctUsers.size();
		out[2] = "" + distinctTweets.size();
		return out;
	}
	
	
}
