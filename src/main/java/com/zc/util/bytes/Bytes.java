package com.zc.util.bytes;

public class Bytes {

	public static final String hexString = "0123456789ABCDEF";
	
	public static int getMaxBinLessValue(int value) {
		int resutl = value;
		
		resutl |= resutl >>> 1;
		resutl |= resutl >>> 2;
		resutl |= resutl >>> 4;
		resutl |= resutl >>> 8;
		resutl |= resutl >>> 16;
		
		return (resutl>>>1) + 1;
	}
	
	public static int getMinBinThanValue(int value) {
		int resutl = value - 1;
		
		resutl |= resutl >>> 1;
		resutl |= resutl >>> 2;
		resutl |= resutl >>> 4;
		resutl |= resutl >>> 8;
		resutl |= resutl >>> 16;
		
		return resutl + 1;
	}
	
	public static byte[] longToByte(long value) {
		return longToByte(new byte[8], value, 0);
	}
	
	public static byte[] longToByte(byte[] bytes, long value, int offset) {
		if (bytes == null || bytes.length < offset + 8) {
			return bytes;
		}

		bytes[offset] = (byte) (value & 0xFF);
		bytes[offset + 1] = (byte) ((value >> 8) & 0xFF);
		bytes[offset + 2] = (byte) ((value >> 16) & 0xFF);
		bytes[offset + 3] = (byte) ((value >> 24) & 0xFF);
		bytes[offset + 4] = (byte) ((value >> 32) & 0xFF);
		bytes[offset + 5] = (byte) ((value >> 40) & 0xFF);
		bytes[offset + 6] = (byte) ((value >> 48) & 0xFF);
		bytes[offset + 7] = (byte) ((value >> 56) & 0xFF);
		
		return bytes;
	}
	
	public static long byteToLong(byte[] bytes) {
		return byteToLong(bytes, 0);
	}
	
	public static long byteToLong(byte[] bytes, int offset) {
		long result = 0L;
		
		if (bytes == null || bytes.length < 8 + offset) {
			return result;
		}

		result |= (bytes[offset] & 0xFFL)
				| ((bytes[1 + offset] & 0xFFL) << 8)
				| ((bytes[2 + offset] & 0xFFL) << 16)
				| ((bytes[3 + offset] & 0xFFL) << 24)
				| ((bytes[4 + offset] & 0xFFL) << 32)
				| ((bytes[5 + offset] & 0xFFL) << 40)
				| ((bytes[6 + offset] & 0xFFL) << 48)
				| ((bytes[7 + offset] & 0xFFL) << 56);

		return result;
	}
	
	public static int byteToInt(byte[] bytes, int offset) {
		int result = 0;
		
		if (bytes == null || bytes.length < 4 + offset) {
			return result;
		}
		
		result = (bytes[offset] & 0xFF)
				| ((bytes[1 + offset] & 0xFF) << 8)
				| ((bytes[2 + offset] & 0xFF) << 16)
				| ((bytes[3 + offset] & 0xFF) << 24);
		
		return result;
	}
		
	public static int byteToInt(byte[] bytes) {
		return byteToInt(bytes, 0);
	}
	
	public static byte[] intToByte(byte[] bytes, int value, int offset) {
		if (bytes == null || bytes.length < offset + 4) {
			return bytes;
		}
		
		bytes[offset] = (byte) (value & 0xFF);
		bytes[offset + 1] = (byte) ((value >> 8) & 0xFF);
		bytes[offset + 2] = (byte) ((value >> 16) & 0xFF);
		bytes[offset + 3] = (byte) ((value >> 24) & 0xFF);
		
		return bytes;
	}
	
	public static byte[] intToByte(int value) {
		return intToByte(new byte[4], value, 0);
	}
	
	public static String longToHex(long value) {
		StringBuffer result = new StringBuffer();
		for (int index = 60; index >= 0; index -= 4) {
			result.append(hexString.charAt((int) (value>>>index) & 0xF));
		}
		
		return result.toString();
	}
	
	public static String intToHex(int value) {
		StringBuffer result = new StringBuffer();
		for (int index = 28; index >= 0; index -= 4) {
			result.append(hexString.charAt((value>>>index) & 0x0F));
		}
		
		return result.toString();
	}
	
	public static String byteToHex(byte value) {
		return String.valueOf(hexString.charAt((value>>4) & 0xF))
				+ String.valueOf(hexString.charAt(value & 0xF));
	}
	
	public static String sizeToString(long size) {
		if (size < 0) {
			return null;
		}
		if (size < (1L << 10)) {
			return String.valueOf(size);
		}
		if (size < (1L << 20)) {
			return String.valueOf(size >> 10) + "K";
		}
		if (size < (1L << 30)) {
			return String.valueOf(size >> 20) + "M";
		}
		if (size < (1L << 40)) {
			return String.valueOf(size >> 30) + "G";
		}
		if (size < (1L << 50)) {
			return String.valueOf(size >> 40) + "T";
		}

		return String.valueOf(size >> 50) + "P";
	}
	
}
