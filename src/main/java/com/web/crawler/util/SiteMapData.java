package com.web.crawler.util;

import java.io.Serializable;
import java.util.Map;

public class SiteMapData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nodeUrl;
	
	private Map<Integer, String> linkData;
	
	private Map<Integer, String> imgData;

	public String getNodeUrl() {
		return nodeUrl;
	}

	public void setNodeUrl(String nodeUrl) {
		this.nodeUrl = nodeUrl;
	}

	public Map<Integer, String> getLinkData() {
		return linkData;
	}

	public void setLinkData(Map<Integer, String> linkData) {
		this.linkData = linkData;
	}

	public Map<Integer, String> getImgData() {
		return imgData;
	}

	public void setImgData(Map<Integer, String> imgData) {
		this.imgData = imgData;
	}

	@Override
	public String toString() {
		return "SiteMapData [nodeUrl=" + nodeUrl + ", linkData=" + linkData + ", imgData=" + imgData + "]";
	}
	
	

}
