package com.neolib.Info;

//
//ORIGINAL LINE: [StructLayout(LayoutKind.Sequential)] public struct CACHE_DESCRIPTOR
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
public final class CACHE_DESCRIPTOR
{
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: public byte Level;
	public byte Level;
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: public byte Associativity;
	public byte Associativity;
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: public ushort LineSize;
	public short LineSize;
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: public uint Size;
	public int Size;
	public PROCESSOR_CACHE_TYPE Type = PROCESSOR_CACHE_TYPE.values()[0];

	public CACHE_DESCRIPTOR clone()
	{
		CACHE_DESCRIPTOR varCopy = new CACHE_DESCRIPTOR();

		varCopy.Level = this.Level;
		varCopy.Associativity = this.Associativity;
		varCopy.LineSize = this.LineSize;
		varCopy.Size = this.Size;
		varCopy.Type = this.Type;

		return varCopy;
	}
}