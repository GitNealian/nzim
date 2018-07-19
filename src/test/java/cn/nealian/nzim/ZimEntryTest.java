package cn.nealian.nzim;

import java.io.IOException;

import org.junit.Test;

import cn.nealian.nzim.ZimFile;

public class ZimEntryTest {
	@Test
	public void testArticleEntry() {
		try {
			ZimFile file = new ZimFile("/home/neailan/桌面/jkiwix/zims/wikipedia_zh_medicine_nopic.zim");
			System.out.println(file.getEntry(file.getMainPage()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testRedirectEntry() {
		try {
			ZimFile file = new ZimFile("/home/neailan/桌面/jkiwix/zims/wikipedia_zh_medicine_nopic.zim");
			System.out.println(file.getEntry(131));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
