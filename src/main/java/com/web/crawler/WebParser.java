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

	public void parse(String httpURL) {

		URL url = null;
		URLConnection urlConnection = null;
		try {
			url = new URL(httpURL);
			urlConnection = url.openConnection();
			InputStream input = urlConnection.getInputStream();
			Document doc = Jsoup.parse(input, "UTF-8", "");
			Elements elements = doc.select("a");
			elements.forEach(anchorLink -> System.out.println(anchorLink.attr("href")));

		} catch (MalformedURLException malformedURLException) {
			malformedURLException.printStackTrace();
		} catch (IOException e) {
			throw new RuntimeException("Unable to connect to the URL", e);
		}
	}

}
