package testing;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import protocol.JoinRequest;
import protocol.Request;

public class TestingChatClient extends Thread {
	
	private Socket s;
	
	public TestingChatClient(String host, int port) {
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
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			out.flush();
			while (!s.isClosed()) {
				String msg = kb.nextLine();
				if(msg.startsWith("/join")){
					System.out.println(msg);
					Request r = new JoinRequest("tempname", "temp");
					System.out.println("Made request");
					out.writeObject(r);
					System.out.println("Wrote request");
					out.flush();
					System.out.println("Sent /join request");
				}
			}
		} catch (IOException e) {}
		kb.close();
	}

	public static void main(String[] args) {
		// synopsis: java ChatClient machine port
//		if (args.length != 2) {
//			System.out.println("usage: java ChatClient machine port");
//			System.exit(1);
//		}
		
		String machine = "localhost";
		int port = 30000;
		
		new TestingChatClient(machine, port).run();
	}

}
