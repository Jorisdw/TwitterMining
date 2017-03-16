package entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class TweetProcessor {

	public TweetProcessor() {
		
	}
	
	public static ArrayList<TweetObj> getAllTweets() throws IOException, ParseException{
		
		ArrayList<TweetObj> tweets = new ArrayList<TweetObj>();
		
		tweets.addAll(getTweetsFrom("NewYork-2015-2-23"));
		tweets.addAll(getTweetsFrom("NewYork-2015-2-24"));
		tweets.addAll(getTweetsFrom("NewYork-2015-2-25"));
		tweets.addAll(getTweetsFrom("NewYork-2015-2-26"));
		for(int i=1 ; i != 29; i++){
			//tweets.addAll(getTweetsFrom("Paris-2015-2-" + i));
		}
		for(int i=1 ; i != 32; i++){
			//tweets.addAll(getTweetsFrom("Paris-2015-1-" + i));
		}
//
//		tweets.addAll(getTweetsFrom("Oscars-2015-2-23"));
//		tweets.addAll(getTweetsFrom("Oscars-2015-2-24"));
//		tweets.addAll(getTweetsFrom("Oscars-2015-2-25"));
//		tweets.addAll(getTweetsFrom("Oscars-2015-2-26"));
		return tweets;
	}
	
	public static ArrayList<TweetObj> getTweetsFrom(String file) throws IOException, ParseException{
		
		System.out.println("Processing file " + file);
		BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/data/" + file));		
		
		JSONParser parser=new JSONParser();	
		
		ArrayList<TweetObj> tweets = new ArrayList<TweetObj>();
		int counter = 0;
		for (String string = reader.readLine(); string != null; string = reader.readLine()) {
			JSONObject tweet = (JSONObject) parser.parse(string);
			TweetObj tweetObj = new TweetObj();
			
			//   Get the data from each tweet
			String tweetId = tweet.get("id").toString();
			tweetObj.setTweetId(tweetId);
			//System.out.println(tweetId);
			tweetObj.setLan(tweet.get("lang").toString());
			tweetObj.setRetweet_count(Integer.parseInt(tweet.get("retweet_count").toString()));
			//tweetObj.setPlace(tweet.get("place").toString());
			tweetObj.setTText(tweet.get("text").toString());
			JSONObject userInf = (JSONObject) parser.parse(tweet.get("user").toString());
			
			tweetObj.settUser(userInf.get("name").toString());
			tweetObj.setUserId(userInf.get("id").toString());
		
			JSONObject entities = (JSONObject) parser.parse(tweet.get("entities").toString());
			JSONArray hashtags = (JSONArray) parser.parse(entities.get("hashtags").toString());
			
			Iterator<JSONObject> iterator = hashtags.iterator();
			
			boolean hasHashtag = false;
			String hashName = "";
				while(iterator.hasNext()){
					JSONObject hashtag = iterator.next();
					hashName = hashtag.get("text").toString().toLowerCase();
					tweetObj.addHashtag(hashName);
					hasHashtag = true;
					HashtagTweets.hashtagTweetsHashmap.addHashtagTweet(hashName, tweetObj);
				}
			
			
			if(hasHashtag){	
				tweetObj.sortHashtags();
				tweets.add(tweetObj);
			}
			/*counter++;
			if (counter > 520)
				break;*/
		}	
		System.out.println("Finish Processing file " + file);
		return tweets;
	}
	
}
