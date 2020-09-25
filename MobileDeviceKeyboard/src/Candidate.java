/*
 * This class creates the candidate that will hold a word and 
 * a confidence value
 * 
 * Author: James Zhou
 * */
public class Candidate implements Comparable<Candidate>{
	private String word;
	private Integer confidence;

	//constructor
	public Candidate(String w, Integer c) {
		word = w;
		confidence = c;
	}
	
	//returns the autocomplete candidate
	public String getWord() {
		return word;
	}
	
	//returns the confidence* for the candidate
	public Integer getConfidence() {
		return confidence;
	}
	
	@Override
	public String toString() {
		return word+ " (" +confidence+")";
	}
	
	//Allows candidates to be sorted based on their confidence
	public int compareTo(Candidate other) {
		return other.confidence - this.confidence;
	}
}
