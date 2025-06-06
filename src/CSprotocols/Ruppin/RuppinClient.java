package CSprotocols.Ruppin;

import java.io.*;
import java.net.*;

public class RuppinClient {

	public static void main(String[] args) throws IOException {
		Socket ruppinSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		
		try {
			ruppinSocket = new Socket("127.0.0.1", 4445);
			out = new PrintWriter(ruppinSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(ruppinSocket.getInputStream()));
			
		}catch (UnknownHostException e) {
			System.err.println("Don't know about host: your host.");
			System.exit(1);
			} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to: your host.");
			System.exit(1);
			}
		
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String fromServer;
		String fromUser;
		
		while ((fromServer = in.readLine()) != null)
		{
			System.out.println("Server: " + fromServer);
			if (fromServer.equals("Thanks"))
				break;
			fromUser = stdIn.readLine();
			if (fromUser != null || fromUser.trim().isEmpty() == false) {
				System.out.println("Client: " + fromUser);
				out.println(fromUser);
			}
		}
		out.close();
		in.close();
		stdIn.close();
		
		ruppinSocket.close();
	}
}
