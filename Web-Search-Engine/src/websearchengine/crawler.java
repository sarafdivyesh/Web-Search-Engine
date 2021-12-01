package websearchengine;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class crawler {

	static int flag = 0;
	static int temp = 0;
	private static Set<String> crawlerList1 = new HashSet<String>();
	private static Set<String> crawlerList2 = new HashSet<String>();
	private static String regex = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)";

	public static void startCrawler(String url, BufferedWriter writer) throws IOException {
		Pattern patternObj = Pattern.compile(regex);
		Document document = Jsoup.connect(url).get();
		if (flag == 0) {
			ParseURL.saveDoc(document, url);
			writer.write(document.title().trim() + ":::" + url);
			writer.newLine();
			flag++;
		}
		Elements hyperlinks = document.select("a[href]");

		for (Element link : hyperlinks) {
			if (verify_Url(link.attr("abs:href")) && patternObj.matcher(link.attr("href")).find() && temp <= 100) {
				System.out.println(link.attr("abs:href"));
				document = Jsoup.connect(link.attr("abs:href")).get();
				writer.write(document.title().trim() + ":::" + link.attr("abs:href"));
				writer.newLine();
				ParseURL.saveDoc(document, link.attr("abs:href"));
				crawlerList1.add(link.attr("abs:href"));
				temp++;
			}
			if (temp == 100) {
				break;
			}
		}
	}

	// This method verify all URL's fetched by crawler 
	private static boolean verify_Url(String URL) {
		if (crawlerList1.contains(URL)) {
			return false;
		}
		if (URL.endsWith(".jpeg") || URL.endsWith(".jpg") || URL.endsWith(".png") || URL.endsWith(".pdf")
				|| URL.contains("#") || URL.contains("?") || URL.contains("mailto:") || URL.startsWith("javascript:")
				|| URL.endsWith(".gif") || URL.endsWith(".xml")) {
			return false;
		}
		return true;
	}

	// This methods kick start the web crawler
	public static void triggerCrawler(String URL) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("website.txt"));
		startCrawler(URL, writer);
		Set<String> crawlerList2 = new HashSet<String>(crawlerList1);
		for (String str : crawlerList2) {
			startCrawler(str, writer);
		}
		writer.close();
	}
}
