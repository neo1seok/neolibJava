package com.neolib.NeoServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.rmi.ServerException;
import java.text.MessageFormat;
import java.util.stream.Stream;

import com.neolib.Util.NeoHexString;


public class NeoDefClientHandler implements  INeoClientHandler
{


	private static String orgHexStr;
	public Socket tcpclient;
	protected boolean isRun;
	protected boolean _disposed;
	protected Object param;
	protected InputStream stream = null;
	protected OutputStream outstream = null;
	
	protected byte[] readbuffer;
	protected byte[] writebuffer;

	public  void SetTcpClient(Socket client)
	{
		tcpclient = client;
	}

	public  void Init()
	{

		isRun = true;

	}





	public  void Stop()
	{
		isRun = false;
	}

	protected  void WriteLog(String fmt,  Object... args)
	{
		System.out.println(MessageFormat.format(fmt, args));
		//Console.Write(fmt, args);

	}
	protected  void WriteLine(String fmt,  Object... args)
	{
		WriteLog(fmt + "\r\n", args);

	}
	public  void RunClient() throws IOException
	{
		RunClient(null);
	}
	/// <summary>
	/// 접속되고 나서 호툴 되는 부분 쓰래드 메소드
	/// </summary>
	/// <param name="param"></param>
	public  void RunClient(Object param) throws IOException
	{
		WriteLine("RunClient START");
		this.param = param;

		try
		{
			//client의 접속이 올때까지 block되는 부분(Thread)
			//백그라운드 thread에 처리 맡김
			//clientSocket = tcpListener.AcceptSocket();

			//클라이언트의 데이터를 읽고, 쓰기 위한 스트림을 만든다
			
			stream = tcpclient.getInputStream();
			outstream = tcpclient.getOutputStream(); 
			//stream = new NetworkStream(clientSocket);

			InitRun();
			


			//int session = 0;
			while (isRun)
			{
				if (!tcpclient.isConnected()) break;

				BeforeReadStream();

				ProcessRNS();
				//byte[] readbuffer = ReadFromStream(stream);
				//LoadFromReadBytes(readbuffer);


				//RunUnitProcess();




				//WriteSize(stream, GetWriteSize());
				//WriteToStream(stream);
				AfterWriteStream();


			}
		}
		catch (SocketException excp)
		{
			//if (excp.ErrorCode != 10035)
			{// WSAEWOULDBLOCK
			//	throw excp;
				WriteLine("socket 종료");
			}
		}

	
		catch (Exception e)
		{
			ProcessException(e);
		}
		finally
		{
            endRun();
			tcpclient.close();;
			tcpclient = null;
			
		}
		WriteLine("RunClient END");


	}

	public static int ReadIntFromStream(InputStream networkStream) throws IOException
	{
		byte[] bytesFrom = new byte[4];

		networkStream.read(bytesFrom, 0, 4);
		
		orgHexStr = NeoHexString.ByteArrayToHexStr(bytesFrom);
		int size = ByteBuffer.wrap(bytesFrom).order(ByteOrder.BIG_ENDIAN).getInt();
		//int size = reverseSize(BitConverter.ToInt32(bytesFrom, 0));
		return size;

	}
	
	public static byte[] ReadFromStream(InputStream networkStream) throws IOException
	{
		orgHexStr = "";
		int size = ReadIntFromStream(networkStream);
		//byte[] bytesFrom = new byte[4];
		//networkStream.Read(bytesFrom, 0, 4);
		//int size = reverseSize(BitConverter.ToInt32(bytesFrom, 0));
		byte[] bytesFrom = new byte[size];
		if(size>0) networkStream.read(bytesFrom, 0, size);
		orgHexStr += NeoHexString.ByteArrayToHexStr(bytesFrom);
		return bytesFrom;
	}
	public static void WriteSize(OutputStream networkStream, int size) throws IOException
	{
		
		byte[] sndbuff = ByteBuffer.allocate(4).putInt(size).array();;
		networkStream.write(sndbuff, 0, 4);
		orgHexStr = NeoHexString.ByteArrayToHexStr(sndbuff);

	}
	public static void WriteToStream(OutputStream networkStream, byte [] writeBuffer) throws IOException
	{
		orgHexStr = "";
		WriteSize(networkStream, writeBuffer.length);
		networkStream.write(writeBuffer, 0, writeBuffer.length);
		orgHexStr += NeoHexString.ByteArrayToHexStr(writeBuffer);
	}

	/// <summary>
	/// Clinet 와 접속후 종료될때까지 루프를 도는데 그 루프안에 
	/// READ와 WRITE를 하는 모든 과정 \n
	/// 디폴트는 에코서버로 전송해야할 데이터 사이즈를 4바이트 빅엔디안으로 주고 받은후 데이터를 보내거나 받는다.
	/// 4바잍 사이즈 수신
	/// 데이터 수신
	/// 4바이트 사이즈 송신
	/// 데이터 송신
	/// </summary>
	protected  void ProcessRNS() throws Exception
	{

		readbuffer = ReadFromStream(stream);
		int size = readbuffer.length/2;
		WriteLine("ROW RCV:{0}", orgHexStr);
		
		RunUnitProcess();

		size = writebuffer.length / 2;
		
		
		WriteToStream(outstream, writebuffer);
		WriteLine("ROW SND:{0}", orgHexStr);

		//WriteSize(stream, GetWriteSize());
		//WriteToStream();

	}

	protected  void ProcessException(Exception e)
	{
		if (e.getClass() == ServerException.class)
		{
			WriteLog(e.getMessage());
			return;
		}
		WriteLog(e.getMessage());
		WriteLog(e.getStackTrace().toString());
	}
	/// <summary>
	/// ProcessRNS 전에 진행할 부분
	/// </summary>
	protected  void BeforeReadStream()  throws Exception
	{
		
	}
	/// <summary>
	/// ProcessRNS 후에 진행할 부분
	/// </summary>
	protected  void AfterWriteStream() throws Exception
	{

	}
	/// <summary>
	/// 클라이언트 접속후 최초로 한번 호출됨
	/// 그 이후 루프를 돌게 된다.
	/// </summary>
	protected  void InitRun()  throws Exception
	{

	}
    protected  void endRun()  
    {
        
    }
	public  void SetParam(Object[] p)
	{

	}
	//protected  void WriteToStream()
	//{
	//    stream.Write(writebuffer, 0, writebuffer.length);
	//    //throw new NotImplementedException();
	//}


	//protected  int GetWriteSize()
	//{
	//    return writebuffer.length;
	//    //throw new NotImplementedException();
	//}
	/// <summary>
	/// 수신과 송신 사이 실제 동작을 하는 부분을 정의 한다.
	/// </summary>
	protected  void RunUnitProcess()
	{
		writebuffer = new byte[readbuffer.length];
		System.arraycopy(readbuffer,0,writebuffer,0,readbuffer.length);
		
		//throw new NotImplementedException();
	}

	//protected  void LoadFromReadBytes(byte[] readbuffer)
	//{
	//    //throw new NotImplementedException();
	//}
	public void Dispose() throws IOException
	{
		if (!_disposed)
		{
			Dispose(true);
		}

		_disposed = true;
	}

	protected  void Dispose(boolean disposing) throws IOException
	{
		
			if (disposing)
			{


				if (tcpclient != null)
				{
					tcpclient.close();
				}


			}
		
	}
	public Socket getTcpClient()
	{
		return this.tcpclient;
	}
	

}