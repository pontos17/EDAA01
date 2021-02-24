package textproc;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.HashSet;

import java.util.Scanner;
import java.util.Set;



public class BookReaderApplication {
	
	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopWords = new HashSet<>() ;
		scan.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+");
		
		while(scan.hasNext()) {
			stopWords.add(scan.next().toLowerCase());
		}
		scan.close();
	
		GeneralWordCounter counter = (new GeneralWordCounter(stopWords));
		
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			counter.process(word); 
				
		}
		s.close();

		BookReaderController brc = new BookReaderController(counter);
		
	}
}
