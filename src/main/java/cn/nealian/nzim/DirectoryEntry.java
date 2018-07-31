package cn.nealian.nzim;

import java.io.InputStream;

public abstract class DirectoryEntry {
	private char namespace;
	private int revision;
	private String url;
	private String title;

	public char getNamespace() {
		return namespace;
	}

	public int getRevision() {
		return revision;
	}

	public String getUrl() {
		return url;
	}

	public String getTitle() {
		return title;
	}

	protected void setNamespace(char namespace) {
		this.namespace = namespace;
	}

	protected void setRevision(int revision) {
		this.revision = revision;
	}

	protected void setUrl(String url) {
		this.url = url;
	}

	protected void setTitle(String title) {
		this.title = title;
	}

	public abstract String getMimeType();

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("namespace: " + namespace + "\n");
		sb.append("revision: " + revision + "\n");
		sb.append("url: " + url + "\n");
		sb.append("title: " + title + "\n");
		return sb.toString();
	}

}
