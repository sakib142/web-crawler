package com.web.crawler.filter;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class CrawlerUrlFilterTest {

	@Test
	public void testWithCorrectCrawlerURLValue() {
		CrawlerUrlFilter crawlerUrlFilter = new CrawlerUrlFilter("www.test.com");
		assertEquals(true, crawlerUrlFilter.include("www.test.com"));
	}
	
	@Test
	public void testWithInCorrectCrawlerURLValue() {
		CrawlerUrlFilter crawlerUrlFilter = new CrawlerUrlFilter("www.test1.com");
		assertEquals(false, crawlerUrlFilter.include("www.test.com"));
	}

}
