package client;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {
	private BufferedReader in;
	private static String message = null;
	private GUI g = new GUI();
	public static void main(String[] args) throws IOException, InterruptedException {
		BufferedReader b = new BufferedReader(new FileReader(new File("ip.txt"))); //this needs to be revised at some point
		Socket s = new Socket(b.readLine(), 45454);
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		Scanner scan = new Scanner(System.in);
		new Thread(new Client(in)).start();
		while(true) {
			if (Client.message != null) {
				out.println(Client.message);
				setMessage(null);
			}
			out.flush();
		}
	}
	public Client(BufferedReader in) throws IOException {
		this.in = in;
	}
	public void run() {
		while (true) {
			try {
				Thread.sleep(5);
				if (in.ready()) {
					g.getChat().append(in.readLine() + "\n");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public synchronized static void setMessage(String message) {
		Client.message = message;
	}
}
