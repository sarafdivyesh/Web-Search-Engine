package websearchengine;

import static java.util.stream.Collectors.toMap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.Set;
import java.util.StringTokenizer;

import textprocessing.In;
import textprocessing.TST;

public class SearchEngine {

	static int flag = 0;
	
	// This method will map title of the document to its corresponding URL
	public static HashMap<String, String> fileMapper() {
		int i = 0;
		HashMap<String, String> UrlIndex = new HashMap<String, String>();
		In in = new In("website.txt");
		while (!in.isEmpty()) {
			String text = in.readLine();
			String textSplit[] = text.split(":::");
			System.out.println(textSplit[0] + " " + textSplit[1]);
			UrlIndex.put(textSplit[0].trim(), textSplit[1].trim());
		}
		return UrlIndex;
	}
	
	// This method will generate TST tree of each of the text file with word frequency
	public static TST<Integer> getTST(String finalPath) {
		String regex = "[a-zA-Z0-9]+";
		String str = " ";
		String word = "";

		int j = 0;
		TST<Integer> tst = new TST<Integer>();
		In in = new In(finalPath);

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);

		while (!in.isEmpty()) {
			String text = in.readLine();
			StringTokenizer st = new StringTokenizer(text, " ");
			while (st.hasMoreTokens()) {
				str = st.nextToken();
				matcher.reset(str);
				while (matcher.find()) {
					word = matcher.group().toLowerCase();

					if (tst.contains(word)) {

						tst.put(word, tst.get(word) + 1);

					} else {

						tst.put(word, 1);
					}
				}
			}
		}

		return tst;
	}

	// This method will return hashmap containing frequency list of words in given files  
	public static HashMap<String, Integer> getFreqList(String word) {

		flag = 0;

		ArrayList<String> textList = new ArrayList<>();
		HashMap<String, Integer> freqList = new HashMap<String, Integer>();
		

		File folder = new File("textfiles");
		File[] files = folder.listFiles();

		for (int i = 0; i < files.length; i++) {

			String filePath = "textfiles/";
			String fileName = files[i].getName();
			String finalPath = filePath + fileName;

	
			TST<Integer> tst = new TST<Integer>();
			tst = getTST(finalPath);
		

			int counter = 0;

			if (tst.contains(word)) {
				flag++;
				int count = tst.get(word);
				counter = counter + count;
			}

			freqList.put(fileName, counter);
		}

		return freqList;
	}
	
	// This method checks the validity of URL
	public static String isValid(String URL) {
		try {
			System.out.println("Verfying the URL");
			URL obj = new URL(URL);
			HttpURLConnection CON = (HttpURLConnection) obj.openConnection();
			CON.setRequestMethod("GET");
			int response = CON.getResponseCode();
			if (response == 200) {
				return "Yes";

			} else {
				return "No";
			}

		}

		catch (Exception e) {
			return "No";
		}
	}
	
	//This methods helps in sorting the hashmap to identify ranking of pages
	public static HashMap<String, Integer> sortHashMap(HashMap<String, Integer> freqList) {
		HashMap<String, Integer> sortedFreqList = freqList.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
				.collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));

		return sortedFreqList;
	}
	
	// This method will search word in all text files
	public static void searchWord() throws IOException {
		SuggesAltWord suggest = new SuggesAltWord();
		Scanner sc = new Scanner(System.in);
		HashMap<String, String> hash = fileMapper();
		String choice1 = "y";
		do {
			System.out.println("\n*************************************************************************\n");
			System.out.println("Enter a Word/String to be searched on web site");
			String str = sc.next();
			System.out.println("\n*************************************************************************");
			HashMap<String, Integer> freq = getFreqList(str);
			if (flag == 0) {
				System.out.println("Word is not found in any of the files\n");
				System.out.println("Suggesting similar words.....");
				suggest.suggestWord(str);
			} else {
				System.out.println(
						"----------------------Following are the Web pages contain the word-------------------");

				for (Entry<String, Integer> entry : freq.entrySet()) {
					String key = entry.getKey();
					int value = entry.getValue();
					if (value >= 1) {
						String hyperlink = hash.get(key);
						if (hyperlink != null) {
							System.out.println(hyperlink + " => " + entry.getValue());
						}
					}
				}

				HashMap<String, Integer> sortedFreqList = sortHashMap(freq);

				int value = 0;
				int k = 0;
				System.out.println(
						"\n\n---------------Top 5 Web pages having highest frequency of word-------------------");
				for (Entry<String, Integer> entry : sortedFreqList.entrySet()) {
					if ((value = entry.getValue()) != 0 && hash.get(entry.getKey()) != null && k < 5) {
						System.out.println(hash.get(entry.getKey()) + " => " + entry.getValue());
					}
					k++;
				}
			}
		} while (choice1.equals("y"));
	}

	// Main method to run the program
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		String choice = "n";
		System.out.println("\n*************************************************************************\n");
		System.out.println("                       Welcome to Web Search Engine                      ");
		System.out.println("\n*************************************************************************\n");
		System.out.println("                               Team Members                              \n");
		System.out.println("                 Abhinav Mehta, Divyesh Saraf,                ");
		System.out.println("                 Karan Rawal, Varinder Babool                 ");
		System.out.println("\n*************************************************************************\n");
		do {
			System.out.println("                    select the option mentioned below                   \n ");
			System.out.println("*************************************************************************\n");
			System.out.println(" 1) Enter 1 for the Web search from the URL your choice");
			System.out.println(" 2) Enter 2 for the Web search from static URL (https://www.javatpoint.com/)");
			System.out.println(" 3) Enter 3 for Exit ");
			System.out.println("\n*************************************************************************\n");
			int SelectOption = sc.nextInt();

			switch (SelectOption) {
			case 1:
				System.out.println("\n Please enter valid URL in these 'www.abc.com' format");
				String URL = sc.next();
				URL = "https://" + URL + "/";
				choice = isValid(URL);
				if (choice == "Yes") {
					System.out.println("Enterd URL " + URL + " is valid\n");
					System.out.println("Crwaling started....");
					Crawler.triggerCrawler(URL);
					System.out.println("Crwaling finished....");
					searchWord();
				} else {
					System.out.println("Enterd URL " + URL + " is not again valid, Please try again\n");
					choice = "Yes";
				}
				break;

			case 2:
				choice = isValid("https://www.javatpoint.com/");
				if (choice == "Yes") {
					System.out.println("Enterd URL https://www.javatpoint.com/ is valid\n");
					System.out.println("Crwaling started....");
					Crawler.triggerCrawler("https://www.javatpoint.com/");
					System.out.println("Crwaling finished....");
					searchWord();
				} else {
					System.out.println("Enterd URL https://www.javatpoint.com/ is not again valid, Please try again\n");
					choice = "Yes";
				}
				break;

			case 3:
				System.out.println("Exit...");
				choice = "n";
				break;

			default:
				System.out.println("Please enter correct option");
				choice = "y";

			}
		} while (choice.equals("y"));

	}
}
