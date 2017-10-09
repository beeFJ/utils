package com.zf.utils;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.utils.io.FileUtil;

public class FileUtilTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetSubDirectories() {
		List<File> fileList = FileUtil.getSubDirectories("c:"+FileUtil.separator+"develop");
		for (File file : fileList) {
			try {
				System.out.println(file.getCanonicalPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testGetDirectoryFiles() {
		List<File> fileList = FileUtil.getDirectoryFiles("c:" + FileUtil.separator + "develop");
		for (File file : fileList) {
			try {
				System.out.println(file.getCanonicalPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
