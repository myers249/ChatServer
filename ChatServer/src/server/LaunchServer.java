package server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class LaunchServer {
	static ArrayList<Server> servers = new ArrayList<Server>();
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(45454);
		System.out.println("server running...");
		while(true) {
			Socket socket = serverSocket.accept();
			Server server = new Server(socket);
			servers.add(server);
			new Thread(server).start();
			System.out.println("Client connected");
		}
		//new Server(45454).run();
	}
	public static void setHasRefreshed() {
		for (Server s : servers)
			s.hasRefreshedUserList = false;
	}
}
