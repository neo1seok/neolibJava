package com.neolib.NeoMFW;

public interface INeoManager extends INeoInterface
{
	void OnInit()throws Exception;;
	void OnFinish()throws Exception;;
	void OnStartLoop()throws Exception;;
	void OnEndLoop()throws Exception;;
	void OnLoop() throws Exception;
	void OnProcException(Exception ex);

// 		void SetInfo(int infoType, object arg);
// 		object GetInfo(int infoType);

	void CtrlThread(CONTROLLTHREADTYPE ctrlType)throws Exception;;

}