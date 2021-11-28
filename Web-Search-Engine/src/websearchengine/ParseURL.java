package websearchengine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ParseURL {

	/**
	 * this method will save the html file into system
	 * 
	 * @param document
	 * @param url
	 */
	public static void saveDoc(Document document, String url) {
		try {
			//System.out.println("URL in saveDoc: "+url);
			PrintWriter html = new PrintWriter(
					new FileWriter(Path.htmlDirectoryPath + document.title().replace('/', '_') + ".html"));
			//System.out.println("Title: "+document.title());
			html.write(document.toString());
			html.close();
			htmlToText(Path.htmlDirectoryPath + document.title().replace('/', '_') + ".html", url,
					document.title() + ".txt");

		} catch (Exception e) {

		}

	}

	/**
	 * this method will save html file content to .txt file
	 * 
	 * @param htmlfile
	 * @param url
	 * @param filename
	 * @throws Exception
	 */
	public static void htmlToText(String htmlfile, String url, String filename) throws Exception {
		//System.out.println("URL in htmlToText: "+url);
		File file = new File(htmlfile);
		Document document = Jsoup.parse(file, "UTF-8");
		String data = document.text().toLowerCase();
		BufferedWriter writer = new BufferedWriter( new FileWriter(Path.txtDirectoryPath + filename));
	//	PrintWriter writer = new PrintWriter(Path.txtDirectoryPath + filename);
		writer.write(data);
		writer.close();
	}
}
