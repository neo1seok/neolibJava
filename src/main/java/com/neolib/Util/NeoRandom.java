package com.neolib.Util;


public class NeoRandom extends java.util.Random
{

	public final boolean getRandBool()
	{
		return (this.nextInt(1) == 1) ? true : false;
	}
	private int m_setFixedLength = -1;

	public final int getFixedLength()
	{
		return m_setFixedLength;
	}
	public final void setFixedLength(int value)
	{
		m_setFixedLength = value;
	}




//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: public byte[] GetRandData(int legth, bool isText)
	public final byte[] GetRandData(int legth, boolean isText)
	{
		return isText ? GetRandText(legth) : GetRandBin(legth);
	}

//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: public byte[] GetRandBin(int length)
	public final byte[] GetRandBin(int length)
	{
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: var ret = new byte[length];
		byte[] ret = new byte[length];
		this.nextBytes(ret);
		//NextBytes(ret);
		return ret;
	}
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: public byte[] GetRandText(int length)
	public final byte[] GetRandText(int length)
	{
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: var ret = new byte[length];
		byte[] ret = new byte[length];

		String reft = "";
		for (char ch = 'a'; ch <= 'z'; ch++)
		{
			reft += ch;
		}
		for (char ch = 'A'; ch <= 'Z'; ch++)
		{
			reft += ch;
		}
		for (char ch = '0'; ch <= '9'; ch++)
		{
			reft += ch;
		}




		for (int i = 0; i < length; i++)
		{
			
			if (next(10) == 0)
			{
				ret[i] = 0x20;
				continue;
			}
			if (next(20) == 1)
			{
				ret[i] = 0xa;
				continue;
			}

//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: ret[i] = (byte)reft[Next(reft.Length)];
			ret[i] = (byte)reft.charAt(next(reft.length()));
		}

		return ret;

	}

}