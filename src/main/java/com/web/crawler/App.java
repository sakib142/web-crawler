package com.web.crawler;

public class App {
	public static void main(String[] args) {
		WebParser webParser = new WebParser();
		webParser.parseHtmlFile("http://wiprodigital.com");
	}
}
