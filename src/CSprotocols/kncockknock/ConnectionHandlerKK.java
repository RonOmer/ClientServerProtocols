package CSprotocols.kncockknock;
import java.io.*;
import java.net.Socket;

public class ConnectionHandlerKK implements Runnable {
	private Socket socket;
	
	public ConnectionHandlerKK(Socket socket) {
		this.socket = socket;
	}
	@Override
	public void run() {
		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String inputLine, outputLine;
			KnockKnockProtocol kkp = new KnockKnockProtocol();
			
			outputLine = kkp.processInput(null);
			out.println(outputLine);
			
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.equals("q")) break;
					outputLine = kkp.processInput(inputLine);
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
