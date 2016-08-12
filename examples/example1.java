package com.hmkcode;

import java.util.Map;
import java.util.TreeMap;

import com.puppycrawl.tools.checkstyle.gui.Main;

public class A {
	/**
	 * Some text
	 * 
	 * @param url
	 *            some url
	 * @param name
	 *            some name
	 * @return the image at the specified URL
	 * @see Image
	 */
	public Image getImage(URL url, String name) {
		try {
			return getImage(new URL(url, name));
		} catch (MalformedURLException e) {
			return null;
		}
	}
	
	// This is a comment
	public static void main(String[] args) {
		System.out.println("Hello World!");
	}
	
}

public class B {
	private int a; // Variable a

	public static Map sortByKey(Map unsortedMap) {
		Map sortedMap = new TreeMap(); // This is another comment
		sortedMap.putAll(unsortedMap); 
		/*
		 * Also a comment
		 */
		return sortedMap;
	}
}