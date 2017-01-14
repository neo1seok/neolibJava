package com.neolib.NeoThread;

import com.neolib.Util.NeoUtil;

public class ChildSampleNeoThread extends BaseNeoThread
{



//C# TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
		///#region const def
//C# TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
		///#endregion

//C# TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
		///#region private
//C# TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
		///#endregion

//C# TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
		///#region construct
	public ChildSampleNeoThread()
	{

	}
//C# TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
		///#endregion

//C# TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
		///#region public
//C# TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
		///#endregion

//C# TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
		///#region override method
	@Override
	protected void OnInitThread() //loop 시작전에 해야할 과정을 정의
	{
		m_logView.show.invoke ("InitThread");

	}
	@Override
	protected void OnEndThread() //루프 끝난후 과정을 정의
	{
		m_logView.show.invoke("EndThread");
	}

	@Override
	protected void OnLoop()
	//루프안에 들어가는 과정을 재정의
	//리턴이 false일 경우 루프에서 나감 
	{
		m_logView.show.invoke("DoLoopProcess");
		NeoUtil.Sleep(100);
	}

	@Override
	protected void OnProcessException(Exception ex)
	{
	//루프 중간에 Exception이 발생할경우 처리 

	}
//C# TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
		///#endregion


//C# TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
		///#region private method
//C# TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
		///#endregion

//C# TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
		///#region publci method
//C# TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
		///#endregion
}