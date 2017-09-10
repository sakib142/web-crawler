package com.web.crawler.util;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

public class TagAttributesInfoTest {

	@Test
	public void testSetter_setsProperly() throws NoSuchFieldException, IllegalAccessException {

		final TagAttributesInfo tagAttribute = new TagAttributesInfo();
		final Field attributes = tagAttribute.getClass().getDeclaredField("attributes");
		final Field tagMap = tagAttribute.getClass().getDeclaredField("tagMap");

		List<String> attributeList = new ArrayList<>();
		attributeList.add("href");

		Map<Integer, String> tagMapVal = new HashMap<>();
		tagMapVal.put(1, "a");

		tagAttribute.setAttributes(attributeList);
		tagAttribute.setTagMap(tagMapVal);

		attributes.setAccessible(true);
		tagMap.setAccessible(true);

		assertEquals(attributes.get(tagAttribute), attributeList);
		assertEquals(tagMap.get(tagAttribute), tagMapVal);

	}

	@Test
	public void testGetter_getsValue() throws NoSuchFieldException, IllegalAccessException {

		final TagAttributesInfo tagAttribute = new TagAttributesInfo();

		final Field attributes = tagAttribute.getClass().getDeclaredField("attributes");
		final Field tagMap = tagAttribute.getClass().getDeclaredField("tagMap");

		List<String> attributeList = new ArrayList<>();
		attributeList.add("href");
		tagAttribute.setAttributes(attributeList);

		Map<Integer, String> tagMapVal = new HashMap<>();
		tagMapVal.put(1, "a");
		tagAttribute.setTagMap(tagMapVal);

		attributes.setAccessible(true);
		tagMap.setAccessible(true);

		attributes.set(tagAttribute, attributeList);
		tagMap.set(tagAttribute, tagMapVal);

		assertEquals(tagAttribute.getAttributes(), attributeList);
		assertEquals(tagAttribute.getTagMap(), tagMapVal);

	}

}
