package CSprotocols.Ruppin;
import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class RuppinServer{
		private final ArrayList<Client> clientState = new ArrayList<>();
		
	public void activateServer() {
		int counter = 1;
		ServerSocket serversocket = null;
		try {
			serversocket = new ServerSocket(4445);
		}
		catch (IOException e)
		{
		System.err.println("Could not listen on port: 4444.");
		System.exit(1);
		}
		System.out.println("Ruppin server selected.");
		while(true)
		{
		Socket clientSocket = null;	
		try {
			clientSocket = serversocket.accept();
			System.out.println("New client connected: " + counter++ );
			ConnectionHandlerRuppin handler = new ConnectionHandlerRuppin(clientSocket,clientState); 
			new Thread(handler).start();
		} 
		catch (IOException e) {
			System.err.println("Accept failed.");
			System.exit(1);
			}
		}
	}
}