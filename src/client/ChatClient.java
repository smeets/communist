package client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
	
	private Socket s;
	
	public ChatClient(String host, int port) {
		try {
			s = new Socket(host, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		new MessagePrinter(s).start();
		Scanner kb = new Scanner(System.in);
		try {
			OutputStream out = s.getOutputStream();
			while (!s.isClosed()) {
				String msg = kb.nextLine() + '\n';
				out.write(msg.getBytes());
			}
		} catch (IOException e) {}
		kb.close();
	}

	public static void main(String[] args) {
		// synopsis: java ChatClient machine port
		if (args.length != 2) {
			System.out.println("usage: java ChatClient machine port");
			System.exit(1);
		}
		
		String machine = args[0];
		int port = Integer.parseInt(args[1]);
		
		new ChatClient(machine, port).run();
	}

}
