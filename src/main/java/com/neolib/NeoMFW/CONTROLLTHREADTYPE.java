package com.neolib.NeoMFW;

public enum CONTROLLTHREADTYPE
{
	STARTDOWORK,
	WAITDOWORK,
	FINISH,
	STARTTHREAD,
	FINISHTHREAD,
	COUNTRESET,
	WAITATDOWORK,
	FORCEEND;

	public int getValue()
	{
		return this.ordinal();
	}

	public static CONTROLLTHREADTYPE forValue(int value)
	{
		return values()[value];
	}
}