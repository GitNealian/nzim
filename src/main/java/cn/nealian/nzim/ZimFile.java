package cn.nealian.nzim;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ZimFile {
	private ZimFileHeader header;
	private File file;
	private final List<String> mimeList;
	private ZimFileReader reader;

	public ZimFile(String path) throws IOException {
		this.file = new File(path);
		this.reader = new ZimFileReader(file);
		this.header = reader.readHeader();
		this.mimeList = reader.readMimeList(header);
	}

	/* index starts with 0 and it's sorted by url */
	public DirectoryEntry getEntry(int index, boolean redirect) throws IOException {
		if (index >= 0 && index < getArticleCount()) {
			return getEntryByEntryPtr(reader.readEntryPtr(header, index), redirect);
		}
		return null;
	}

	public DirectoryEntry getEntry(String url, boolean redirect) throws IOException {
		return getEntry(getEntryIndex(url), redirect);
	}

	private int getEntryIndex(String url) throws IOException {
		return reader.readEntryIndex(header, url);
	}

	private DirectoryEntry getEntryByEntryPtr(long entryPtr, boolean redirect) throws IOException {
		return reader.readEntry(header, mimeList, entryPtr, redirect);
	}

	public List<String> getMimeList() {
		return this.mimeList;
	}

	public int getMagicNumber() {
		return header.getMagicNumber();
	}

	public int getMajorVersion() {
		return header.getMajorVersion();
	}

	public int getMinorVersion() {
		return header.getMinorVersion();
	}

	public String getUuid() {
		return header.getUuid();
	}

	public int getArticleCount() {
		return header.getArticleCount();
	}

	public int getClusterCount() {
		return header.getClusterCount();
	}

	public long getUrlPtrPos() {
		return header.getUrlPtrPos();
	}

	public long getTitlePtrPos() {
		return header.getTitlePtrPos();
	}

	public long getClusterPtrPos() {
		return header.getClusterPtrPos();
	}

	public long getMimeListPos() {
		return header.getMimeListPos();
	}

	public int getMainPage() {
		return header.getMainPage();
	}

	public int getLayoutPage() {
		return header.getLayoutPage();
	}

	public long getChecksumPos() {
		return header.getChecksumPos();
	}
}
