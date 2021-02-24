package textproc;

import java.util.*;

public class MultiWordCounter<S, I> implements TextProcessor {
	private Map <String, Integer> wordCounter; 
	
	public MultiWordCounter (String[] words) {
		wordCounter = new HashMap<String,Integer> ();
		
		for (String s : words) {
			wordCounter.put(s, 1);
		}
	}

	@Override
	public void process(String w) {
		if(wordCounter.containsKey(w)) {
			wordCounter.put(w, wordCounter.get(w)+1);
		
		}
	}

	@Override
	public void report() {
		for (String w : wordCounter.keySet()) {
			System.out.println(w + ": " + wordCounter.get(w));
		}		
	}
}
