package com.web.crawler.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.web.crawler.filter.CrawlerUrlFilter;
import com.web.crawler.util.SiteMapData;

public class CrawlerWebParserJobTest {

	@InjectMocks
	private CrawlerWebParserJob crawlerWebParserJob;

	@BeforeClass
	public void beforeClass() {
		MockitoAnnotations.initMocks(this);

	}

	@BeforeMethod
	public void beforeMethod() {

	}

	@Test(expectedExceptions = { MalformedURLException.class, IOException.class, RuntimeException.class })
	public void testExceptionThrownWhenWrongURLused() {
		String domainUrl = "www.url.com";
		CrawlerUrlFilter crawlerUrlFilter = new CrawlerUrlFilter(domainUrl);
		CrawlerService crawler = new CrawlerService(crawlerUrlFilter);
		List<SiteMapData> siteMapData = new ArrayList<>();
		crawlerWebParserJob = new CrawlerWebParserJob(domainUrl, crawler, siteMapData);
		crawlerWebParserJob.parseHtmlFile();

	}

	@Test
	public void testWithCorrectURL() {
		String domainUrl = "http://wiprodigital.com/";
		CrawlerUrlFilter crawlerUrlFilter = new CrawlerUrlFilter(domainUrl);
		CrawlerService crawler = new CrawlerService(crawlerUrlFilter);
		List<SiteMapData> siteMapData = new ArrayList<>();
		crawlerWebParserJob = new CrawlerWebParserJob(domainUrl, crawler, siteMapData);
		crawlerWebParserJob.parseHtmlFile();

	}

}
