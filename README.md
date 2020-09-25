**AutoComplete**

This is an algorithm that 
 * analyzes the passages typed by the user.
 * Suggests a set of candidates of autocompleted words given the word fragment

 **How to Build and Run**
 
 The executable, `autoComplete.jar` is packaged with a .jar file. The Java SE Development Kit will be required to run the file.
 
 **Testing**
  The executable features a very simple gui with three textboxes and a button
  * The first textbox is what the algorithm will use to train the autocomplete algorithm
  * The second textbox will be the code fragment which the algorithm will use as the basis to select candidates
  * The button labeled, "Suggest," runs the algorithm based on the two strings entered in the previous textboxes
  * the third textbox will feature the resulting candidates ordered by confidence level
  
  
  
 **How the Program Works**
 
  For this program, I figured a Trie would be the best data structure as its branches allow the easiest access to all similar words that branch from that word. 
 
 In this program, there are 4 classes:
  * AutocompleteProvider
  * Candidate
  * GUI
  * Trie
  
  1. The `GUI` handles all front end operations, gathering all the strings and sending them to the `AutocompleteProvider` to be executed.
  2. In `AutocompleteProvider`, the String is split into a list using  regex and then trained by inputting each word into the `Trie` class. 
      * In `Trie`, words are inserted. If there is a duplicate, a special counter is incremented(this counter will be used to determine confidence level).
  3. To get the final string of suggestions, `Autocomplete` executes the `getWords` and `getSuggestions` methods by calling `buildCandidates` in `Trie` in order to get the list of candidates before sorting them using `Candidates`'s `.compareTo` method based around the Confidence Level. 
      * `Trie`'s `buildCandidates` method works first reaching the end of the String fragment before recursively scouring over every single branch in order to find all possible candidates. `Trie` then creates `Candidate` classes for each word and adds them to the output List. 
  4. This is then sent back to the GUI where it can be seen.
  
  
