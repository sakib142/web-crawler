package com.web.crawler.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.web.crawler.filter.CrawlerFilter;
import com.web.crawler.util.SiteMapData;

public class CrawlerService {

	private CrawlerFilter urlFilter;
	private Set<String> crawledUrls;
	private ExecutorService crawlService;
	protected final LinkedBlockingQueue<String> linksQueue;
	protected CyclicBarrier barrier;
	private List<SiteMapData> data;

	public CrawlerService(CrawlerFilter urlFilter) {
		this.urlFilter = urlFilter;
		crawledUrls = new HashSet<String>();
		linksQueue = new LinkedBlockingQueue<>();
		// create thread pool
		crawlService = Executors.newCachedThreadPool();
		data = new CopyOnWriteArrayList<SiteMapData>();
		barrier = new CyclicBarrier(2);
	}

	public void addUrl(String url) {
		linksQueue.add(url);
	}

	public void crawl() {

		while (!linksQueue.isEmpty()) {
			String nextUrl = null;
			try {
				nextUrl = linksQueue.take();

				if (nextUrl == null)
					System.out.println("queue is null here");

				if (!shouldCrawlUrl(nextUrl))
					continue; // skip this URL.

				this.crawledUrls.add(nextUrl);

				CrawlerWebParserJob crawlJob = new CrawlerWebParserJob(nextUrl, this, data);
				crawlService.submit(crawlJob);

				if (linksQueue.isEmpty()) {
					barrier.await();
				}

			} catch (InterruptedException | BrokenBarrierException e) {
				System.out.println("Error crawling URL: " + nextUrl);
			}

		}

		crawlService.shutdown();

		try {
			// wait until all threads have ended
			crawlService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException ex) {
			Logger.getLogger(CrawlerService.class.getName()).log(Level.SEVERE, null, ex);
		}

		String siteMapData = new Gson().toJson(data);
		String workingDir = System.getProperty("user.dir");
		String siteMapDir = workingDir + "crawlerSiteMap.json";
		Logger.getLogger(CrawlerService.class.getName()).log(Level.INFO,
				"Crawler Structured site map file location ------->" + siteMapDir);

		try (FileWriter file = new FileWriter(siteMapDir)) {
			file.write(siteMapData.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private boolean shouldCrawlUrl(String nextUrl) {
		if (this.urlFilter != null && !this.urlFilter.include(nextUrl)) {
			return false;
		}
		if (this.crawledUrls.contains(nextUrl)) {
			return false;
		}
		if (nextUrl.startsWith("javascript:")) {
			return false;
		}
		if (nextUrl.startsWith("#")) {
			return false;
		}
		if (nextUrl.endsWith(".swf")) {
			return false;
		}
		if (nextUrl.endsWith(".pdf")) {
			return false;
		}
		if (nextUrl.endsWith(".png")) {
			return false;
		}
		if (nextUrl.endsWith(".gif")) {
			return false;
		}
		if (nextUrl.endsWith(".jpg")) {
			return false;
		}
		if (nextUrl.endsWith(".jpeg")) {
			return false;
		}

		return true;
	}

}
