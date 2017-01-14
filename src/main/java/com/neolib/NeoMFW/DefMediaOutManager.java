package com.neolib.NeoMFW;

import com.neolib.Util.NeoUtil;

public class DefMediaOutManager extends DefBaseManager
{
// 		override public void OnLoop()
// 		{
// 			LogView("{0}", this);
// 			object asdfas;
// 			m_managerGroup.bufferManager.RecvPacket(0, out asdfas, 0);
// 		}
	@Override
	public void OnLoop() throws Exception
	{
		super.OnLoop();
		NeoUtil.Sleep(200);
	}
	@Override
	public void OnProcException(Exception ex)
	{

	}

}