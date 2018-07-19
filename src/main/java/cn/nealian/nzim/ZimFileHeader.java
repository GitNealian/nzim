package cn.nealian.nzim;

class ZimFileHeader {
	private int magicNumber;
	private int majorVersion;
	private int minorVersion;
	private byte[] uuid;
	private int articleCount;
	private int clusterCount;
	private long urlPtrPos;
	private long titlePtrPos;
	private long clusterPtrPos;
	private long mimeListPos;
	private int mainPage;
	private int layoutPage;
	private long checksumPos;

	protected void setMagicNumber(int magicNumber) {
		this.magicNumber = magicNumber;
	}

	protected void setMajorVersion(int majorVersion) {
		this.majorVersion = majorVersion;
	}

	protected void setMinorVersion(int minorVersion) {
		this.minorVersion = minorVersion;
	}

	protected void setUuid(byte[] uuid) {
		this.uuid = uuid;
	}

	protected void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}

	protected void setClusterCount(int clusterCount) {
		this.clusterCount = clusterCount;
	}

	protected void setUrlPtrPos(long urlPtrPos) {
		this.urlPtrPos = urlPtrPos;
	}

	protected void setTitlePtrPos(long titlePtrPos) {
		this.titlePtrPos = titlePtrPos;
	}

	protected void setClusterPtrPos(long clusterPtrPos) {
		this.clusterPtrPos = clusterPtrPos;
	}

	protected void setMimeListPos(long mimeListPos) {
		this.mimeListPos = mimeListPos;
	}

	protected void setMainPage(int mainPage) {
		this.mainPage = mainPage;
	}

	protected void setLayoutPage(int layoutPage) {
		this.layoutPage = layoutPage;
	}

	protected void setChecksumPos(long checksumPos) {
		this.checksumPos = checksumPos;
	}

	public int getMagicNumber() {
		return magicNumber;
	}

	public int getMajorVersion() {
		return majorVersion;
	}

	public int getMinorVersion() {
		return minorVersion;
	}

	public String getUuid() {
		String ret = "0x";
		for (int i = uuid.length - 1; i >= 0; i--) {
			ret += Integer.toHexString(uuid[i]);
		}
		return ret;
	}

	public int getArticleCount() {
		return articleCount;
	}

	public int getClusterCount() {
		return clusterCount;
	}

	public long getUrlPtrPos() {
		return urlPtrPos;
	}

	public long getTitlePtrPos() {
		return titlePtrPos;
	}

	public long getClusterPtrPos() {
		return clusterPtrPos;
	}

	public long getMimeListPos() {
		return mimeListPos;
	}

	public int getMainPage() {
		return mainPage;
	}

	public int getLayoutPage() {
		return layoutPage;
	}

	public long getChecksumPos() {
		return checksumPos;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("magicNumber: " + magicNumber + "\n");
		sb.append("majorVersion: " + majorVersion + "\n");
		sb.append("minorVersion: " + minorVersion + "\n");
		sb.append("uuid: " + getUuid() + "\n");
		sb.append("articleCount: " + articleCount + "\n");
		sb.append("clusterCount: " + clusterCount + "\n");
		sb.append("urlPtrPos: " + urlPtrPos + "\n");
		sb.append("titlePtrPos: " + titlePtrPos + "\n");
		sb.append("clusterPtrPos: " + clusterPtrPos + "\n");
		sb.append("mimeListPos: " + mimeListPos + "\n");
		sb.append("mainPage: " + mainPage + "\n");
		sb.append("layoutPage: " + layoutPage + "\n");
		sb.append("checksumPos: " + checksumPos + "\n");
		return sb.toString();
	}
}
