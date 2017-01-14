package com.neolib.Info;

//C# TO JAVA CONVERTER WARNING: Java does not allow user-defined value types. The behavior of this class will differ from the original:
//ORIGINAL LINE: public struct VERIONINFO
public final class VERIONINFO
{
	public String version;
	public String content;
	public java.util.Date buildDate = new java.util.Date(0);
	public VERIONINFO()
	{
	}

	public VERIONINFO(String version_, String content_, java.util.Date buildDate_)
	{
		version = version_;
		content = content_;
		buildDate = buildDate_;

	}

	public VERIONINFO clone()
	{
		VERIONINFO varCopy = new VERIONINFO();

		varCopy.version = this.version;
		varCopy.content = this.content;
		varCopy.buildDate = this.buildDate;

		return varCopy;
	}
}