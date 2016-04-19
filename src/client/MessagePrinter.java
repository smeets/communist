package client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MessagePrinter extends Thread {
	private Socket s;
	
	public MessagePrinter(Socket s) {
		this.s = s;
	}
	
	public void run() {
		try {
			Scanner scan = new Scanner(s.getInputStream());
			while (!s.isClosed()) {
				System.out.println(scan.nextLine());
			}
			scan.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
