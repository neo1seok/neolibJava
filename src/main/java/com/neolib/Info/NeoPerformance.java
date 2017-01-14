package com.neolib.Info;

public class NeoPerformance
{
	public NeoPerformanceConter CPU;
	public NeoPerformanceConter mem;
	public NeoPerformanceConter addPageFault;
	public String m_procName;
	public int LogicalCoreCount;
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: public ulong PHYMEM;
	public long PHYMEM;

	public final float getCpuValue()
	{
		return CPU.getNextValue() / LogicalCoreCount;
	}

	public final float getMemoryValue()
	{
		return mem.getNextValue();
	}

	public final String getStrCpuValue()
	{
//C# TO JAVA CONVERTER TODO TASK: The '0:0.0' format specifier is not converted to Java:
		return String.format("{0:0.0}%%", getCpuValue());
	}

	public final String getStrMemoryValue()
	{
		return com.neolib.Util.NeoUtil.noteBytes((long)getMemoryValue());
	}
	public NeoPerformance(String procname)
	{
		m_procName = procname;
		Init();
	}
	private NeoPerformanceConter GetPerformCounter(String categoryName, String counterName, String instanceName)
	{
		NeoPerformanceConter ret = null;
		try
		{
			ret = new NeoPerformanceConter(categoryName, counterName, instanceName);
		}
		catch (java.lang.Exception e)
		{
			//ret = new PerformanceCounter();

		}
		return ret;
	}
	private void Init()
	{

		CPU = GetPerformCounter("Process", "% Processor Time", m_procName);

		mem = GetPerformCounter("Process", "Working Set", m_procName);

// 			mem = GetPerformCounter("Process", "Working Set - Private", m_procName);
// 			if(mem.IsNotAssigne ){
// 				mem = GetPerformCounter("Process", "Working Set", m_procName);
// 			}

		addPageFault = GetPerformCounter("Process", "Page Faults/sec", m_procName);

		//LogicalCoreCount = CpuInfo.GetLogicalCoreCount();
		//PHYMEM = MemInfo.GetPhysTotalMem();
	}

	public final String GetCurStateDef()
	{

		float fpagefault = addPageFault.getNextValue();
		return String.format("CPU:%1$s MEM:%2$s PF/s : %3$s ", getStrCpuValue(), getStrMemoryValue(), fpagefault);



	}
	public final String GetCurStateSHORT()
	{
		float fpagefault = addPageFault.getNextValue();
		return String.format("CPU:%1$s MEM:%2$s ", getStrCpuValue(), getStrMemoryValue());

	}
}