package com.neolib.NeoMFW;

import com.neolib.NeoThread.BaseNeoThread;

//#endif

	public class IOThread extends BaseNeoThread
	{
		private INeoManager m_mediaHandling;
		private int m_count = 0;
		public final int getCurCount()
		{
			return m_count;
		}
		public final void setCurCount(int value)
		{
			m_count = value;
		}
// 		public IOThread(INeoManager mediaHandling)
// 		{
// 			m_mediaHandling = mediaHandling;
// 		}
		public IOThread(INeoManager mediaHandling, int waitDelay, boolean isAutoEvent) throws Exception
		{
			super(waitDelay, isAutoEvent);
			m_mediaHandling = mediaHandling;
			//m_mediaHandling.DoInterface(MANAGERINFOTYPE.SETCURTHREAD.getValue(), this);
			m_mediaHandling.DoInterface(MANAGERINFOTYPE.SETCURTHREAD.ordinal(), this);
		}
		public final void CountReset()
		{
			m_count = 0;
		}

		@Override
		protected void OnInitThread() throws Exception
		{
			super.OnInitThread();
			m_mediaHandling.OnInit();
			m_count = 0;
		}
		@Override
		protected void OnEndThread() throws Exception
		{
			super.OnEndThread();
			m_mediaHandling.OnFinish();
		}
		@Override
		protected void OnLoop() throws Exception
		{
			try
			{
				if (m_count == 0)
				{
					m_mediaHandling.OnStartLoop();
				}
				m_mediaHandling.OnLoop();

			}
//			catch (RuntimeException ex)
//			{
//				m_mediaHandling.OnProcException(ex);
//
//			}
			catch (Exception e)
			{
				m_mediaHandling.OnProcException(e);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			m_count++;
		}
		@Override
		protected void OnBreak() throws Exception
		{
			m_mediaHandling.OnEndLoop();
		}
		@Override
		protected void OnProcessException(Exception ex)
		//protected void OnProcessException(RuntimeException ex)
		{
			m_mediaHandling.OnProcException(ex);
		}
		@Override
		protected void Etc()
		{

		}
	}