package com.neolib.NeoMFW;

public enum MANAGERINFOTYPE
{
	SETCOVALUE(0),
	SETHANDLER(1),
	SETCURTHREAD(2),
	SETADMININFO(3),
	SETCUSTUMINFO(4),
	SETMANAGERNAME(5),
	SETTHREADCOUNT(6),
	GETTHREADCOUNT(7),
	GETONOPERATION(8),
	GETMANAGERNAME(9),
	DOTEST(10),
	CUSTUMCMD(10000);

	private int intValue;
	private static java.util.HashMap<Integer, MANAGERINFOTYPE> mappings;
	private static java.util.HashMap<Integer, MANAGERINFOTYPE> getMappings()
	{
		if (mappings == null)
		{
			synchronized (MANAGERINFOTYPE.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, MANAGERINFOTYPE>();
				}
			}
		}
		return mappings;
	}

	private MANAGERINFOTYPE(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static MANAGERINFOTYPE forValue(int value)
	{
		return getMappings().get(value);
	}
}