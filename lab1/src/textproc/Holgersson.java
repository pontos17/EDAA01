package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		
		long t0 = System.nanoTime();
		
		List <TextProcessor> textList = new ArrayList <TextProcessor>(); 
		
//		textList.add(new SingleWordCounter("nils")); 
//		textList.add(new SingleWordCounter("norge")); 
//		textList.add(new MultiWordCounter<String, Integer> (REGIONS)); 
		
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopWords = new HashSet<>();
		scan.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+");
		
		while(scan.hasNext()) {
			stopWords.add(scan.next().toLowerCase());
		}
		scan.close();
		textList.add( new GeneralWordCounter(stopWords));
		
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			for (TextProcessor p : textList) { 
					p.process(word);
			}
		}
		s.close();
			for (TextProcessor p : textList) {
				p.report();
			}
			long t1 = System.nanoTime();
			System.out.println("tid: " + (t1-t0)/1000000.0 + " ms");
			/** 757... ms var median värdet*/ 
			/** 840... ms om man ändrade till TreeMap*/
	}
}