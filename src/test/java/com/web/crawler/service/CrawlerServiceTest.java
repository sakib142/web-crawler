package com.web.crawler.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.web.crawler.filter.CrawlerUrlFilter;
import com.web.crawler.util.SiteMapData;

public class CrawlerServiceTest {

	@Mock
	public ExecutorService executor;

	@Mock
	CyclicBarrier barrier;

	@Mock
	LinkedBlockingQueue<String> linksQueue;
	
	@Mock
	CrawlerUrlFilter crawlerUrlFIlter;

	@InjectMocks
	private CrawlerService crawlerService = new CrawlerService(crawlerUrlFIlter);

	@BeforeClass
	public void beforeClass() {
		MockitoAnnotations.initMocks(this);

	}

	@BeforeMethod
	public void beforeMethod() {
	    crawlerService.addUrl("www.test.com");
		List<SiteMapData> siteMapData = new ArrayList<>();
		CrawlerWebParserJob callable = new CrawlerWebParserJob("", crawlerService, siteMapData);
		Mockito.when(executor.submit(callable)).thenReturn(null);

	}

	@Test
	public void testRun() {
		try {
			Mockito.when(linksQueue.isEmpty()).thenReturn(false);
			Mockito.when(barrier.await()).thenThrow(new RuntimeException());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		crawlerService.crawl();

	}

}
