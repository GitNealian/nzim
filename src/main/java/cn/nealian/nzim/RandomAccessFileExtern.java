package cn.nealian.nzim;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class RandomAccessFileExtern extends RandomAccessFile {

	protected RandomAccessFileExtern(File file, String mode) throws FileNotFoundException {
		super(file, mode);
	}

	protected int readFourLittleEndianBytesAsInt() throws IOException {
		byte[] buff = new byte[4];
		int ret = read(buff);
		if (ret != 4) {
			throw new IOException("4 bytes needed but " + ret + "bytes read");
		}
		return buff[0] & 0xFF | (buff[1] & 0xFF) << 8 | (buff[2] & 0xFF) << 16 | (buff[3] & 0xFF) << 24;
	}

	protected int readTwoLittleEndianBytesAsInt() throws IOException {
		byte[] buff = new byte[2];
		int ret = read(buff);
		if (ret != 2) {
			throw new IOException("2 bytes needed but " + ret + "bytes read");
		}
		return buff[0] & 0xFF | (buff[1] & 0xFF) << 8;
	}

	protected byte[] readSixteenBytesAsByteArray() throws IOException {
		byte[] buff = new byte[16];
		int ret = read(buff);
		if (ret != 16) {
			throw new IOException("16 bytes needed but " + ret + "bytes read");
		}
		return buff;
	}

	protected long readEightLittleEndianBytesAsLong() throws IOException {
		byte[] buff = new byte[8];
		int ret = read(buff);
		if (ret != 8) {
			throw new IOException("8 bytes needed but " + ret + "bytes read");
		}
		return buff[0] & 0xFF | (buff[1] & 0xFF) << 8 | (buff[2] & 0xFF) << 16 | (buff[3] & 0xFF) << 24
				| buff[4] & 0xFF << 32 | (buff[5] & 0xFF) << 40 | (buff[6] & 0xFF) << 48 | (buff[7] & 0xFF) << 56;
	}

	/* read a string in UTF-8 charset */
	protected String readString() throws IOException {
		List<Byte> bl = new ArrayList<>();
		byte[] buff;
		Byte b = (byte) read();
		while (b != 0) {
			bl.add(b);
			b = (byte) read();
		}
		buff = new byte[bl.size()];
		for (int i = 0; i < bl.size(); i++) {
			buff[i] = bl.get(i);
		}
		return new String(buff, "utf-8");
	}

	protected int fourLittleEndianBytesToInt(byte[] buff) {
		assert buff.length == 4;
		return buff[0] & 0xFF | (buff[1] & 0xFF) << 8 | (buff[2] & 0xFF) << 16 | (buff[3] & 0xFF) << 24;
	}

}
