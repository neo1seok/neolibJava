package com.neolib.NeoServer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.neolib.Util.NeoHexString;


public class SampleHandler extends NeoDefClientHandler
{
	
	
	protected void ProcessRNS() throws IOException
	{
		//super.ProcessRNS();
		byte [] bheader = new byte[3];
		byte [] btail = new byte[2];
		//this.stream.read();
		int realsize = this.stream.read(bheader);
		this.WriteLine("realsize:{0} ", realsize);
		if(realsize < 0) {
			//throw new IOException();
			tcpclient.close();
		}
		;
		short  size = ByteBuffer.wrap(bheader,1,2).order(ByteOrder.BIG_ENDIAN).getShort();
		
		this.WriteLine("size:{0} {1} thd id:{2}", size,NeoHexString.ByteArrayToHexStr(bheader),Thread.currentThread().getId());
		
		if(size > 1024) {
			this.WriteLine("RETURN");
			this.stream.close();
			return ;
			
		}
		
		byte [] data = new byte[size];
		this.stream.read(data);

		this.stream.read(btail);
		
		
		String allbyte = NeoHexString.ByteArrayToHexStr(bheader)+NeoHexString.ByteArrayToHexStr(data)+NeoHexString.ByteArrayToHexStr(btail);
		this.WriteLine(" {0} size:{1} {2}", Thread.currentThread().getId(),size,allbyte);
		
		byte [] sndbuff = NeoHexString.HexStrToByteArray(allbyte);
		this.outstream.write(sndbuff);

	}
	protected void RunUnitProcess()
	{
		
	}


	
	public static void main( String[] args )
	{
		NeoServerHandler neoHandler = new NeoServerHandler(5510, SampleHandler.class);
		neoHandler.Start();
		

	}

}
