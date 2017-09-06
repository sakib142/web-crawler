package com.web.crawler;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WebParser {

	public void parseHtmlFile(String httpURL) {

		URL url = null;
		URLConnection urlConnection = null;
		try {

			url = new URL(httpURL);
			urlConnection = url.openConnection();
			InputStream input = urlConnection.getInputStream();
			Document doc = Jsoup.parse(input, "UTF-8", "");
			parse(doc, "a", "href");
			parse(doc, "img", "src");

		} catch (MalformedURLException malformedURLException) {
			malformedURLException.printStackTrace();
		} catch (IOException e) {
			throw new RuntimeException("Unable to connect to the URL", e);
		}
	}

	private void parse(Document doc, String tagName, String tagAttribute) {
		Elements elements = doc.select(tagName);
		elements.forEach(anchorLink -> System.out.println(anchorLink.attr(tagAttribute)));
	}
}
