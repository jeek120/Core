package com.xxfff.core.util;

import java.util.UUID;

public class UUIDUtils {
	public static String getUUID() {
		String uudi = UUID.randomUUID().toString();
		return uudi.substring(0, 8) + uudi.substring(9, 13)
				+ uudi.substring(14, 18) + uudi.substring(19, 23)
				+ uudi.substring(24);
		//return s.replace("-", "")
	}
}
