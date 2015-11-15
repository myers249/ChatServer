package server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable {
	Socket socket;
	static String message;
	static boolean ready = false;
	static boolean newUser = false;
	boolean isNewUser = false;
	boolean hasRefreshedUserList = false;
	String user;
	static ArrayList<String> users = new ArrayList<String>();
	private static int counter = 0;
	public Server(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.println("Welcome to the server");
			out.flush();
			Server.newUser = true;
			user = in.readLine();
			Server.users.add(user);
			LaunchServer.setHasRefreshed();
			//out.println(user);
			while (true) {
				
				//System.out.println(Server.message);
				if (Server.ready) {
					out.println(Server.message);
					out.flush();
					//System.out.println(Server.message);
					Server.ready = false;
				}
				if (Server.newUser && !hasRefreshedUserList)
					setUserList(out);
				if (in.ready())
					getMessages(in);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public synchronized void getMessages(BufferedReader in) throws IOException {
		String message = in.readLine();
		setMessage(user + ": " + message);
		Server.ready = true;
	}
	private static void setMessage(String message) {
		Server.message = message;
	}
	private synchronized void setUserList(PrintWriter out) {
		String userList = "";
		for(String s : Server.users) {
			userList += s + "\n";
		}
		out.println("!!" + userList);
		out.flush();
		hasRefreshedUserList = true;
		Server.counter++;
		if (Server.users.size() == Server.counter) {
			Server.newUser = false;
		}
	}
}
