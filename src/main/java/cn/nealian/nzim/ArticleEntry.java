package cn.nealian.nzim;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public class ArticleEntry extends DirectoryEntry implements Closeable{
	private String mineType;
	private int clusterNumber;
	private int blobNumber;
	private long clusterPtrPos;
	private int blobSize;
	private InputStream is;

	protected void setMineType(String mineType) {
		this.mineType = mineType;
	}

	public int getBlobSize() {
		return blobSize;
	};

	public long getClusterPtrPos() {
		return clusterPtrPos;
	}

	protected void setClusterPtrPos(long pos) {
		this.clusterPtrPos = pos;
	}

	public int getClusterNumber() {
		return clusterNumber;
	}

	public int getBlobNumber() {
		return blobNumber;
	}

	protected void setClusterNumber(int clusterNumber) {
		this.clusterNumber = clusterNumber;
	}

	protected void setBlobNumber(int blobNumber) {
		this.blobNumber = blobNumber;
	}

	public void setBlobSize(int blobSize) {
		this.blobSize = blobSize;
	}

	protected void setInputStream(InputStream is) {
		this.is = is;
	}

	@Override
	public String getMimeType() {
		return mineType;
	}

	public InputStream getInputStream() {
		return is;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(super.toString());
		sb.append("clusterNumber: " + clusterNumber + "\n");
		sb.append("blobNumber: " + blobNumber + "\n");
		sb.append("blobSize:" + blobSize + "\n");
		return sb.toString();
	}

	@Override
	public void close() throws IOException {
		is.close();
	}

}
