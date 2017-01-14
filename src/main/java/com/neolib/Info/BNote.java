package com.neolib.Info;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;

public class BNote
{

	public enum BYTEUNIT
	{
		B,
		KB,
		MB,
		GB,
		TB;

		public int getValue()
		{
			return this.ordinal();
		}

		public static BYTEUNIT forValue(int value)
		{
			return values()[value];
		}
	}
	private static Map<BYTEUNIT,Long> m_hashtable = new LinkedHashMap<BYTEUNIT,Long>();
	private static Map<String,BYTEUNIT> m_hashtableStr = new LinkedHashMap<String,BYTEUNIT>();

	private static boolean IsInit = false;

	static
	{
		Init();
	}
	public static void Init()
	{
		if (IsInit == true)
		{
			return;
		}
		IsInit = true;


		long ll = 1;

		for (BYTEUNIT tmp : BYTEUNIT.values())
		{
			m_hashtableStr.put(tmp.toString(), tmp);
			m_hashtable.put(tmp, ll);
			ll *= 1024;

		}


	}
//	public static BYTEUNIT getByteUnit(String str){
//		for (BYTEUNIT tmp : BYTEUNIT.values())
//		{
//			if(tmp.toString().equals(str)){
//				return tmp;
//			}
//		}
//		return BYTEUNIT.B;
//	}
	public static String noteBytes(long bytes)
	{

		return noteBytes((double) bytes);
	}
	public static String noteBytes(double bytes)
	{
		double tmpByte = bytes;
		double prevByte = tmpByte;
		//string[] mb = new string[] { "B", "KB", "MB", "GB", "TB" };
		int count = 0;

		while (true)
		{
			tmpByte /= BN.KB;
			if (tmpByte < 1)
			{
				break;
			}
			prevByte = tmpByte;
			count++;
		}

		BYTEUNIT bunit = BYTEUNIT.forValue(count);
		DecimalFormat format = new DecimalFormat(".##");
		String str = format.format(prevByte);
		String ret = format.format(prevByte);
		if (prevByte == 0)
		{
			ret = (new Double(prevByte)).toString();
		}

		return ret + bunit.toString();
	}
	public static double ConvByte(long bytes, BYTEUNIT bunit)
	{


		//Init();
		int index = bunit.getValue();


		//long ubytes = (long) m_hashtable.get(bunit);
		Long ubytes = (Long) m_hashtable.get(bunit);


		return (double)bytes / ubytes;
	}
	public static double ConvByte(long bytes, String str)
	{
		;
		int bunit = -1;
		//Enum.GetValues()
//C# TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java:
		for (BYTEUNIT tmp : BYTEUNIT.values())
		{

			if (tmp.toString().equals(str))
			{
				bunit = (int)tmp.getValue();
				break;
			}
		}
		if (bunit < 0)
		{
			return -1;
		}

		return ConvByte(bytes, BYTEUNIT.forValue(bunit));

		//Init();

	}
	public static double ConvDoubleValue(String string){
		string = string.trim();
		String endchar = string.substring(string.length()-1,string.length());
		long productiveval = 1;
		if(endchar.equals("B")){
			endchar = string.substring(string.length()-2,string.length());
			string = string.replace(endchar, "");
			
			BYTEUNIT byteunit = m_hashtableStr.get(endchar);
			
			productiveval = m_hashtable.get(byteunit);
			
			
			
		}
		return Double.parseDouble(string) * productiveval;
				
	}
	public static long ConvLongValue(String string){
		string = string.trim();
		String endchar = string.substring(string.length()-1,string.length());
		long productiveval = 1;
		if(endchar.equals("B")){
			endchar = string.substring(string.length()-2,string.length());
			string = string.replace(endchar, "");
			
			BYTEUNIT byteunit = m_hashtableStr.get(endchar);
			
			productiveval = m_hashtable.get(byteunit);
			
			
			
		}
		return Long.parseLong(string) * productiveval;
				
	}

}