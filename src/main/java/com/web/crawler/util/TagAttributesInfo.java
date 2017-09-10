package com.web.crawler.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class TagAttributesInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<String> attributes;

	private Map<Integer, String> tagMap;

	public List<String> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<String> attributes) {
		this.attributes = attributes;
	}

	public Map<Integer, String> getTagMap() {
		return tagMap;
	}

	public void setTagMap(Map<Integer, String> tagMap) {
		this.tagMap = tagMap;
	}

}
