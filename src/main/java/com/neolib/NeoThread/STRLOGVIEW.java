package com.neolib.NeoThread;

public class STRLOGVIEW
{
	public ShowState show;
	public ClearState clear;
	public STRLOGVIEW()
	{
		show = new ShowState()
		{
			//@Override
			public void invoke(String fmt, Object... args)
			{
				//System.out.println(String.format(fmt, args));
			}
		};
		clear = new ClearState()
		{
			//@Override
			public void invoke()
			{
			}
		};

	}
}