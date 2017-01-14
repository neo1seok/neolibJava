package com.neolib.NeoMFW;

import com.neolib.NeoThread.STRLOGVIEW;

// 	public class COVALUE4LMFW{
// 		
// 		public object CustomInfo;
// // 		public STRLOGVIEW LogView;
// // 		public bool isAutoEvent;
// // 		public int defDelay;
// // 
// // 		public bool MediaInStart;
// // 		public bool MediaOutStart;
// // 		public bool ConvertingStart;
// 
// 		public COVALUE4LMFW()
// 		{
// 			LogView = new STRLOGVIEW();
// 			defDelay = 100;
// 			isAutoEvent = false;
// 			MediaInStart = false;
// 			MediaOutStart = false;
// 			ConvertingStart = false;
// 		}
// 	}
//C# TO JAVA CONVERTER WARNING: Java does not allow user-defined value types. The behavior of this class will differ from the original:
//ORIGINAL LINE: public struct NEOMANGERINFO
	public final class NEOMANGERINFO
	{
		public INeoManager NeoManager;
		public boolean IsStart;
		public int DelayTime;
		public boolean IsAutoEvent;
		public STRLOGVIEW LogView;
		public IOThread IoThread;
		public boolean IsAdmin;
		public NEOMANGERINFO()
		{
		}

		public NEOMANGERINFO(INeoManager NeoManager_, boolean IsStart_, int DelayTime_, boolean IsAutoEvent_, boolean IsAdmin_)
		{
			NeoManager = NeoManager_;
			IsStart = IsStart_;
			DelayTime = DelayTime_;
			IsAutoEvent = IsAutoEvent_;
			LogView = new STRLOGVIEW();
			IoThread = null;
			IsAdmin = IsAdmin_;
		}
		public NEOMANGERINFO(INeoManager NeoManager_, boolean IsStart_, int DelayTime_, boolean IsAutoEvent_)
		{
			this(NeoManager_, IsStart_, DelayTime_, IsAutoEvent_, false);
		}
		public NEOMANGERINFO(INeoManager NeoManager_)
		{
			this(NeoManager_, false);

		}
		public NEOMANGERINFO(INeoManager NeoManager_, boolean IsStart_)
		{
			this(NeoManager_, IsStart_, 0, false, false);

		}


		public NEOMANGERINFO clone()
		{
			NEOMANGERINFO varCopy = new NEOMANGERINFO();

			varCopy.NeoManager = this.NeoManager;
			varCopy.IsStart = this.IsStart;
			varCopy.DelayTime = this.DelayTime;
			varCopy.IsAutoEvent = this.IsAutoEvent;
			varCopy.LogView = this.LogView;
			varCopy.IoThread = this.IoThread;
			varCopy.IsAdmin = this.IsAdmin;

			return varCopy;
		}
	}