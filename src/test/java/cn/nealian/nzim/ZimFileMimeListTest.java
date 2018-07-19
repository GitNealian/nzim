package cn.nealian.nzim;

import java.io.IOException;

import org.junit.Test;

import cn.nealian.nzim.ZimFile;

public class ZimFileMimeListTest {
	@Test
	public void testGetMimeList() {
		try {
			ZimFile file = new ZimFile("/home/neailan/桌面/jkiwix/zims/wikipedia_zh_medicine_nopic.zim");
			file.getMimeList().forEach(mime -> {
				System.out.println(mime);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
