package server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Server implements Runnable {
	Socket socket;
	static String message = "welcome to the server";
	static boolean ready = false;
	public Server(Socket socket) {
		this.socket = socket;
	}
	public void run() {
		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.println(Server.message);
			out.flush();
			while (true) {
				//System.out.println(Server.message);
				if (Server.ready) {
					out.println(Server.message);
					out.flush();
					//System.out.println(Server.message);
					Server.ready = false;
				}
				if (in.ready())
					getMessages(in);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public synchronized void getMessages(BufferedReader in) throws IOException {
		String message = in.readLine();
		setMessage(message);
		Server.ready = true;
	}
	private static void setMessage(String message) {
		Server.message = message;
	}
}
