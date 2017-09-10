package com.web.crawler.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.stream.IntStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.web.crawler.filter.UrlNormalizer;
import com.web.crawler.util.SiteMapData;
import com.web.crawler.util.TagAttributesInfo;

public class CrawlerWebParserJob implements Runnable {

	protected CrawlerService crawler = null;
	protected String urlToCrawl = null;
	protected List<SiteMapData> siteMapData = null;

	public CrawlerWebParserJob(String urlToCrawl, CrawlerService crawler, List<SiteMapData> siteMapData) {
		this.urlToCrawl = urlToCrawl;
		this.crawler = crawler;
		this.siteMapData = siteMapData;
	}

	@Override
	public void run() {
		try {
			parseHtmlFile();
		} catch (Exception ex) {

		}
	}

	public void parseHtmlFile() {

		URL url = null;
		URLConnection urlConnection = null;
		try {

			url = new URL(this.urlToCrawl);
			urlConnection = url.openConnection();
			InputStream input = urlConnection.getInputStream();
			Document doc = Jsoup.parse(input, "UTF-8", "");
			String baseUrl = url.toExternalForm();

			Map<String, TagAttributesInfo> map = new HashMap<String, TagAttributesInfo>();

				List<String> anchorAttrs = new ArrayList<String>();
				anchorAttrs.add("href");
				map.put("a", this.getAttributeInfo(anchorAttrs));

				List<String> imgAttrs = new ArrayList<String>();
				imgAttrs.add("src");
				map.put("img", this.getAttributeInfo(imgAttrs));

			SiteMapData data = new SiteMapData();
			data.setNodeUrl(baseUrl);

			parse(doc, map, baseUrl);

			data.setLinkData(map.get("a").getTagMap());
			data.setImgData(map.get("img").getTagMap());
			siteMapData.add(data);

			if (crawler.barrier.getNumberWaiting() == 1) {
				crawler.barrier.await();
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		} catch (IOException e) {
			throw new RuntimeException("Unable to connect to the URL", e);
		}
	}

	private void parse(Document doc, Map<String, TagAttributesInfo> htmlTagAttributeMap, String baseURL) {

		htmlTagAttributeMap.forEach((tag, attributeInfo) -> {

			Elements elements = doc.select(tag);
			attributeInfo.getAttributes().forEach(attribute -> {

				IntStream.range(0, elements.size()).forEach(index -> {
					String linkUrl = elements.get(index).attr(attribute);
					String normalizedUrl = UrlNormalizer.normalize(linkUrl, baseURL);

					try {
						if (tag.equalsIgnoreCase("a")) {
							crawler.linksQueue.put(normalizedUrl);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(" - " + normalizedUrl);
					attributeInfo.getTagMap().put(index, normalizedUrl);
				});
			}

			);

		});
	}

	private TagAttributesInfo getAttributeInfo(List<String> attr) {

		TagAttributesInfo tagInfo = new TagAttributesInfo();

		ArrayList<String> tagAttributeList = new ArrayList<>();
		attr.forEach(attrInfo -> {
			tagAttributeList.add(attrInfo);
		});
		Map<Integer, String> tagContentMap = new HashMap<Integer, String>();

		tagInfo.setAttributes(tagAttributeList);
		tagInfo.setTagMap(tagContentMap);

		return tagInfo;
	}

	private void parse1(Document doc, String tagName, String tagAttribute, String baseURL) {
		Elements elements = doc.select(tagName);

		elements.forEach(anchorLink -> {
			String linkUrl = anchorLink.attr(tagAttribute);
			String normalizedUrl = UrlNormalizer.normalize(linkUrl, baseURL);
			try {
				if (tagName.equalsIgnoreCase("a")) {
					crawler.linksQueue.put(normalizedUrl);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(" - " + normalizedUrl);
		});

	}

}
