package com.neolib.NeoMFW;

import com.neolib.NeoThread.ShowState;

	public class BaseNeoManager implements INeoManager
	{
		protected ShowState LogView;
		protected Object m_coValue;
		protected MANAGERGROUP m_managerGroup = new MANAGERGROUP();
		protected NEOMANGERINFO m_curNeoManagerInfo = new NEOMANGERINFO();

		protected IOThread m_curThread;

		protected boolean m_isOnOperation;
		protected String m_managerName;



		public Object DoInterface(int cmd, Object... wparam)
		{
			MANAGERINFOTYPE infotype = MANAGERINFOTYPE.forValue(cmd);
			if(infotype == null ) return null;
			//object arg = wparam[0];

			switch (infotype)
			{
				case SETCOVALUE:
					m_coValue = (Object)wparam[0];
					//LogView = m_coValue.LogView.show;
					break;
				case SETHANDLER:
					m_curNeoManagerInfo = (NEOMANGERINFO)wparam[0];
					LogView = m_curNeoManagerInfo.LogView.show;
					break;

				case SETADMININFO:
					m_managerGroup = (MANAGERGROUP)wparam[0];
					//m_adminInfo = (ADMININFO)wparam[0];
					break;
				case SETMANAGERNAME:
					m_managerName = (String)wparam[0];
					//m_adminInfo = (ADMININFO)wparam[0];
					break;


				case SETCURTHREAD:
					m_curThread = (IOThread)wparam[0];
					break;
				case SETTHREADCOUNT:
					m_curThread.setCurCount((Integer)wparam[0]);
					break;

				case GETTHREADCOUNT:
					return m_curThread.getCurCount();

				case GETONOPERATION:
					return m_isOnOperation;

				case GETMANAGERNAME:
					return m_managerName;


				default:
					break;

			}

			return null;
		}


		public void OnInit()throws Exception
		{
			int asd = 0;
			m_isOnOperation = false;
		}

		public void OnStartLoop() throws Exception
		{


		}
		public void OnLoop() throws Exception
		{


		}
		public void OnEndLoop()throws Exception
		{


		}
		public void OnFinish()throws Exception
		{

		}
		public void OnProcException(Exception ex)
		{

		}
		
		@SuppressWarnings("deprecation")
		public void CtrlThread(CONTROLLTHREADTYPE ctrlType) throws Exception
		{
			switch (ctrlType)
			{
				case STARTDOWORK:
					m_curThread.StartDoWork();
					m_isOnOperation = true;
					break;
				case WAITDOWORK:
					m_curThread.WaitDoWork();
					m_isOnOperation = false;
					break;
				case FINISH:
					m_curThread.FinishDoWork();
					break;
				case STARTTHREAD:
					m_curThread.StartThread();
					break;
				case FINISHTHREAD:
					m_curThread.FinishThread();
					break;
				case COUNTRESET:
					m_curThread.CountReset();
					break;
					
				case WAITATDOWORK:
					try {
						m_curThread.getCurThread().join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

				case FORCEEND:
					m_curThread.getCurThread().destroy();
					break;



			}
		}

	}