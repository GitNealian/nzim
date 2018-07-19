package cn.nealian.nzim;

import java.io.InputStream;

public class LinktargetOrDeletedEntry extends DirectoryEntry{
	public static final int LINKTARGET = 0xfff3;
	public static final int DELETED = 0xfffd;
	private int type;
	
	public int getType() {
		return type;
	}
	protected void setType(int type) {
		this.type = type;
	}
	@Override
	public String getMimeType() {
		if(type == 0xfff3) {
			return "linktarget";
		}else {
			return null;
		}
	}
	@Override
	public InputStream getInputStream() {
		return null;
	}
	
}
