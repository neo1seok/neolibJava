package com.neolib.Info;

//using System.Linq;

public enum PROCESSOR_CACHE_TYPE
{
	Unified(0),
	Instruction(1),
	Data(2),
	Trace(3);

	private int intValue;
	private static java.util.HashMap<Integer, PROCESSOR_CACHE_TYPE> mappings;
	private static java.util.HashMap<Integer, PROCESSOR_CACHE_TYPE> getMappings()
	{
		if (mappings == null)
		{
			synchronized (PROCESSOR_CACHE_TYPE.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, PROCESSOR_CACHE_TYPE>();
				}
			}
		}
		return mappings;
	}

	private PROCESSOR_CACHE_TYPE(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static PROCESSOR_CACHE_TYPE forValue(int value)
	{
		return getMappings().get(value);
	}
}