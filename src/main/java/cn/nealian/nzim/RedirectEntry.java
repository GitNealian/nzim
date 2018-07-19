package cn.nealian.nzim;

import java.io.InputStream;

public class RedirectEntry extends DirectoryEntry {
	private int redirectIndex;

	public int getRedirectIndex() {
		return redirectIndex;
	}

	protected void setRedirectIndex(int redirectIndex) {
		this.redirectIndex = redirectIndex;
	}

	@Override
	public String getMimeType() {
		return "redirect";
	}

	@Override
	public InputStream getInputStream() {
		return null;
	}

	@Override
	public String toString() {
		return super.toString() + "redirectIndex: " + redirectIndex + "\n";
	}

}
