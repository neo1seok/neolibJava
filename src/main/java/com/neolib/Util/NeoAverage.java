package com.neolib.Util;

//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
//using System.Linq;


public class NeoAverage
{
	private Stopwatch m_sw = new Stopwatch();
	private java.util.LinkedList<Double> m_que = new java.util.LinkedList<Double>();
	private int m_aveCount;
	private long m_prevElapTime;


	public final void setSW(Stopwatch value)
	{
		m_sw = value;
	}

	public NeoAverage(int count)
	{
		m_aveCount = count;
	}
	public NeoAverage()
	{
		this(10);

	}
	public final String getBPS()
	{
		double d = m_que.peek();
		long x = (long) d; // x = 1234
	
		return NeoUtil.noteBytes(x);

	}
	public final String getAVGBPS()
	{
		return NeoUtil.noteBytes((long)GetAveg());

	}

	public final double GetAveg()
	{
		
		Double[] tmp = m_que.toArray(new Double[m_que.size()]);
		
		double[] d = new double[tmp.length] ;
		int i =0;
		for(Double tmp1:tmp){
			d[i++] = (double)tmp1;
		}
		
		
		return NeoUtil.GetAverage(d);
	}

	public final double GetAveg(double bps)
	{
		SetAveg(bps);
		return GetAveg();
	}

	public final void SetAveg(double bps)
	{
		if (bps < 0)
		{
			return;
		}

		m_que.offer(bps);

		if (m_que.size() > m_aveCount)
		{
			m_que.poll();
		}
	}
	public final void Start()
	{
		//m_sw.Reset();
		m_sw.start();
	}
	public final void Check(long bytes)
	{

		m_sw.stop();
		SetAveg((double)bytes / (m_sw.getElapsedTimeSecs() + 1) * 1000);
		//SetAveg((double)bytes * 2048);//  2012.12.05


	}



	public final void ReSet()
	{
		m_que.clear();
		//m_sw.Reset();
		m_sw.start();
		m_prevElapTime = 0;
	}
}