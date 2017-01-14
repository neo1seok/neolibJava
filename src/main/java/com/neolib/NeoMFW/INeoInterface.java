package com.neolib.NeoMFW;

public interface INeoInterface
{
	Object DoInterface(int cmd, Object... wparam) throws Exception;

	// 		void SetInfo(int infoType, object arg);
	// 		object GetInfo(int infoType);
	// 
	// 		void SendPacket(int type, object input, int length,  object resstate);
	// 		object RecvPacket(int type, int length, object resstate);
	// 
	// 		int GetBuffCount(int buffType);
	// 
	// 		object CheckState(int checktype);



}