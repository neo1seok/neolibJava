package com.neolib.NeoMFW;


public class DefMediaInManager extends DefBaseManager
{

// 		override public void OnLoop()
// 		{
// 			LogView("{0}", this);
// 			m_managerGroup.bufferManager.SendPacket(0,null,0);
// 		}
	private static int totcount = 0;
	@Override
	public void OnProcException(Exception ex)
	{

	}
// 		override public object GetInfo(int type)
// 		{
// 			if (type == 0) base.GetInfo(type);
// 
// 			return totcount;
// 		}

	@Override
	public void OnLoop() throws Exception
	{
		super.OnLoop();
		totcount++;
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}