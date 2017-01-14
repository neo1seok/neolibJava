package com.neolib.NeoMFW;


/** 
 사용자가 정의 한다.
 
 
*/
//C# TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
//#if true
//C# TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
	///#region 사용자 정의 클래스

public class DefManagerFrame extends NeoManagerFW //NeoMangerFWGenForm<MediaInManager, MediaOutManager, NeoStateManager, NeoBufferManager>
{
	public DefManagerFrame(INeoInterface bufferManager, Object customInfo)
	{
		super(bufferManager, customInfo);

	}

	public static DefManagerFrame CreateManagerFrame(Object coval) throws Exception
	{
		//COVALUE4LMFW coval = new COVALUE4LMFW();

// 			MANAGERGROUP Handler = new MANAGERGROUP();
// 			Handler.mediaInManager = new DefMediaInManager();
// 			Handler.mediaOutManager = new DefMediaOutManager();
// 			Handler.convertingManager = new DefConvertingManager();
// 			Handler.stateManager = new DefStateManager();
// 			Handler.bufferManager = new DefBufferManager();
// 
		DefManagerFrame ret = new DefManagerFrame(new DefBufferManager(), coval);

		ret.Add("MediaIn", new DefMediaInManager());
		ret.Add("MediaOut", new DefMediaOutManager());
		ret.Add("Converting", new DefConvertingManager());

		ret.Add("State", new NEOMANGERINFO(new DefStateManager(), true));


		return ret;

	}

}