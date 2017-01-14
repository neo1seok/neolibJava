package com.neolib.NeoThread;

public enum THREADSTATE
{
	NOTRHEAD,
	INIT,
	WAITING,
	DOWORK,
	ENDTRREAD;

	public int getValue()
	{
		return this.ordinal();
	}

	public static THREADSTATE forValue(int value)
	{
		return values()[value];
	}
}