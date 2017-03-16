package entities;

import java.util.ArrayList;

public class Hashtag {
	private ArrayList<TweetObj> tweets = null;
	public ArrayList<TweetObj> getTweets() {
		return tweets;
	}
	public void setTweets(ArrayList<TweetObj> tweets) {
		this.tweets = tweets;
	}
	public Hashtag()
	{
		tweets = new ArrayList<TweetObj>();
	}
	public void addTweet(TweetObj tweet)
	{
		tweets.add(tweet);
	}
	public String printData() {
		String out = "";
		for(TweetObj tweet : tweets)
			out += "User:  " + tweet.gettUser() + " ** User ID:  " + tweet.getUserId()  + " ** Lang:  " + tweet.getLan() + " ** retweet count : " + tweet.getRetweet_count()+ " ** place :  " + tweet.getPlace()+ " ** Text :  " + tweet.getTText() + "\n"; 
		
		out += "----------------------------------------------------------------------\n";
		return out;
	}
}
