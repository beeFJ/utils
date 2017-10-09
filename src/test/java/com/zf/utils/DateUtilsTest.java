package com.zf.utils;

import java.util.Date;

import org.junit.Test;

import static com.utils.date.DateUtil.*;

public class DateUtilsTest {

	@Test
	public void testFormatDateToString() {
		String dateStr = formatDateToString(new Date());
		String dateStr2 = formatDateToString(new Date(), "yyyy-MM-dd");
		String dateStr3 = formatDateToString(new Date(), "yyyy-MM-dd HH:mm:ss:SSS");
		System.out.println(dateStr);
		System.out.println(dateStr2);
		System.out.println(dateStr3);
	}
	
	@Test
	public void testToGMTDate() {
		String dateStr = toGMTDateString(new Date());
		System.out.println(dateStr);
	}

}
