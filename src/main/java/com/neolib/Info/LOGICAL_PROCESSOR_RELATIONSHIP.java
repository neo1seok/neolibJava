package com.neolib.Info;

//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: public enum LOGICAL_PROCESSOR_RELATIONSHIP : uint
public enum LOGICAL_PROCESSOR_RELATIONSHIP 
{
	ProcessorCore(0),
	NumaNode(1),
	RelationCache(2),
	RelationProcessorPackage(3);

	private int intValue;
	private static java.util.HashMap<Integer, LOGICAL_PROCESSOR_RELATIONSHIP> mappings;
	private static java.util.HashMap<Integer, LOGICAL_PROCESSOR_RELATIONSHIP> getMappings()
	{
		if (mappings == null)
		{
			synchronized (LOGICAL_PROCESSOR_RELATIONSHIP.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, LOGICAL_PROCESSOR_RELATIONSHIP>();
				}
			}
		}
		return mappings;
	}

	private LOGICAL_PROCESSOR_RELATIONSHIP(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static LOGICAL_PROCESSOR_RELATIONSHIP forValue(int value)
	{
		return getMappings().get(value);
	}
}