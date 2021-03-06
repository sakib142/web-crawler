package com.web.crawler.filter;

public class CrawlerUrlFilter implements CrawlerFilter {

	private String domainUrl = null;

	public CrawlerUrlFilter(String domainUrl) {
		this.domainUrl = domainUrl;
	}

	@Override
	public boolean include(String url) {
		return url.startsWith(this.domainUrl);
	}
}
