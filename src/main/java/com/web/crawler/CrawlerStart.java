package com.web.crawler;

import com.web.crawler.filter.CrawlerUrlFilter;
import com.web.crawler.service.CrawlerService;

public class CrawlerStart {

	public static void main(String[] args) {

		String url = "http://wiprodigital.com/";
		CrawlerService crawler = new CrawlerService(new CrawlerUrlFilter(url));
		crawler.addUrl(url);
		crawler.crawl();
	}
}
