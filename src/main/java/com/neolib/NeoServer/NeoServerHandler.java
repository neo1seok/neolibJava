package com.neolib.NeoServer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class NeoServerHandler implements Runnable
{
	public  ServerSocket tcpListener = null;
	public  Thread serverThread = null;//new Thread(new ThreadStart(ServerHandler.Run));
	public  int port = 4951;
	protected int maxClient = 100;
	public  boolean isRun = true;
	//public List<Thread> listClientThread = new LinkedList<Thread>();
	int index = 0;
	public Map<Integer,ThreadSet> listINeoClientHandler = new LinkedHashMap<Integer,ThreadSet>();
	public List<Object> transferParams = new LinkedList<Object>();
	Class clienttype;

	class ThreadSet extends Thread{
		//public Thread thread;
		protected INeoClientHandler iNeoClientHandler;
		protected int index = 0;
		public ThreadSet( INeoClientHandler iNeoClientHandler,int index) {
			super();
			//this.thread = thread;
			this.iNeoClientHandler = iNeoClientHandler;
			this.index = index;
		}

		@Override
		synchronized public void run() {
			try {
				WriteLine("Thread Run");
				iNeoClientHandler.RunClient();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			WriteLine("Thread Rmove {0} bf" ,listINeoClientHandler.size());
			listINeoClientHandler.remove(index);
			
			WriteLine("Thread Rmove {0} af",listINeoClientHandler.size());
		}


	}
	
	public int getMaxClient() {
		return maxClient;
	}

	public boolean isRun() {
		return isRun;
	}

	public void setMaxClient(int maxClient) {
		this.maxClient = maxClient;
	}

	public void setRun(boolean isRun) {
		this.isRun = isRun;
	}

	public NeoServerHandler(int port, Class clienttype)
	{

		this.clienttype = clienttype;
		this.port = port;
		//transferParams.add(listINeoClientHandler);
	}

	public NeoServerHandler(int port)
	{
		this(port, NeoDefClientHandler.class);


	}
	public NeoServerHandler()
	{
		int asd = 0;

	}
	public   void Stop() throws InterruptedException
	{
		isRun = false;


		for (Entry<Integer, ThreadSet> tmp : listINeoClientHandler.entrySet())
		{
			tmp.getValue().iNeoClientHandler.Stop();
		}



		//if (tcpListener != null) tcpListener.Stop();:확인 필요

		System.out.printf("Stop");

		serverThread.join(1000);

		//	if (serverThread.IsAlive) serverThread.Abort();

		for (Entry<Integer, ThreadSet> tmp : listINeoClientHandler.entrySet())
		{
			//if (tmp.isAlive()) tmp.abort();::확인 필요 
		}



	}
	public  void Start()
	{
		isRun = true;
		//var sh = new NeoServerHandler();
		serverThread = 	new Thread(this);
		serverThread.start();

	}

	protected  void WriteLog(String fmt, Object ...args)
	{
		System.out.format(MessageFormat.format(fmt, args));


	}
	protected  void WriteLine(String fmt,Object ... args)
	{
		WriteLog(fmt +"\r\n", args);

	}
	protected  void ExceptionProcss(Exception ex)
	{

		try
		{
			throw ex;
		}
		//		catch (SocketException exa)
		//		{
		//			int asd = 0;
		//			WriteLine("LISTEN 종료");
		//		}
		catch(Exception exa)
		{
			WriteLine("Exception :" + ex);
		}

	}


	public void ClientWork(INeoClientHandler client){


	}
	public   void run()
	{

		WriteLine("Run");



		InitRun();

		//ClasslibIPST100Server.initServer();
		try
		{
			//ip주소를 나타내는 객체 생성. TcpListener생성시 인자로 사용

			//			String hostname = Dns.GetHostName();
			//			//IPAddress ipAd = IPAddress.Parse(hostname);
			//			System.Net.IPHostEntry host = System.Net.Dns.GetHostEntry(System.Net.Dns.GetHostName());


			//TcpListener class를 이용하여 클라이언트 연결 받아 들임

			tcpListener = new ServerSocket(port);
			//tcpListener.Start();

			WriteLine("MUITI Waiting for connections...(PORT:{0})", port);

			while (isRun)
			{
				//accepting the connection
				//tcpListener.AcceptTcpClient

				if (listINeoClientHandler.size() >= maxClient) continue;

				WriteLine("LISTENING THREAD NUM : {0} ", listINeoClientHandler.size());
				Socket client = tcpListener.accept();


				String address = client.getLocalAddress().toString();

				WriteLine("CONNECT CLIENT;{0} ", address,listINeoClientHandler.size());
				final INeoClientHandler cHandler = CreateInstance();
				cHandler.SetTcpClient(client);
				cHandler.SetParam(giveParams());
				cHandler.Init();


				//creating a new thread for the client
				ThreadSet clientThread = new ThreadSet(cHandler,index);
				listINeoClientHandler.put(index,clientThread);


				//listClientThread.add(clientThread);
				clientThread.start();
				index++;

			}
		}
		catch (Exception exp)
		{
			ExceptionProcss(exp);

		}
		finally
		{
			EndRun();
			//tcpListener.
			//tcpListener.stop();
		}
		WriteLine("END LOOP");
	}

	protected  void EndRun()
	{

	}

	protected  Object[] giveParams()
	{
		return new Object[0];

	}
	protected  INeoClientHandler CreateInstance() throws InstantiationException, IllegalAccessException
	{
		//		clienttype.getClass().newInstance();
		//		Object creater = Activator.CreateInstance(clienttype);

		return (INeoClientHandler)clienttype.newInstance();

	}

	protected  void InitRun()
	{

	}




}