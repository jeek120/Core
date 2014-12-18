package com.xxfff.core.util;

public class StringUtils extends org.apache.commons.lang.StringUtils {
	public static String toFirstUpperCase(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public static boolean isInteger(String s) {
		return s.matches("\\d+");
	}
}
