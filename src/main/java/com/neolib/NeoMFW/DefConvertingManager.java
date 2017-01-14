package com.neolib.NeoMFW;


public class DefConvertingManager extends DefBaseManager
{
	@Override
	public void OnLoop() throws Exception
	{
		super.OnLoop();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void OnProcException(Exception ex)
	{

	}
}