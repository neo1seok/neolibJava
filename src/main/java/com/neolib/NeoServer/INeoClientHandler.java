package com.neolib.NeoServer;

import java.io.IOException;
import java.net.Socket;

public interface INeoClientHandler {
	
	void Init();

	void SetParam(Object ...p);

	void RunClient(Object param) throws IOException;

	void RunClient() throws IOException;

	void Stop();



	void SetTcpClient(Socket client);

	Socket getTcpClient();

}
