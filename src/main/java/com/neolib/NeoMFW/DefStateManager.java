package com.neolib.NeoMFW;

import com.neolib.Util.NeoUtil;

public class DefStateManager extends DefBaseManager
{

	protected INeoManager m_mediaInManager;
	protected INeoManager m_convertingManager;
	protected INeoManager m_mediaOutManager;


	@Override
	public void OnStartLoop()
	{
		int asd = 0;
		m_mediaInManager = m_managerGroup.manFrame.getManagerInfo().get("MediaIn").NeoManager;
		m_mediaOutManager = m_managerGroup.manFrame.getManagerInfo().get("MediaOut").NeoManager;
		m_convertingManager = m_managerGroup.manFrame.getManagerInfo().get("MediaOut").NeoManager;
	}
	@Override
	public void OnLoop() throws Exception
	{
		super.OnLoop();

		LogView.invoke("{0} {1} {2} {3}", this.DoInterface(MANAGERINFOTYPE.GETTHREADCOUNT.getValue()), m_mediaInManager.DoInterface(MANAGERINFOTYPE.GETTHREADCOUNT.getValue()), m_mediaInManager.DoInterface(MANAGERINFOTYPE.GETTHREADCOUNT.getValue()), m_mediaInManager.DoInterface(MANAGERINFOTYPE.GETTHREADCOUNT.getValue()), 0);
		NeoUtil.Sleep(200);


	}
	@Override
	public void OnProcException(Exception ex)
	{

	}
}