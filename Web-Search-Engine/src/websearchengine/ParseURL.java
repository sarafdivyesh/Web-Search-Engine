package websearchengine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ParseURL {

	// This method will save HTML file into local system
	public static void saveDoc(Document document, String url) {
		try {
			PrintWriter html = new PrintWriter(
					new FileWriter("webFiles/"+ document.title().replace('/', '_') + ".html"));
			html.write(document.toString());
			html.close();
			htmlToText("webFiles/" + document.title().replace('/', '_') + ".html", url, document.title());

		} catch (Exception e) {

		}

	}

	// This method will convert HTML to text and store text files to local system
	public static void htmlToText(String htmlfile, String url, String filename) throws Exception {
		
		File file = new File(htmlfile);
		Document document = Jsoup.parse(file, "UTF-8");
		String data = document.text().toLowerCase();
		BufferedWriter writer = new BufferedWriter(new FileWriter("textfiles/" + filename));
		writer.write(data);
		writer.close();
	}
}
