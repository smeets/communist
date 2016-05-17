package client;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import protocol.Response;

public class ResponseHandler extends Thread {
	private Socket s;
	private ChatClient client;

	public ResponseHandler(ChatClient owner, Socket s) {
		this.s = s;
		client = owner;
	}

	public void run() {
		try {
			ObjectInputStream in = new ObjectInputStream(s.getInputStream());
			System.out.println("Client is accepting");
			while (!s.isClosed()) {
				Response r;
				try {
					r = (Response) in.readObject();
					r.execute(client);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
