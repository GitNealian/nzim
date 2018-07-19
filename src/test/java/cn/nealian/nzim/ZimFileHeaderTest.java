package cn.nealian.nzim;

import java.io.IOException;

import org.junit.Test;

import cn.nealian.nzim.ZimFile;

public class ZimFileHeaderTest {
	@Test
	public void testGetHeader() {
		try {
			ZimFile file = new ZimFile("/home/neailan/桌面/jkiwix/zims/wikipedia_zh_medicine_nopic.zim");
			System.out.println("article count: " + file.getArticleCount());
			System.out.println("uuid: " + file.getUuid());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
