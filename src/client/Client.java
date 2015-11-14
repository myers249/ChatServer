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
	public static void main(String[] args) throws IOException, InterruptedException {
		BufferedReader b = new BufferedReader(new FileReader(new File("..\\ip.txt"))); //this needs to be revised at some point
		Socket s = new Socket(b.readLine(), 45454);
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		Scanner scan = new Scanner(System.in);
		new Thread(new Client(in)).start();
		while(true) {
			
			out.println(scan.nextLine());
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
					System.out.println(in.readLine());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
