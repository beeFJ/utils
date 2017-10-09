package com.utils.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FileUtil {
	
	/**
	 * 当前操作系统下的命名分隔符
	 */
	public final static String separator = File.separator;
	
	/**
	 * 得到当前目录下的子目录列表
	 * @return
	 */
	public static List<File> getSubDirectories(String pathname) {
		List<File> dirList = new ArrayList<File>();
		putDirToList(dirList, pathname);
		return dirList;
	}
	
	/**
	 * 获取当前目录及其子目录下的所有文件
	 * @return
	 */
	public static List<File> getDirectoryFiles(String pathname) {
		List<File> fileList = new ArrayList<File>();
		putFileToList(fileList, pathname);
		return fileList;
	}
	
	private static void putDirToList(List<File> dirList, String pathname) {
		File file = new File(pathname);
		String[] fileNames = file.list();
		for (String fileName : fileNames) {
			File subFile = new File(file.getPath(), fileName);
			if(subFile.isDirectory()) {
				dirList.add(subFile);
				putDirToList(dirList, file.getPath()+FileUtil.separator+fileName);
			}
		}
	}
	
	private static void putFileToList(List<File> dirList, String pathname) {
		File file = new File(pathname);
		String[] fileNames = file.list();
		for (String fileName : fileNames) {
			File subFile = new File(file.getPath(), fileName);
			if(subFile.isFile()) {
				dirList.add(subFile);
			}
			if(subFile.isDirectory()) {
				putFileToList(dirList, file.getPath() + FileUtil.separator + fileName);
			}
		}
	}
	
}
