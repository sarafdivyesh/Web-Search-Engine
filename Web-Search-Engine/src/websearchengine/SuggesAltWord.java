package websearchengine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import textprocessing.In;

public class SuggesAltWord {

	static ArrayList<String> wordlist = new ArrayList<>();
	static HashMap<String, Integer> mapper = new HashMap<>();
	static int distanceAllowed = 1;
	
	// This method will suggest alternative word
	public static void suggestWord(String word) throws IOException {
		String regex = "[a-z0-9]+";
		String str = " ";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		File folder = new File("textfiles");
		File[] files = folder.listFiles();

		for (int i = 0; i < files.length; i++) {
			matchPattern(files[i], word, matcher);
		}

		int i = 0;
		for (Map.Entry entry : mapper.entrySet()) {

			i++;

			if (i == 1) {
				System.out.println("Did you mean? ");
			}

			System.out.print("(" + i + ") " + entry.getKey() + "\n");
		}
		if (i == 0) {
			System.out.println("No Alternate word found");
		}

	}

	// This method will call edit distance algorithm to find distance between pattern and text
	public static void matchPattern(File fileName, String word, Matcher matcher) throws IOException {
		String text = "";

		BufferedReader br = new BufferedReader(new FileReader(fileName));

		while ((text = br.readLine()) != null) {
			matcher.reset(text);
			while (matcher.find()) {
				wordlist.add(matcher.group());
			}
		}

		for (String str : wordlist) {
			int distance = EditDistance.editDistance(word, str);
			if (distance == distanceAllowed) {
				mapper.put(str, distance);
			}
		}

	}

}
