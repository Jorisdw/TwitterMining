package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class TweetObj {

	private String tweetId;
	public String getTweetId() {
		return tweetId;
	}
	public void setTweetId(String tweetId) {
		this.tweetId = tweetId;
	}
	private ArrayList<String> hashtags ;
	public ArrayList<String> getHashtags() {
		return hashtags;
	}
	public void setHashtags(ArrayList<String> hashtags) {
		this.hashtags = hashtags;
	}
	
	public TweetObj()
	{
		hashtags = new ArrayList<String>();
	}
	
	
	private int retweet_count;
	
	private String TText; 
	
	private String tUser;
	
	private String place;
	
	private String lan;
	
	private String userId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLan() {
		return lan;
	}
	public void setLan(String lan) {
		this.lan = lan;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String gettUser() {
		return tUser;
	}
	public void settUser(String tUser) {
		this.tUser = tUser;
	}
	public String getTText() {
		return TText;
	}
	public void setTText(String tText) {
		TText = tText;
	}
	public int getRetweet_count() {
		return retweet_count;
	}
	public void setRetweet_count(int retweet_count) {
		this.retweet_count = retweet_count;
	}
	public void addHashtag(String hash)
	{
		hashtags.add(hash);
	}
	
	public void sortHashtags(){
		HashSet<String> hashtags1 = new HashSet<String>(hashtags);
		hashtags = new ArrayList<String>(hashtags1);
		Collections.sort(hashtags);
	}
	
	public String getHashtagsAsString() {
		String out = "";
		for (String hash : hashtags)
		{
			out += hash;
		}
		return out;
		

	}   
	public boolean containsSameHashtags(TweetObj tweet)
	{
		if(hashtags.size() != tweet.getHashtags().size())
			return false;
		
		for (int i = 0; i< hashtags.size(); i++)
		{
			if(!hashtags.get(i).equals(tweet.getHashtags().get(i)))
				return false;
		}
		return true;
	}
	
	public String toString(){
		return tweetId + "--" + hashtags.toString();
	}
	
	
	@Override
	public boolean equals(Object tweet){
		return containsSameHashtags((TweetObj) tweet);
	}
	
	@Override
	public int hashCode(){
		return getHashtagsAsString().hashCode();
	}

	public SimpleWeightedGraph<String, DefaultWeightedEdge> addHashtagsToGraph(SimpleWeightedGraph<String, DefaultWeightedEdge> graph)
	{
		for(int i = 0; i < hashtags.size() - 1; i++)
		{
			for(int j= i + 1; j < hashtags.size(); j++)
			{
				if(graph.containsEdge(hashtags.get(i), hashtags.get(j)))
				{
					DefaultWeightedEdge edg = graph.getEdge(hashtags.get(i), hashtags.get(j));
					graph.setEdgeWeight(edg, graph.getEdgeWeight(edg) + 1);
				}
				else
					{
						graph.addVertex(hashtags.get(i));
						graph.addVertex(hashtags.get(j));
						graph.addEdge(hashtags.get(i), hashtags.get(j));
					}
				
			}
			
		}
		
		return graph;
	}
	
	
	

}
