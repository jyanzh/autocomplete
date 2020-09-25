import java.util.ArrayList;
import java.util.List;

/*	a Trie that will store all the words inputed
 * 
 * Author: James Zhou
 * */
public class Trie {
	// Alphabet size (# of symbols) 
    	private final int ALPHABET_SIZE = 26; 
  	private Trie[] children = new Trie[ALPHABET_SIZE]; 
    	private boolean isEndOfWord;
    	private int instances;

	/*
	 * constructor
	 */
    	public Trie() {
    		isEndOfWord = false;
    		instances = 0;
        
        	for (int i = 0; i < ALPHABET_SIZE; i++) {
        		children[i] = null; 
       	 	}
    	}
    
	/*
	 * recursively goes through the trie, initializing a path based on the letters.
	 */
    	public void insert(String word) 
    	{ 
    		//cuts off the first char for next recursive call
        	String nextWord = word.substring(1);
    		int index = word.charAt(0) - 'a'; 
    	
        	//creating new node if not initialized yet
        	if (children[index] == null) {
        		children[index] = new Trie();
        	}
        
        	if (nextWord.length() < 1) {
        		//if the word is completely used up, marks the node as EndOfWord
        		children[index].end(); 
        		children[index].increment();
        	} else {
        		//if word not used up, recursive call with first char cut off
        		children[index].insert(nextWord);; 
        	}
    	} 

	/*
	 * goes through the trie until the end of the inputed word is reached. Then,
	 * calls buildHelper to find all possible candidates
	 */
    	public boolean buildCandidates(ArrayList<Candidate> output, String word, int counter) 
    	{ 
    		if (word.length() <= counter) {
    			buildHelper(output, word, "", 0);
    			return true;
    		}
    		//desired character based on the counter
        	int index = word.charAt(counter) - 'a'; 
        
        	if (children[index] == null) {
        		//returns false if it reaches an empty branch
        		return false; 
        	} else if (counter == word.length()) {
        		//returns true if end of word, and the word is exhausted to length 0
        		buildHelper(output, word, "", 0);
        	return true;
        	}  else {
        		//recursive call to next to char if no conditions met
        		return children[index].buildCandidates(output, word, ++counter);
        	}
    	} 
    
	/*
	 * leaves off on the node in the trie where buildCandidates stops. searches the
	 * rest of the trie based on the node, adding to the output array all candidate
	 * words.
	 */
    	public void buildHelper(ArrayList<Candidate> output, String word, String s, int index) {
    		//searches all the branches to find any remaining words.
    		for (int i = 0; i < children.length; i++) {
    			if (children[i] != null) {
    				//if end of a word is reached, adds it into the output array of candidates.
    				if (children[i].isEndOfWord) {
    	    				output.add(new Candidate((word+s+(char)(i+'a')), children[i].instances));
    				}
    				//keeps recursively looping until it reaches a dead end to find the rest of the words
    				children[i].buildHelper(output, word, (s+(char)(i+'a')), index+1);
    			}
    		}
    	
    	}
    
    	public void end() {
    		isEndOfWord = true;
    	}
    	//increments instance by 1
    	public void increment() {
    		instances++;
    	}

} 

