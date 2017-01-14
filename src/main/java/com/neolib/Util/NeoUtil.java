package com.neolib.Util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import com.neolib.Info.BNote;
import com.google.common.collect.Maps;

public class NeoUtil {

	static String m_sep = "/";

	public static void Sleep(int msec) {
		try {
			Thread.sleep(msec);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void Test() {

	}

	public static native int GetLastError();

	static {
		// System.loadLibrary("kernel32.dll");
	}

	public static void SetWindowStyle() {
		m_sep = "\\";

	}

	public static native int FormatMessage(int dwFlags, String lpSource,
			int dwMessageId, int dwLanguageId, StringBuilder lpBuffer,
			int nSize, String[] Arguments);

	public static String GetLastErrorMessage() {
		StringBuilder strLastErrorMessage = new StringBuilder(255);
		int ret2 = GetLastError();
		int dwFlags = 4096;

		int ret3 = FormatMessage(dwFlags, null, ret2, 0, strLastErrorMessage,
				strLastErrorMessage.capacity(), null);

		return strLastErrorMessage.toString();
	}

	/**
	 * int형을 byte배열로 바꿈<br>
	 * 
	 * @param integer
	 * @param order
	 * @return
	 */
	public static byte[] intTobyte(int integer, ByteOrder order) {

		ByteBuffer buff = ByteBuffer.allocate(Integer.SIZE / 8);
		buff.order(order);

		// 인수로 넘어온 integer을 putInt로설정
		buff.putInt(integer);

		return buff.array();
	}

	/**
	 * byte배열을 int형로 바꿈<br>
	 * 
	 * @param bytes
	 * @param order
	 * @return
	 */
	public static int byteToInt(byte[] bytes, ByteOrder order) {

		ByteBuffer buff = ByteBuffer.allocate(Integer.SIZE / 8);
		buff.order(order);

		// buff사이즈는 4인 상태임
		// bytes를 put하면 position과 limit는 같은 위치가 됨.
		buff.put(bytes);
		// flip()가 실행 되면 position은 0에 위치 하게 됨.
		buff.flip();

		return buff.getInt(); // position위치(0)에서 부터 4바이트를 int로 변경하여 반환
	}

	/*
	 * //C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
	 * equivalent in Java: //ORIGINAL LINE: public static byte[]
	 * MakeByteFormStruct<THD>(THD orgstruct) public static <THD> byte[]
	 * MakeByteFormStruct(THD orgstruct) { //C# TO JAVA CONVERTER WARNING:
	 * Unsigned integer types have no direct equivalent in Java: //ORIGINAL
	 * LINE: byte[] retBuffer = new byte[Marshal.SizeOf(orgstruct)]; byte[]
	 * retBuffer = new
	 * byte[System.Runtime.InteropServices.Marshal.SizeOf(orgstruct)];
	 * ConvertStr2Bin(orgstruct, retBuffer, 0); return retBuffer; }
	 * 
	 * 
	 * 
	 * //C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
	 * equivalent in Java: //ORIGINAL LINE: public static THD
	 * MakeStructFromByte<THD>(byte[] inData, int startpt) public static <THD>
	 * THD MakeStructFromByte(byte[] inData, int startpt) { THD logHd = null;
	 * RefObject<THD> tempRef_logHd = new RefObject<THD>(logHd);
	 * ConvertBin2Str(inData, startpt, tempRef_logHd); logHd =
	 * tempRef_logHd.argValue; return logHd; } //C# TO JAVA CONVERTER WARNING:
	 * Unsigned integer types have no direct equivalent in Java: //ORIGINAL
	 * LINE: public static byte[] MakeByteFormStructArray<THD>(THD [] orgstruct)
	 * public static <THD> byte[] MakeByteFormStructArray(THD[] orgstruct) {
	 * //C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
	 * equivalent in Java: //ORIGINAL LINE: var ret = new List<byte>();
	 * java.util.ArrayList<Byte> ret = new java.util.ArrayList<Byte>(); //C# TO
	 * JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in
	 * Java: for (var tmp : orgstruct) { ret.addAll(MakeByteFormStruct(tmp)); }
	 * 
	 * //C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
	 * equivalent in Java: //ORIGINAL LINE: return ret.ToArray(); return
	 * ret.toArray(new byte[0]); } //C# TO JAVA CONVERTER WARNING: Unsigned
	 * integer types have no direct equivalent in Java: //ORIGINAL LINE: public
	 * static THD [] MakeStructArrayFromByte<THD>(byte[] inData, int startpt)
	 * public static <THD> THD[] MakeStructArrayFromByte(byte[] inData, int
	 * startpt) { java.util.ArrayList<THD> tableindexs = new
	 * java.util.ArrayList<THD>(); int lenth =
	 * System.Runtime.InteropServices.Marshal.SizeOf(THD.class); int total =
	 * inData.length - startpt;
	 * 
	 * while (startpt < total) {
	 * 
	 * THD tableindex = NeoUtil.<THD>MakeStructFromByte(inData, startpt);
	 * tableindexs.add(tableindex); //EDHEDAER_TABLEINDEX tableindex =
	 * NeoUtil.MakeStructFromByte(table,startpoint); startpt += lenth; } return
	 * tableindexs.toArray(new THD[0]);
	 * 
	 * }
	 * 
	 * public static <THD> THD AssignMemory() {
	 * 
	 * return com.neolib.Util.NeoUtil.<THD>MakeStructFromByte(null, 0); }
	 * 
	 * 
	 * //C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
	 * equivalent in Java: //ORIGINAL LINE: public static bool
	 * ConvertBin2Str<THD>(byte[] inData, int startpt, ref THD logHd) public
	 * static <THD> boolean ConvertBin2Str(byte[] inData, int startpt,
	 * RefObject<THD> logHd) {
	 * 
	 * 
	 * int size = System.Runtime.InteropServices.Marshal.SizeOf(logHd.argValue);
	 * IntPtr pnt = Marshal.AllocHGlobal(size);
	 * 
	 * try {
	 * 
	 * // Copy the struct to unmanaged memory. if (inData == null) { //C# TO
	 * JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent
	 * in Java: //ORIGINAL LINE: inData = new byte[size]; inData = new
	 * byte[size]; }
	 * 
	 * Marshal.Copy(inData, startpt, pnt, size);
	 * 
	 * //Marshal.PtrToStructure(); // Set this Point to the value of the //
	 * Point in unmanaged memory. logHd.argValue =
	 * (THD)Marshal.PtrToStructure(pnt, THD.class);
	 * 
	 * } catch (java.lang.Exception e) { int asd = 0; return false;
	 * 
	 * } finally { // Free the unmanaged memory. Marshal.FreeHGlobal(pnt); }
	 * return true;
	 * 
	 * } //C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
	 * equivalent in Java: //ORIGINAL LINE: public static bool
	 * ConvertStr2Bin<THD>(THD logHd, byte[] inData, int startpt) public static
	 * <THD> boolean ConvertStr2Bin(THD logHd, byte[] inData, int startpt) {
	 * 
	 * // Initialize unmanged memory to hold the struct. //int size =
	 * Marshal.SizeOf(logHd); //IntPtr pnt = Marshal.AllocHGlobal(size);
	 * 
	 * 
	 * try { //C# TO JAVA CONVERTER TODO TASK: There is no equivalent to an
	 * 'unsafe' block in Java: unsafe { //C# TO JAVA CONVERTER TODO TASK: There
	 * is no direct Java equivalent to 'fixed' blocks: fixed (byte *
	 * fixed_buffer = &inData[startpt]) { Marshal.StructureToPtr(logHd,
	 * (IntPtr)fixed_buffer, false); } } //C# TO JAVA CONVERTER TODO TASK: There
	 * is no preprocessor in Java: //#if false
	 * 
	 * Marshal.StructureToPtr((Object)logHd, pnt, false);
	 * 
	 * // Copy the struct to unmanaged memory. Marshal.Copy(pnt, inData,
	 * startpt, size); //Marshal.PtrToStructure(); // Set this Point to the
	 * value of the // Point in unmanaged memory. //#endif
	 * 
	 * 
	 * } catch (java.lang.Exception e) { int asd = 0; return false;
	 * 
	 * } finally { // Free the unmanaged memory. //Marshal.FreeHGlobal(pnt); }
	 * return true;
	 * 
	 * }
	 * 
	 * public static <THX> long GetHeaderSize() { return
	 * System.Runtime.InteropServices.Marshal.SizeOf(THX.class); }
	 */

	// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
	// equivalent in Java:
	// ORIGINAL LINE: public static DateTime ConvTime_T2DateTime(UInt64 time_t)
	public static java.util.Date ConvTime_T2DateTime(long time_t) {
		return new Date(time_t);
	}

	// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
	// equivalent in Java:
	// ORIGINAL LINE: public static UInt64 ConvDateTime2Time_T(DateTime dt)
	public static long ConvDateTime2Time_T(java.util.Date dt) {
		return dt.getTime();
	}

	public static String noteBytes(long bytes) {
		return BNote.noteBytes(bytes);
		// double tmpByte = bytes;
		// double prevByte = tmpByte;
		// string[] mb = new string[] { "B", "KB", "MB", "GB", "TB" };
		// int count = 0;
		// while(true){
		// tmpByte /= 1024;
		// if (tmpByte < 1) break;
		// prevByte = tmpByte;
		// count ++;
		// }
		// return prevByte.ToString("#.##") + mb[count];
	}

	public static double GetAverage(double[] arr) {
		double sum = 0;
		if (arr.length == 0) {
			return 0;
		}
		for (double tmp : arr) {
			sum += tmp;
		}
		return sum / arr.length;
	}

	// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
	// equivalent in Java:
	// ORIGINAL LINE: public static string GetStrig(byte[] orgbyte)
	public static String GetStrig(byte[] orgbyte) {
		return GetStrig(orgbyte, "");
	}

	// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
	// equivalent in Java:
	// ORIGINAL LINE: public static string GetStrig(byte [] orgbyte,Encoding
	// enc)
	public static String GetStrig(byte[] orgbyte, String enc) {
		if (enc == "") {
			enc = "UTF-8";
		}

		String str = "";
		try {
			str = new String(orgbyte, enc);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str.replace("\0", "");
	}

	// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
	// equivalent in Java:
	// ORIGINAL LINE: public static int search(byte[] haystack, byte[]
	// needle,int start)
	public static int search(byte[] haystack, byte[] needle, int start) {
		for (int i = start; i <= haystack.length - needle.length; i++) {
			if (match(haystack, needle, i)) {
				return i;
			}
		}
		return -1;
	}

	public static long GetFileSize(String path) {
		long ret = 0;
		try {
			ret = (new java.io.File(path)).length();
		} catch (java.lang.Exception e) {

		}
		return ret;
	}

	// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
	// equivalent in Java:
	// ORIGINAL LINE: public static bool match(byte[] haystack, byte[] needle,
	// int start)
	public static boolean match(byte[] haystack, byte[] needle, int start) {
		if (needle.length + start > haystack.length) {
			return false;
		} else {
			for (int i = 0; i < needle.length; i++) {
				if (needle[i] != haystack[i + start]) {
					return false;
				}
			}
			return true;
		}
	}

	public static void MakeDirectory(String path) throws Exception {
		// try
		// {
		String drname = NeoUtil.GetDriectoryNameFromPath(path);
		if ((new java.io.File(path)).isDirectory() == true)
			return;

		String[] comps = drname.split("/");
		drname = "";
		for (String tmp : comps) {
			drname += tmp;
			String compdrname = drname;
			drname += m_sep;
			if ((new java.io.File(compdrname)).isDirectory() == true)
				continue;
			(new java.io.File(compdrname)).mkdir();

		}
		// String drname = (new java.io.File(path)).getParent();
		// String drname = path;
		// while(true){
		// drname = NeoUtil.GetDriectoryNameFromPath(drname);
		//
		// if ((new java.io.File(drname)).isDirectory() == false)
		// {
		// if((new java.io.File(drname)).mkdir()) break;
		// }
		//
		// }
		// }
		// catch (java.lang.Exception e)
		// {
		// e.printStackTrace();
		//
		// }

	}

	public static String join(String[] col, String delim, int startindex,
			int endindex) {
		StringBuilder sb = new StringBuilder();

		int length = endindex - startindex;

		for (int i = 0; i < length; i++) {
			String tmp = col[i + startindex];
			sb.append(tmp);
			if (i != length - 1)
				sb.append(delim);
		}

		return sb.toString();
	}

	public static String[] split(String orgstr, String ch, boolean isRemoveEmpty) {

		String[] comps = orgstr.split(ch);

		List<String> ret = new ArrayList<String>();

		for (String tmp : comps) {

			if (isRemoveEmpty && tmp == "")
				continue;

			ret.add(tmp);

		}
		String[] sArrays = ret.toArray(new String[ret.size()]);

		return sArrays;
	}

	public static String ReplaceNewBasePath(String orgpath, String newBasePath,
			int orgremovedepth) {
		if (newBasePath.equals("")) {
			return orgpath;
		}
		// string[] pathArr = orgpath.Split('\\');
		String[] pathArr = GetSplitFromPath(orgpath);
		if (pathArr.length <= orgremovedepth) {
			orgremovedepth = pathArr.length - 1;
		}
		String tmp = join(pathArr, m_sep, orgremovedepth, pathArr.length);// ,
																			// orgremovedepth,
																			// pathArr.length
																			// -
																			// orgremovedepth);

		return newBasePath + m_sep + tmp.replace(":", "");
	}

	public static String[] GetSplitComponent(String orgpath) {
		return orgpath.split("\\/"/* , StringSplitOptions.RemoveEmptyEntries */);
	}

	public static String GetDriverNameFromPath(String orgpath) {
		String[] ret = GetSplitFromPath(orgpath);

		return ret[0];
	}

	public static String GetPrex(String orgpath) {
		orgpath = orgpath.replace("\\", "/");
		if (orgpath.startsWith("//")) {

			return "//";
		} else if (orgpath.startsWith("/")) {
			return "/";
		}
		return "";
	}

	public static String[] RemoveEmpty(String[] liststr) {
		List<String> ret = new ArrayList<String>();
		for (String tmp : liststr) {
			if (tmp.equals(""))
				continue;
			ret.add(tmp);
		}
		return ret.toArray(new String[ret.size()]);
	}

	public static String[] GetSplitFromPath(String orgpath) {
		List<String> ret = new ArrayList<String>();
		orgpath = orgpath.replace("\\", "/");

		String prex = GetPrex(orgpath);

		String[] pathArr = GetSplitComponent(orgpath);
		pathArr = RemoveEmpty(pathArr);

		pathArr[0] = prex + pathArr[0];
		//
		//
		//
		// String[] pathArr = GetSplitComponent(orgpath);
		// if (orgpath.startsWith("\\\\")) // \\fs01\dd\mm 형식 처리를 위해
		// {
		// pathArr[0] = "\\\\" + pathArr[0];
		// }
		// if (orgpath.startsWith("//")) // \\fs01\dd\mm 형식 처리를 위해
		// {
		// pathArr[0] = "//" + pathArr[0];
		// }
		return pathArr;
	}

	public static String GetDriectoryNameFromPath(String orgpath) {

		String[] comp = GetSplitFromPath(orgpath);
		return join(comp, m_sep, 0, comp.length - 1);
	}

	public static int[] FindDQutoIndex(String orgstr) {
		java.util.ArrayList<Integer> list = new java.util.ArrayList<Integer>();
		int index = -1;

		while (true) {
			index = orgstr.indexOf('"', index + 1);
			if (index < 0) {
				break;
			}
			list.add(index);
		}

		int[] ret = new int[list.size()];
		int i = 0;
		for (Integer tmp : list) {
			ret[i++] = tmp;
		}

		return ret;

	}

	public static String[] ArgsParsing(String orgstr) {
		String[] ret = null;
		int[] indexs = FindDQutoIndex(orgstr);

		if (indexs.length != 0) {
			StringBuilder sb = new StringBuilder(orgstr);
			if (indexs.length % 2 != 0) {
				return null;
			}

			for (int i = 0; i < indexs.length / 2; i++) {
				int st = indexs[2 * i];
				int ed = indexs[2 * i + 1];
				sb.replace(st, ed - st, "\u0003");
			}

			orgstr = sb.toString();
		}
		ret = split(orgstr, new String(new char[] { ' ', '\t' }), true);

		for (int m = 0; m < ret.length; m++) {
			ret[m] = ret[m].replace("\u0003", " ");
			ret[m] = ret[m].replace("\"", "");
		}

		return ret;// orgstr.split(new char[] {' ', '\t'},
					// StringSplitOptions.RemoveEmptyEntries);

	}

	public static String GetStrFormFile(String filename) {

		return GetStrFormFile(filename, "utf-8");
	}

	public static String GetStrFormFile(String filename, String enc) {
		return FileUtil.ReadText(filename, enc);
	}

	public static void SaveStr2File(String filename, String str,
			boolean isAppend, String enc) {
		byte[] buffer;
		try {
			buffer = str.getBytes(enc);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		FileUtil.SaverBytes(filename, buffer, isAppend);

	}

	public static String[] GetStrLineFormFile(String filename, String enc) {
		String contents = GetStrFormFile(filename, enc);
		return split(contents, "\r\n", true);

	}

	public static String[] GetStrLineFormFile(String filename) {
		return GetStrLineFormFile(filename, "utf-8");

	}

	// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
	// equivalent in Java:
	// ORIGINAL LINE: public static DateTime GetDateTimeFromUINT32(UInt32
	// time_t)
	public static java.util.Date GetDateTimeFromUINT32(int time_t) {

		java.util.Date startTime = new java.util.Date(1970, 1, 1, 9, 0, 0);
		return ConvTime_T2DateTime((long) time_t);
	}

	// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
	// equivalent in Java:
	// ORIGINAL LINE: public static UInt32 GetUINT32TimeFromDateTime(DateTime
	// ctime)
	public static int GetUINT32TimeFromDateTime(java.util.Date ctime) {
		return (int) ConvDateTime2Time_T(ctime);
	}

	// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
	// equivalent in Java:
	// ORIGINAL LINE: public static byte[] SubBinary(byte[] org,int start,int
	// count)
	public static byte[] SubBinary(byte[] org, int start, int count) {

		// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
		// equivalent in Java:
		// ORIGINAL LINE: byte[] ret = new byte[count];
		byte[] ret = new byte[count];
		System.arraycopy(org, start, ret, 0, count);

		return ret;

	}

	// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
	// equivalent in Java:
	// ORIGINAL LINE: public static bool BinCompare(byte [] cmp1,byte[] cmp2)
	public static boolean BinCompare(byte[] cmp1, byte[] cmp2) {
		// if (cmp1.Length != cmp2.Length) return false;
		// int length = cmp1.Length;
		// for (int i = 0; i < length;i++ )
		// {
		// if (cmp1[i] != cmp2[i]) return false;
		// }
		return BinCompare(cmp1, 0, cmp2, 0, Math.max(cmp1.length, cmp2.length));
	}

	// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
	// equivalent in Java:
	// ORIGINAL LINE: public static bool BinCompare(byte[] cmp1,int icmp1,
	// byte[] cmp2,int icmp2,int length)
	public static boolean BinCompare(byte[] cmp1, int icmp1, byte[] cmp2,
			int icmp2, int length) {
		// if (cmp1.Length -icmp2 != cmp2.Length) return false;
		// int length = cmp1.Length;
		try {
			for (int i = 0; i < length; i++) {
				if (cmp1[i + icmp1] != cmp2[i + icmp2]) {
					return false;
				}
			}

		} catch (RuntimeException ex) {
			return false;

		}

		return true;

	}

	public static int GetNumberFromUnitNote(String orgnote) {
		int MaxBuffSize = -1;
		char un = orgnote.charAt(orgnote.length() - 1);
		int multi = 1024;

		String refunit = "KMGTPEZY";

		int index = refunit.indexOf(un);

		// int index = Array.indexOf(refunit.toCharArray(), un);
		if (index < 0) {
			// RefObject<Integer> tempRef_MaxBuffSize = new
			// RefObject<Integer>(MaxBuffSize);
			// Integer.TryParse(orgnote, tempRef_MaxBuffSize);

			MaxBuffSize = Integer.parseInt(orgnote);

			// MaxBuffSize = tempRef_MaxBuffSize.argValue;
			return MaxBuffSize;
		}
		orgnote = orgnote.substring(0, orgnote.length() - 1);

		for (int i = 0; i < index; i++) {
			multi *= 1024;
		}

		// RefObject<Integer> tempRef_MaxBuffSize2 = new
		// RefObject<Integer>(MaxBuffSize);
		MaxBuffSize = Integer.parseInt(orgnote);
		// MaxBuffSize = tempRef_MaxBuffSize2.argValue;

		MaxBuffSize *= multi;
		return MaxBuffSize;
	}

	public static NeoRandom Rand = new NeoRandom();

	// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
	// equivalent in Java:
	// ORIGINAL LINE: public static void GetRandData(ref byte[] ret, bool
	// isText)
	public static void GetRandData(RefParam<byte[]> ret, boolean isText) {
		ret.value = Rand.GetRandData(ret.value.length, isText);
	}

	// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
	// equivalent in Java:
	// ORIGINAL LINE: public static byte[] GetRandData(int legth, bool isText)
	public static byte[] GetRandData(int legth, boolean isText) {
		return Rand.GetRandData(legth, isText);
	}

	// public static byte[] GetRandData( byte [] ret,int legth, bool isText)
	// {
	// return isText ? GetRandText ( ret,legth): GetRandBin( ret,legth);
	// }

	// static byte[] GetRandBin( byte [] ret, int length)
	// {
	// if (ret == null) ret = new byte[length];
	//
	// Rand.NextBytes(ret);
	//
	// return ret;
	//
	// }
	// static byte[] GetRandText( byte [] ret,int length)
	// {
	// if(ret == null) ret = new byte[length];
	//
	// string reft = "";
	// for (char ch = 'a'; ch <= 'z'; ch++)
	// {
	// reft += ch;
	// }
	// for (char ch = 'A'; ch <= 'Z'; ch++)
	// {
	// reft += ch;
	// }
	// for (char ch = '0'; ch <= '9'; ch++)
	// {
	// reft += ch;
	// }
	//
	//
	//
	//
	// for (int i = 0; i < length; i++)
	// {
	// if (Rand.Next(3) == 0)
	// {
	// ret[i] = 0x20;
	// continue;
	// }
	// if (Rand.Next(3) == 1)
	// {
	// ret[i] = 0xa;
	// continue;
	// }
	//
	// ret[i] = (byte)Rand.Next(reft[reft.Length]);
	// }
	//
	// return ret;
	//
	// }

	/**
	 * @param 날짜
	 *            일
	 * @return
	 * @autor neo1seok
	 * @since 2014.01.17
	 */
	public static Date getDate(int day) {
		java.util.Calendar today = java.util.Calendar.getInstance();
		today.add(java.util.Calendar.DATE, day);
		java.util.Date tomorrow = today.getTime();
		return tomorrow;
	}

	/**
	 * @param 날짜
	 *            일
	 * @return
	 * @autor neo1seok
	 * @since 2014.01.17
	 */
	public static Date addDate(Date date, int day) {
		java.util.Calendar today = new GregorianCalendar();

		today.set(Calendar.YEAR, date.getYear());
		today.set(Calendar.MONTH, date.getMonth());
		today.set(Calendar.DATE, date.getDate());
		today.add(java.util.Calendar.DATE, day);

		java.util.Date tomorrow = today.getTime();
		return tomorrow;
	}

	/**
	 * @param 날짜
	 *            일
	 * @return
	 * @autor neo1seok
	 * @since 2014.01.17
	 */
	public static String getDate(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String thedate = sdf.format(date);

		return thedate;
	}

	/**
	 * GetDatelong
	 * 
	 * @param year
	 *            the value used to set the <code>YEAR</code> calendar field in
	 *            the calendar.
	 * @param month
	 *            the value used to set the <code>MONTH</code> calendar field in
	 *            the calendar. Month value is 1~12.
	 * @param dayOfMonth
	 *            the value used to set the <code>DAY_OF_MONTH</code> calendar
	 *            field in the calendar.
	 * @param hourOfDay
	 *            the value used to set the <code>HOUR_OF_DAY</code> calendar
	 *            field in the calendar.
	 * @param minute
	 *            the value used to set the <code>MINUTE</code> calendar field
	 *            in the calendar.
	 * @param second
	 *            the value used to set the <code>SECOND</code> calendar field
	 *            in the calendar.
	 */
	public static long GetDateTimelong(int year, int month1to12,
			int dayofmonth, int hour, int min, int sec) {
		Calendar today = new GregorianCalendar(year, month1to12 - 1,
				dayofmonth, hour, min, sec);
		return today.getTimeInMillis();
	}

	public static long GetDatelong(long datetime) {
		Calendar today = new GregorianCalendar();
		today.setTime(new Date(datetime));

		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);

		return today.getTimeInMillis();
	}

	/**
	 * @param 날짜
	 *            일
	 * @return
	 * @author neo1seok
	 * @since 2014.01.17
	 */
	public static long GetTodaylong() {
		Calendar today = new GregorianCalendar();

		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);

		return today.getTimeInMillis();
	}

	public static long GetNowlong() {
		Calendar today = new GregorianCalendar();
		return today.getTimeInMillis();
	}

	/**
	 * @param ? n개 추가
	 * @returStn
	 * @autor neo1seok
	 * @since 2014.01.17
	 */
	public static String repeat(char c, int num) {
		// char c = '?';
		int number = num;
		char[] repeat = new char[number];
		Arrays.fill(repeat, c);
		return new String(repeat);
	}

	public static Map<String, String> GetMapFromArgs(String[] args) {
		Map<String, String> etcmapparam = Maps.newLinkedHashMap();

		for (int i = 0; i < args.length; i++) {

			String option = args[i].toUpperCase();
			option = option.replace("-", "");
			String value = "";
			try {
				value = args[Math.min(i + 1, args.length - 1)];
			} catch (Exception ex) {
			}

			if (value.startsWith("-")) {
				value = "";
			} else {
				i++;
			}

			etcmapparam.put(option, value);

		}
		return etcmapparam;

	}
	public static boolean isSafeEqual(String org,String dst){
		
		if(org !=null && org.equals(dst)){
			return true;
		}
		return false;
		
	}
}
