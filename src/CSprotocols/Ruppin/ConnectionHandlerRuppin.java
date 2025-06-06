package CSprotocols.Ruppin;

import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class ConnectionHandlerRuppin implements Runnable{
	private Socket socket;
	private final ArrayList<Client>clientState;
	
	public ConnectionHandlerRuppin(Socket socket, ArrayList<Client> clientState) {
		this.socket = socket;
		this.clientState = clientState;
	}
	@Override
	public void run() {
		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String inputLine, outputLine;
			RuppinProtocol ruppinP = new RuppinProtocol(clientState);
			
			outputLine = ruppinP.processInput(null);
			out.println(outputLine);
			
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.equals("q")) break;
					outputLine = ruppinP.processInput(inputLine);
					out.println(outputLine);
			}
			out.close();
			in.close();
			socket.close();

		} catch (IOException e) {
			System.err.println("Problem connecting to server");
		}
	}
}
