package com.neolib.NeoThread;


import com.neolib.Util.AutoResetEvent;
import com.neolib.Util.EventWaitHandle;
import com.neolib.Util.ManualResetEvent;
import com.neolib.Util.NeoUtil;

public class BaseNeoThread extends Thread
{
	protected Thread m_Thread;
	protected EventWaitHandle m_setEvent;

	protected boolean m_isRunning;

	protected int m_waitingTime;
	protected RuntimeException m_exp;
	protected Object m_lock;

	protected THREADSTATE m_threadState = THREADSTATE.NOTRHEAD;

	protected STRLOGVIEW m_logView; //.show = Console.WriteLine;
//		protected ClearState m_logClear = Console.Clear;

	public final EventWaitHandle getSetEvent()
	{
		return m_setEvent;
	}



	public final ShowState getLogView()
	{
		return m_logView.show;
	}
// 			set
// 			{
// 				m_logView.show = value;
// 			}

	public final ClearState getLogClear()
	{
		return m_logView.clear;
	}
// 			set
// 			{
// 				m_logView.clear = value;
// 			}


	//protected T m_threadParam;
	public final Thread getCurThread()
	{
		return this;
	}


	public final THREADSTATE getThreadState()
	{
		return m_threadState;
	}
	public final int getWaitTime()
	{
		return m_waitingTime;
	}
	public final void setWaitTime(int value)
	{
		m_waitingTime = value;
	}
	public final boolean getIsRunning()
	{
		return m_isRunning;
	}
	
	public BaseNeoThread(int waitDelay, boolean isAutoEvent)
	{
		//m_Thread = new Thread(new ThreadStart(DoThread));
		m_waitingTime = waitDelay;

		if (isAutoEvent)
		{
			m_setEvent = new AutoResetEvent(false);
		}
		else
		{
			m_setEvent = new ManualResetEvent(false);
		}

		//m_setEvent = new AutoResetEvent(false);

		m_lock = new Object();
		m_logView = new STRLOGVIEW();
	}

	public BaseNeoThread()
	{
		this(100, false);
// 			m_Thread = new Thread(new ThreadStart(DoThread));
// 			m_waitingTime = 0;
// 			m_setEvent = new ManualResetEvent(false);
// 			//m_setEvent = new AutoResetEvent(false);
// 
// 			m_lock = new object();
// 			m_logView = new STRLOGVIEW(0);

	}
	public BaseNeoThread(int waitDelay)
	{
		this(waitDelay, false);
		// 			m_Thread = new Thread(new ThreadStart(DoThread));
		// 			m_waitingTime = 0;
		// 			m_setEvent = new ManualResetEvent(false);
		// 			//m_setEvent = new AutoResetEvent(false);
		// 
		// 			m_lock = new object();
		// 			m_logView = new STRLOGVIEW(0);

	}

	public void SetLogView(ShowState show, ClearState clear)
	{
		m_logView.show = show;
		
		m_logView.clear = clear;
	}
	public void SetLogView(ShowState show)
	{
		m_logView.show = show;
		
	}
	
	public void run(){
		DoThread();
	}

	protected void DoThread()
	//기본적으로 루프를 기준으로 쓰레드를 실행 그러나 루프가 들어가지 않을 경우
	//해위 클래스에서 재정의 한다. 
	{
		try
		{
			m_threadState = THREADSTATE.INIT;
			OnInitThread();


			while (true)
			{
				m_threadState = THREADSTATE.WAITING;
				//m_setEvent.WaitOne();
				
				m_setEvent.waitOne();	
				
				
				
				if (m_isRunning == false)
				{
					OnBreak();
					break;
				}
				m_threadState = THREADSTATE.DOWORK;
				OnLoop();
				NeoUtil.Sleep(m_waitingTime);
			}

		}
		catch (RuntimeException  ex)
		{
			m_exp = (RuntimeException) ex;
			OnProcessException(ex);
		}
		catch ( InterruptedException ex)
		{
			//m_exp = (RuntimeException) ex;
			OnProcessException(ex);
		}
		catch ( Exception ex)
		{
			//m_exp = (RuntimeException) ex;
			OnProcessException(ex);
		}
		
		finally
		{

			try
			{
				OnEndThread();
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				OnProcessException(e);
			}
			m_threadState = THREADSTATE.NOTRHEAD;
		}

	}

	protected void OnInitThread() throws Exception //Thread 시작
	{
		//LogView("{0} InitThread",this);
	}
	protected void OnEndThread() throws Exception //Thread 끝난후 과정 정의.
	{
		//LogView("{0} EndThread", this);
	}


	protected void OnLoop() throws Exception
	//루프안에 들어가는 과정을 재정의
	//리턴이 false일 경우 루프에서 나감 
	{

	}
	protected void OnBreak() throws Exception
	//루프안에 들어가는 과정을 재정의
	//리턴이 false일 경우 루프에서 나감 
	{

	}


	protected void OnProcessException(Exception ex)
	{
	//루프 중간에 Exception이 발생할경우 처리 
		getLogView().invoke(ex.getMessage() + ex.getStackTrace());
	}
	protected void Etc()
	{

	}

	public void StartThread()
	{
		m_isRunning = true;
		//m_Thread = new Thread(new ThreadStart(DoThread));

		//m_Thread.start();
		this.start();

	}


	public void FinishThread()
	{
		m_isRunning = false;
		m_setEvent.set();
		//m_setEvent.

	}

	public void StartDoWork()
	{
		getSetEvent().set();
	}
	public void WaitDoWork()
	{
		getSetEvent().reset();
	}
	public void FinishDoWork()
	{

	}

	public final void ForcedEnd()
	{
		
		
		try {
			stop();
			this.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public final void WaitAndForceEnd()
	{
		WaitAndForceEnd(5000);
	}

	public final void WaitAndForceEnd(int waiteTime)
	{
		int count = 0;
		int unitTime = 100;
		int maxCount = waiteTime / unitTime;
		while (m_threadState != THREADSTATE.NOTRHEAD)
		{
			NeoUtil.Sleep(100);
			if (count > maxCount)
			{
				ForcedEnd();
				break;
			}

			count++;

			Etc();
		}
	}

	public final void DoSyncLoop()
	{
		EventWaitHandle orgEvent = m_setEvent;
		m_setEvent = new ManualResetEvent(false);
		StartThread();
		StartDoWork();

		WaitAndForceEnd();

	}


}