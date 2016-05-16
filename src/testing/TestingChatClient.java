package testing;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import protocol.JoinRequest;
import protocol.LeaveRequest;
import protocol.MembersRequest;
import protocol.MessageRequest;
import protocol.Request;
import protocol.RoomsRequest;

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
				Request r = null;
				if (msg.startsWith("/join")) {
					r = new JoinRequest("tempUser3", "tempRoom");
				} else if (msg.startsWith("/rooms")) {
					r = new RoomsRequest();
				} else if (msg.startsWith("/members")) {
					r = new MembersRequest("tempRoom");
				} else if (msg.startsWith("/leave")) {
					r = new LeaveRequest();
				} else {
					r = new MessageRequest(msg);
				}
				if (r != null) {
					out.writeObject(r);
					out.flush();
				}
			}
		} catch (IOException e) {
		}
		kb.close();
	}

	public static void main(String[] args) {
		// synopsis: java ChatClient machine port
		// if (args.length != 2) {
		// System.out.println("usage: java ChatClient machine port");
		// System.exit(1);
		// }

		String machine = "localhost";
		int port = 30000;

		new TestingChatClient(machine, port).run();
	}

}
