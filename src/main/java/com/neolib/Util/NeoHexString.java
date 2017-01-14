package com.neolib.Util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NeoHexString {

	public static byte[] HexStrToByteArray(String hex) {
		
		hex = hex.toUpperCase();
		
		
		if (hex == null || hex.length() == 0) {
			return null;
		}
		
		if(!IsHexStr(hex)) return null;

		byte[] ba = new byte[hex.length() / 2];
		for (int i = 0; i < ba.length; i++) {
			ba[i] = (byte) Integer
					.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return ba;
	}

	public static boolean IsHexStr(String hex) {
		if(hex.length() %2 == 1) return false;
		
		hex = hex.toUpperCase();
		

		Pattern r = Pattern.compile("^[A-F0-9]*$");

		Matcher m = r.matcher(hex);

		return m.find();
	}

	public static String ByteArrayToHexStr(byte[] bytes) {

		return ByteArrayToHexStr(bytes, "");// sb.toString();
	}

	public static String ByteArrayToHexStr(byte[] bytes, String enc) {
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		for (byte b : bytes)
			sb.append(String.format("%02X%s", b & 0xff, enc));

		return sb.toString();
	}

	public static String TextToHexString(String text)
			throws UnsupportedEncodingException {
		return TextToHexString(text, "UTF-8");
	}

	public static String TextToHexString(String text, String charsetName)
			throws UnsupportedEncodingException {
		return ByteArrayToHexStr(text.getBytes(charsetName));
	}

	public static String HexStringToText(String hexStr)
			throws UnsupportedEncodingException {

		return HexStringToText(hexStr, "UTF-8");
	}

	public static String HexStringToText(String hexStr, String enc)
			throws UnsupportedEncodingException {
		return new String(HexStrToByteArray(hexStr), enc);

	}
	public static void main(String[] args){
		
		byte[] bytes = HexStrToByteArray("aa");
		
		bytes = HexStrToByteArray("aaa");
		bytes = HexStrToByteArray("123123134522");
		
	}

}
