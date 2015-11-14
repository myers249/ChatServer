package server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LaunchServer {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(45454);
		System.out.println("server running...");
		while(true) {
			Socket socket = serverSocket.accept();
			Server server = new Server(socket);
			new Thread(server).start();
			System.out.println("Client connected");
		}
		//new Server(45454).run();
	}
}
