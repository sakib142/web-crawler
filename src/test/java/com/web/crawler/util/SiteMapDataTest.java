package com.web.crawler.util;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

public class SiteMapDataTest {

	@Test
	public void testSetter_setsProperly() throws NoSuchFieldException, IllegalAccessException {
		final SiteMapData siteMapData = new SiteMapData();

		final Field nodeUrl = siteMapData.getClass().getDeclaredField("nodeUrl");
		final Field linkData = siteMapData.getClass().getDeclaredField("linkData");
		final Field imgData = siteMapData.getClass().getDeclaredField("imgData");

		siteMapData.setNodeUrl("www.url.com");

		Map<Integer, String> linkDataMap = new HashMap<>();
		linkDataMap.put(1, "test");
		siteMapData.setLinkData(linkDataMap);

		Map<Integer, String> imgDataMap = new HashMap<>();
		imgDataMap.put(2, "test");
		siteMapData.setImgData(imgDataMap);

		nodeUrl.setAccessible(true);
		linkData.setAccessible(true);
		imgData.setAccessible(true);
		
		assertEquals(nodeUrl.get(siteMapData), "www.url.com");
		assertEquals(linkData.get(siteMapData), linkDataMap);
		assertEquals(imgData.get(siteMapData), imgDataMap);

	}

	@Test
	public void testGetter_getsValue() throws NoSuchFieldException, IllegalAccessException {
		// given
		final SiteMapData siteMapData = new SiteMapData();

		final Field nodeUrl = siteMapData.getClass().getDeclaredField("nodeUrl");
		final Field linkData = siteMapData.getClass().getDeclaredField("linkData");
		final Field imgData = siteMapData.getClass().getDeclaredField("imgData");

		Map<Integer, String> linkDataMap = new HashMap<>();
		linkDataMap.put(1, "test");
		siteMapData.setLinkData(linkDataMap);

		Map<Integer, String> imgDataMap = new HashMap<>();
		imgDataMap.put(2, "test");
		siteMapData.setImgData(imgDataMap);

		nodeUrl.setAccessible(true);
		linkData.setAccessible(true);
		imgData.setAccessible(true);

		nodeUrl.set(siteMapData, "www.url.com");
		linkData.set(siteMapData, linkDataMap);
		imgData.set(siteMapData, imgDataMap);

		// then
		assertEquals(siteMapData.getNodeUrl(), "www.url.com");
		assertEquals(siteMapData.getImgData(), imgDataMap);
		assertEquals(siteMapData.getLinkData(), linkDataMap);

	}

}
