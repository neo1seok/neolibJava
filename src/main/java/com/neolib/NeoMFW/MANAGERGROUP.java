package com.neolib.NeoMFW;


//C# TO JAVA CONVERTER WARNING: Java does not allow user-defined value types. The behavior of this class will differ from the original:
//ORIGINAL LINE: public struct MANAGERGROUP
	public final class MANAGERGROUP
	{
		//public NEOMANGERINFO[] neoManagers;
		public INeoInterface bufferManager;
		public NeoManagerFW manFrame;

		public MANAGERGROUP clone()
		{
			MANAGERGROUP varCopy = new MANAGERGROUP();

			varCopy.bufferManager = this.bufferManager;
			varCopy.manFrame = this.manFrame;

			return varCopy;
		}
	}