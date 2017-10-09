package com.utils.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
	
	/**
	 * 将Date类表示的时间转化成yyyy-MM-dd HH:mm:ss形式的字符串
	 * @param date 待转化的Date时间对象
	 * @return 转化后的时间字符串
	 */
	public static String formatDateToString(Date date) {
		return formatDateToString(date, null);
	}
	
	/**
	 * 将Date类表示的时间转化成特定格式的字符串
	 * @param date 待转化的Date时间对象
	 * @param pattern 字符串的格式
	 * @return 转化后的时间字符串
	 */
	public static String formatDateToString(Date date, String pattern) {
		if(null == date) {
			return null;
		}
		if(pattern == null || "".equals(pattern)) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		
		DateFormat df = new SimpleDateFormat(pattern);
		return df.format(date);
	}
	
	/**
	 * 获取当前系统时间戳
	 * @return 当前系统时间戳
	 */
	public static long getCurrentTimeStamp() {
		return getTimeStamp(new Date());
	}
	
	/**
	 * 获取特定时间的时间戳
	 * @param date 特定的时间Date对象
	 * @return 该特定Date对象的时间戳
	 */
	public static long getTimeStamp(Date date) {
		return date.getTime() / 1000;
	}
	
	/**
	 * 将CST时间转化成GMT时间
	 * @param cstDate 待转化的CST时间
	 * @return 转化后的GMT时间
	 */
	public static String toGMTDateString(Date cstDate) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		String gmtDateStr = df.format(cstDate);
		return gmtDateStr;
	}
	
}
