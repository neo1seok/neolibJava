package com.neolib.Info;

//using System.Linq;

public class NeoPerformanceConter
{
	private Object m_perfCounter = null;
	public NeoPerformanceConter(String categoryName, String counterName, String instanceName)
	{
		try
		{
			//m_perfCounter = new PerformanceCounter(categoryName, counterName, instanceName);
		}
		catch (java.lang.Exception e)
		{

		}

	}
	public final boolean getIsNotAssigne()
	{
		return m_perfCounter == null;
	}


	public final float getNextValue()
	{
		if (m_perfCounter == null)
		{
			return 0;
		}

		try
		{
			//return m_perfCounter.NextValue();
		}
		catch (java.lang.Exception e)
		{
			return 0;
		}
		return 0;

	}


}