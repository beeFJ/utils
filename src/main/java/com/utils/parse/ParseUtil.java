package com.utils.parse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ParseUtil {

	/**
	 * 获取properties文件中key对应的值
	 * @param filePath properties文件的路径
	 * @param key properties文件中的某个key值
	 * @return properties文件中参数key所对应的value值
	 * @throws IOException 
	 */
	public String getPropertiesValue(String filePath, String key) throws IOException {
		InputStream in = this.getClass().getResourceAsStream(filePath);
		Properties prop = new Properties();
		prop.load(in);
		String value = prop.getProperty(key);
		in.close();
		return value;
	}
	
}
