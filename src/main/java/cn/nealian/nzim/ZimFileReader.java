package cn.nealian.nzim;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.tukaani.xz.SingleXZInputStream;

class ZimFileReader {
	private File file;
	private ZimFileHeader header;
	protected ZimFileReader(File file) {
		this.file = file;
	}

	protected List<String> readMimeList(long mimeListPos) throws IOException {
		List<String> mimeList = new ArrayList<>();
		RandomAccessFileExtern rafe = new RandomAccessFileExtern(file, "r");
		rafe.seek(mimeListPos);
		String mime = null;
		do {
			mime = rafe.readString();
			mimeList.add(mime);
		} while (mime != null && !mime.isEmpty());
		rafe.close();
		return mimeList;
	}
	
	protected ZimFileHeader readHeader() throws IOException {
		ZimFileHeader header = new ZimFileHeader();
		RandomAccessFileExtern rafe = new RandomAccessFileExtern(file, "r");
		header.setMagicNumber(rafe.readFourLittleEndianBytesAsInt());
		header.setMajorVersion(rafe.readTwoLittleEndianBytesAsInt());
		header.setMinorVersion(rafe.readTwoLittleEndianBytesAsInt());
		header.setUuid(rafe.readSixteenBytesAsByteArray());
		header.setArticleCount(rafe.readFourLittleEndianBytesAsInt());
		header.setClusterCount(rafe.readFourLittleEndianBytesAsInt());
		header.setUrlPtrPos(rafe.readEightLittleEndianBytesAsLong());
		header.setTitlePtrPos(rafe.readEightLittleEndianBytesAsLong());
		header.setClusterPtrPos(rafe.readEightLittleEndianBytesAsLong());
		header.setMimeListPos(rafe.readEightLittleEndianBytesAsLong());
		header.setMainPage(rafe.readFourLittleEndianBytesAsInt());
		header.setLayoutPage(rafe.readFourLittleEndianBytesAsInt());
		header.setChecksumPos(rafe.readEightLittleEndianBytesAsLong());
		rafe.close();
		this.header = header;
		return header;
	}

	protected long readEntryPtr(long urlPtrPos, int index) throws IOException {
		RandomAccessFileExtern rafe = new RandomAccessFileExtern(file, "r");
		rafe.seek(urlPtrPos + 8 * index);
		long entryPtr = rafe.readEightLittleEndianBytesAsLong();
		rafe.close();
		return entryPtr;
	}

	protected DirectoryEntry readEntry(List<String> mimeList, long clusterPtrPos, long entryPtr, boolean ignoreRedirect) throws IOException {
		DirectoryEntry entry;
		RandomAccessFileExtern rafe = new RandomAccessFileExtern(file, "r");
		rafe.seek(entryPtr);
		int mime = rafe.readTwoLittleEndianBytesAsInt();
		rafe.read(); /* ignore parameter len */
		char namespace = (char) rafe.read();
		int revision = rafe.readFourLittleEndianBytesAsInt();
		if(mime == 0xffff) {
			RedirectEntry rentry = new RedirectEntry();
			rentry.setRedirectIndex(rafe.readFourLittleEndianBytesAsInt());
			entry = rentry;
		}else if(mime == 0xfffd || mime == 0xfffe) {
			LinktargetOrDeletedEntry lentry = new LinktargetOrDeletedEntry();
			lentry.setType(mime);
			entry = lentry;
		}else {
			ArticleEntry aentry = new ArticleEntry();
			aentry.setClusterNumber(rafe.readFourLittleEndianBytesAsInt());
			aentry.setBlobNumber(rafe.readFourLittleEndianBytesAsInt());
			aentry.setClusterPtrPos(clusterPtrPos);
			aentry.setInputStream(readEntryData(aentry));
			entry = aentry;
		}
		entry.setNamespace(namespace);
		entry.setRevision(revision);
		entry.setUrl(rafe.readString());
		entry.setTitle(rafe.readString());
		rafe.close();
		return entry;
	}

	protected int readEntryIndex(long urlPtr,String url, boolean ignoreRedirect) {
		return 0;
	}
	
	private InputStream readEntryData(ArticleEntry entry) throws IOException {
		RandomAccessFileExtern rafe = new RandomAccessFileExtern(file, "r");
		/* both clusterNumber and blobNumber start with 0 */
		rafe.seek(entry.getClusterPtrPos() + (entry.getClusterNumber()) * 8);
		long clusterPos = rafe.readEightLittleEndianBytesAsLong();
		rafe.seek(clusterPos);
		int a = rafe.read();
		int OFFSET_SIZE = 4;
		if((a&0x10) == 0 && header.getMajorVersion()==6) {
			OFFSET_SIZE = 8;
		}
		if (a == 4) {
			// rafe.skipBytes(4 * (blobNumber - 1));
			SingleXZInputStream xcis = new SingleXZInputStream(new ZimInputStream(rafe), 4194304);
			byte[] buff = new byte[OFFSET_SIZE];

			xcis.skip(OFFSET_SIZE * entry.getBlobNumber());/* now pointer points to offset of the blob we want */
			xcis.read(buff);
			int blobOffset = rafe.fourLittleEndianBytesToInt(buff);
			/* now pointer points to offset of the next blob to the blob we want */
			xcis.read(buff);
			int nextBlobOffset = rafe.fourLittleEndianBytesToInt(buff);

			/*
			 * we have already finished 4*(entry.getBlobNumber()+2) bytes of the
			 * "blobOffset" bytes journey
			 */
			xcis.skip(blobOffset - OFFSET_SIZE * (entry.getBlobNumber() + 2));
			entry.setBlobSize(nextBlobOffset - blobOffset);
			return xcis;
		}
		rafe.close();
		return null;
	}

	private class ZimInputStream extends InputStream {
		private RandomAccessFileExtern rafe;

		public ZimInputStream(RandomAccessFileExtern rafe) {
			this.rafe = rafe;
		}

		@Override
		public int read() throws IOException {
			return rafe.read();
		}
	}
}