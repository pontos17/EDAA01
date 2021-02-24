package textproc;

import java.util.*;


public class GeneralWordCounter implements TextProcessor {
	private Set<String> stopWords; 
	private Map <String, Integer> wordCount; 
	
	public GeneralWordCounter (Set<String> gwd){
		wordCount = new HashMap<String, Integer>(); 
		stopWords = gwd; 
	}

	@Override
	public void process(String w) {
		
		if(stopWords.contains(w)) {
				return;
		}
		if(wordCount.containsKey(w)) {
			wordCount.put(w, wordCount.get(w)+1);
		} else {
			wordCount.put(w, 1);
		}
	}

	@Override
	public void report() {
		
		/** for (String w : wordCount.keySet()) {
			if (wordCount.get(w) > 200) {
				System.out.println(w + ": " + wordCount.get(w));
			}
		}	*/
		Set<Map.Entry<String, Integer>> wordSet = wordCount.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<> (wordSet);
		wordList.sort(new WordCountComparator());
		
		for(int i = 0; i < 20; i++) {
			System.out.println(wordList.get(i).getKey() + ": " + wordList.get(i).getValue()); 
		}
		
		/*for (String p : wordCount.keySet()) {
			if (wordCount.get(p) > 200) {
				System.out.println(p + " : " + wordCount.get(p));
			}*/
		}
	
	public List<Map.Entry<String, Integer>> getWordList(){
		return new ArrayList<>(wordCount.entrySet());
	}

		
	}


