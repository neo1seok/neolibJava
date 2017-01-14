package com.neolib.Util;

//using System.Linq;

public enum ERRSTATE
{
	NONE,
	ERROR,
	UNCERTAIN;

	public int getValue()
	{
		return this.ordinal();
	}

	public static ERRSTATE forValue(int value)
	{
		return values()[value];
	}
}