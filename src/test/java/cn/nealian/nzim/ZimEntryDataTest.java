package cn.nealian.nzim;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import cn.nealian.nzim.ArticleEntry;
import cn.nealian.nzim.ZimFile;

public class ZimEntryDataTest {
	@Test
	public void testGetEntryDataUncompressed() {
//		ZimFile file = new ZimFile("/home/neailan/桌面/jkiwix/zims/wikipedia_en_ray_charles_2013-03.zim");
		
		try {
			ZimFile file = new ZimFile("/home/neailan/桌面/jkiwix/zims/wikipedia_zh_medicine_nopic.zim");
			ArticleEntry entry = (ArticleEntry) file.getEntry(3399);
			InputStream is = entry.getInputStream();
			byte[] buff = new byte[entry.getBlobSize()];
			is.read(buff);
			System.out.println(new String(buff, "utf-8"));
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
