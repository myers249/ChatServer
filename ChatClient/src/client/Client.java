package client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable {
	private BufferedReader in;
	private PrintWriter out;
	private static String message = null;
	private  GUI g = new GUI();
	public static void main(String[] args) throws IOException, InterruptedException {
		BufferedReader b = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("client/ip.txt"))); //this needs to be revised at some point
		Socket s = new Socket(b.readLine(), 45454);
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		new Thread(new Client(in, out)).start();
		
		while(true) {
			if (Client.message != null) {
				out.println(Client.message);
				setMessage(null);
			}
			out.flush();
		}
	}
	public Client(BufferedReader in, PrintWriter out) throws IOException {
		this.in = in;
		this.out = out;
	}
	public void run() {
		String user = g.getScreenName();
		out.println(user);
		out.flush();
		String message = "";
		boolean go;
		while (true) {
			try {
				Thread.sleep(5);
				if (in.ready()) {
					do {
						if(!(message += in.readLine() + "\n").substring(0,2).equals("!!"))
							break;
					} while(in.ready());
					if (message.substring(0, 2).equals("!!")) {
						g.getUserList().setText(message.substring(2));
					} else 
						g.getChat().append(message);
					message = "";	
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
