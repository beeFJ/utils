package com.utils.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	private StringUtil() {}
	
	public static boolean regMatch(String regStr, CharSequence str) {
		Pattern pattern = Pattern.compile(regStr);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
}
