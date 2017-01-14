package com.neolib.NeoMFW;

import java.util.Map;
import java.util.Set;

// 	public struct ADMININFO{
// 		public NeoManagerFW manFrame;
// 		public IOThread InputThread;
// 		public IOThread outputThread;
// 		public IOThread convertingThread;
// 		public IOThread managerThread;
// 
// 	}
// 
	public class NeoManagerFW
	{
		protected MANAGERGROUP m_managerGroup = new MANAGERGROUP();
		//protected COVALUE4LMFW m_coValue;
		protected Object m_customInfo;

		protected Map<String,NEOMANGERINFO> m_listNeoManagerInfo = new java.util.HashMap<String,NEOMANGERINFO>();
//		List<IOThread> m_threaDs = new List<IOThread>();

		//ADMININFO m_adminInfo = new ADMININFO();
		private boolean isThreadStarted = false;

		public final Map<String, NEOMANGERINFO> getManagerInfo()
		{
			return m_listNeoManagerInfo;
		}

		public NeoManagerFW(INeoInterface bufferManager, Object customInfo)
		{
			m_managerGroup.bufferManager = bufferManager;
			m_managerGroup.manFrame = this;
			m_customInfo = customInfo;

		}


		public final void Add(String managerName, NEOMANGERINFO neoManagerInfo) throws Exception
		{

			
			neoManagerInfo.IoThread = new IOThread(neoManagerInfo.NeoManager, neoManagerInfo.DelayTime, neoManagerInfo.IsAutoEvent);
//			m_threaDs.Add(neoManagerInfo.IoThread);



			neoManagerInfo.NeoManager.DoInterface(MANAGERINFOTYPE.SETHANDLER.getValue(), neoManagerInfo.clone());
			neoManagerInfo.NeoManager.DoInterface(MANAGERINFOTYPE.SETCOVALUE.getValue(), m_customInfo);
			//if (neoManagerInfo.IsAdmin) 
			neoManagerInfo.NeoManager.DoInterface(MANAGERINFOTYPE.SETADMININFO.getValue(), m_managerGroup.clone());
			neoManagerInfo.NeoManager.DoInterface(MANAGERINFOTYPE.SETMANAGERNAME.getValue(), managerName);

			neoManagerInfo.IoThread.StartThread();
			
			m_listNeoManagerInfo.put(managerName, neoManagerInfo.clone());

		}
		public final void Add(String managerName, INeoManager iNeoManager) throws Exception
		{
			NEOMANGERINFO neoManagerInfo = new NEOMANGERINFO(iNeoManager);
			Add(managerName, neoManagerInfo.clone());
		}
		public final void Remove(String managerName) throws Exception
		{
			
			NEOMANGERINFO neoManagerInfo = m_listNeoManagerInfo.get(managerName);
			neoManagerInfo.IoThread.FinishThread();
			
			neoManagerInfo.IoThread.WaitAndForceEnd(5000);//neoManagerInfo.NeoManager.DoInterface(MANAGERINFOTYPE.SETCURTHREAD.getValue() );
			
			m_listNeoManagerInfo.remove(managerName);
			


		}
		
		private void RunLoops(CONTROLLTHREADTYPE conType) throws Exception
		{
			
			Set<String> set = m_listNeoManagerInfo.keySet();
			for (String tmpkey : set)
			{
				//m_listNeoManagerInfo.get(tmpkey);
				m_listNeoManagerInfo.get(tmpkey).NeoManager.CtrlThread(conType);
			}
		}

		public  void Start() throws Exception
		{
			//if (!isThreadStarted) StartThreads();

			Set<String> set = m_listNeoManagerInfo.keySet();
			for (String tmpkey : set){
				NEOMANGERINFO tmpinfo = m_listNeoManagerInfo.get(tmpkey);
				if (tmpinfo.IsStart)
				{
					tmpinfo.NeoManager.CtrlThread(CONTROLLTHREADTYPE.STARTDOWORK);
				}
				
			}
				
		}
		public  void Wait() throws Exception
		{
			RunLoops(CONTROLLTHREADTYPE.WAITDOWORK);
			// 			foreach (var tmp in m_threaDs)
			// 			{
			// 				tmp.WaitDoWork();
			// 			}
			// 		}
		}

// 		public void Stop()
// 		{
// 			Finish();
// 		}
		public  void Finish() throws Exception
		{


// 			foreach (var tmp in m_threaDs)
// 			{
// 				tmp.FinishThread();
// 			}
			FinshThread();

// 			foreach (var tmp in m_threaDs)
// 			{
// 				tmp.WaitAtDoWork();
// 			}
// 
			//RunLoops(CONTROLLTHREADTYPE.WAITATDOWORK);

		}
		public final void WaitAndForceEnd()
		{



		}

		public final void CountReSet() throws Exception
		{
			RunLoops(CONTROLLTHREADTYPE.COUNTRESET);
		}
		public final void ReInit() throws Exception
		{
			Finish();

			CountReSet();

			StartThreads();

		}


		protected final void StartThreads() throws Exception //loop 시작전에 해야할 과정을 정의
		{
// 			foreach (var tmp in m_threaDs)
// 			{
// 				tmp.StartThread();
// 			}
			RunLoops(CONTROLLTHREADTYPE.STARTTHREAD);
// 			foreach (var tmp in m_listNeoManagerInfo)
// 			{
// 				tmp.Value.NeoManager.CtrlThread(CONTROLLTHREADTYPE.STARTTHREAD);
// 			}
			isThreadStarted = true;
		}

		protected final void FinshThread() throws Exception //루프 끝난후 과정을 정의
		{
// 			foreach (var tmp in m_listNeoManagerInfo)
// 			{
// 
// 				tmp.Value.NeoManager.CtrlThread(CONTROLLTHREADTYPE.FINISHTHREAD);
// 			}
			
			Set<String> set = m_listNeoManagerInfo.keySet();
			for (String tmpkey : set){
				NEOMANGERINFO tmpinfo = m_listNeoManagerInfo.get(tmpkey);
				tmpinfo.NeoManager.CtrlThread(CONTROLLTHREADTYPE.FINISHTHREAD);
				tmpinfo.NeoManager.CtrlThread(CONTROLLTHREADTYPE.WAITATDOWORK);
				
			}

//C# TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java:
			

			isThreadStarted = false;


// 			foreach (var tmp in m_threaDs)
// 			{
// 				tmp.CurThread.Join();
// 			}

		}
		public final void ForceEnd() throws Exception //루프 끝난후 과정을 정의
		{
//C# TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java:
			
			Set<String> set = m_listNeoManagerInfo.keySet();
			for (String tmpkey : set){
				NEOMANGERINFO tmpinfo = m_listNeoManagerInfo.get(tmpkey);
				tmpinfo.NeoManager.CtrlThread(CONTROLLTHREADTYPE.FORCEEND);
			
			}
			

		}




	}