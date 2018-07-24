package cn.nealian.nzim;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class ZimTest {
	ZimFile file;

	public ZimTest() throws IOException {
		file = new ZimFile("/home/neailan/桌面/jkiwix/zims/wikipedia_zh_medicine_nopic.zim");
	}

	@Test
	public void testGetEntryData() {
		try {
			ArticleEntry entry = (ArticleEntry) file.getEntry(3399, false);
			InputStream is = entry.getInputStream();
			byte[] buff = new byte[entry.getBlobSize()];
			is.read(buff);
			System.out.println(new String(buff, "utf-8"));
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testArticleEntry() {
		try {
			System.out.println(file.getEntry(file.getMainPage(), false));
			for (int i = 0; i < file.getArticleCount(); i++) {
				if (file.getEntry(i, false).getMimeType().contains("javascript")) {
					System.out.println(file.getEntry(i, false).getUrl());
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testRedirectEntry() {
		try {
			System.out.println("131");
			System.out.println(file.getEntry(131, false));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetHeader() {
		System.out.println("article count: " + file.getArticleCount());
		System.out.println("uuid: " + file.getUuid());
	}

	@Test
	public void testGetMimeList() {
		file.getMimeList().forEach(mime -> {
			System.out.println(mime);
		});
	}

	public void getAllSortedUrl() {
		int i = 0;
		while (i < file.getArticleCount()) {
			try {
				System.out.println(file.getEntry(i, false).getNamespace() + "/" + file.getEntry(i, false).getUrl());
			} catch (IOException e) {
				e.printStackTrace();
			}
			i++;
		}
	}

	@Test
	public void testGetEntryByUrl() throws IOException {
		System.out.println("get entry by url: 正壬醇.html\n" + file.getEntry("-/j/js_modules/site.js", false));
	}

}
