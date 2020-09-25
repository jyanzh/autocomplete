import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* This class runs the Trie data structure. 
 * it takes in a passage and has methods to train
 * the trie as well as sort out the candidates.
 * 
 * Author: James Zhou
 * */

public class AutocompleteProvider {
	private ArrayList<Candidate> output;
	private ArrayList<String> temp;
	private Trie root = new Trie();
	
	//constructor
	public AutocompleteProvider() {
		output = new ArrayList<Candidate>();
		temp = new ArrayList<String>();
	}
	
	//trains the algorithm with the provided passage
	public void train(String passage) {
		List<String> allMatches = new ArrayList<String>();
		
		//using a regular expression to put all the words into a list
		Matcher m = Pattern.compile("[a-zA-z]+").matcher(passage);
		while (m.find()) {
		  allMatches.add(m.group().toLowerCase());
		}
		
		//inserts all the words found into the trie
		for (int i = 0; i < allMatches.size(); i++) {
			root.insert(allMatches.get(i));
		}
	}
	
	//returns list of candidates ordered by confidence
	private List<Candidate> getWords(String fragment) {
		root.buildCandidates(output, fragment, 0);
		Collections.sort(output);
		return output;
	}
	
	//returns a string of all the candidates in order of confidence
    public String getSuggestions(String input) {
    	String temp = "";
    	List<Candidate> words = getWords(input);
    	int i = 0;
    	temp = "";
    	
    	while (i < words.size()) {
			temp = temp +words.get(i).toString()+ ", ";
			i++;
		}
    	
    	return temp;
    }

}
