package entities;

public class EdgeObj {

	private String hashtag1;
	public String getHashtag1() {
		return hashtag1;
	}
	public void setHashtag1(String hashtag1) {
		this.hashtag1 = hashtag1;
	}
	private String hashtag2;
	public String getHashtag2() {
		return hashtag2;
	}
	public void setHashtag2(String hashtag2) {
		this.hashtag2 = hashtag2;
	}
	private int weight = 0;
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public void increamentWeight() {
		weight++;
	}
	public boolean equals(EdgeObj ed)
	{
		if((ed.getHashtag1().equals(hashtag1) && ed.getHashtag2().equals(hashtag2)) || (ed.getHashtag1().equals(hashtag2) && ed.getHashtag2().equals(hashtag1)))
			return true;
		
		return false;
	}
}
