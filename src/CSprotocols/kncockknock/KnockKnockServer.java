package knockknock;
import java.net.*;
import java.io.*;

public class KnockKnockServer {
	
	public void activateServer()
	{
	int counter = 1;
	ServerSocket serverSocket = null;
	try
	{
		serverSocket = new ServerSocket(4444);
	}
	catch (IOException e)
	{
	System.err.println("Could not listen on port: 4444.");
	System.exit(1);
	}
	System.out.println("KnockKnock server selected.");
	while(true)
		{
	Socket clientSocket = null;
	try {
		clientSocket = serverSocket.accept();
		System.out.println("New client connected: " + counter++ );
		ConnectionHandlerKK handler = new ConnectionHandlerKK(clientSocket);
		new Thread(handler).start();
		
		} catch (IOException e) {
		System.err.println("Accept failed.");
		System.exit(1);
		}
		}
   }
}


