package client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.io.BufferedOutputStream;
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
import testing.MessagePrinter;

public class ChatClient extends Thread {
	public String name;
	private Socket s;
	
	public ChatClient(String host, int port, String name) {
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
					r = new JoinRequest(name, "tempRoom");
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
}
