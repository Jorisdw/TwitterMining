package entities;

import java.util.HashMap;

public class HashtagTweets {
	
	public static HashtagTweets hashtagTweetsHashmap = new HashtagTweets();
	
	private HashMap<String, Hashtag> hashtagTweets = null;
	
	public HashMap<String, Hashtag> getHashtagTweets() {
		return hashtagTweets;
	}

	public void setHashtagTweets(HashMap<String, Hashtag> hashtagTweets) {
		this.hashtagTweets = hashtagTweets;
	}

	public HashtagTweets()
	{
		hashtagTweets = new HashMap<String, Hashtag>();
	}
	
	public void addHashtagTweet(String hash, TweetObj tweet)
	{
		Hashtag htag = hashtagTweets.get(hash);
		if(htag == null)
		{
			Hashtag hTagOb = new Hashtag();
			hTagOb.addTweet(tweet);
			hashtagTweets.put(hash, hTagOb);
			
		}else
		{
			htag.addTweet(tweet);
		}
	}
}
