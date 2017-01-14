package com.neolib.NeoMFW;


public class DefBaseManager extends BaseNeoManager
{
	//protected  int count = 0;
	private DefBufferManagerReal m_bufferManager;
	@Override
	public void OnStartLoop() throws Exception
	{
		super.OnStartLoop();
		m_bufferManager = new DefBufferManagerReal(m_managerGroup.bufferManager);
		//count = 0;
	}
// 		override public object GetInfo(int type)
// 		{
// 			return count;
// 		}
	@Override
	public void OnLoop() throws Exception
	{
		//LogView("{0}", this);
		//count++;
	}

}