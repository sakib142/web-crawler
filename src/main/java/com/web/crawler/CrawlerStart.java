package com.web.crawler;

import com.web.crawler.filter.CrawlerUrlFilter;
import com.web.crawler.service.CrawlerService;

public class CrawlerStart {

	public static void main(String[] args) {

		/*
		 * if(args.length < 1) { System.err.
		 * println("Provide a URL as argument to the CrawlerMainMT class.");
		 * return; }
		 */

		// String url = args[0];
		String url = "http://wiprodigital.com/";
		CrawlerService crawler = new CrawlerService(new CrawlerUrlFilter(url));
		crawler.addUrl(url);
		crawler.crawl();
	}
}
