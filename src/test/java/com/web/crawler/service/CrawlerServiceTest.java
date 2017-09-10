package com.web.crawler.service;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CrawlerServiceTest {

	public ExecutorService executor;

	@Mock
	CyclicBarrier barrier;
	
	@InjectMocks
	private CrawlerService crawlerService;

	@BeforeClass
	public void beforeClass() {
		MockitoAnnotations.initMocks(this);

	}

	@BeforeMethod
	public void beforeMethod() {
		executor = Executors.newFixedThreadPool(2);
		crawlerService.addUrl("www.test.com");
		/*List<SiteMapData> siteMapData = new ArrayList<>();
		CrawlerWebParserJob callable = new CrawlerWebParserJob("", crawlerService, siteMapData);
		Mockito.when(executor.submit(callable)).thenThrow(new RuntimeException());*/
		try {
			Mockito.when(barrier.await()).thenReturn(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRun() {
		//crawlerService.crawl();
		
	}

}
