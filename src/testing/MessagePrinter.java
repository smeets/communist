package testing;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import protocol.Response;

public class MessagePrinter extends Thread {
	private Socket s;

	public MessagePrinter(Socket s) {
		this.s = s;
	}

	public void run() {
		try {
			ObjectInputStream in = new ObjectInputStream(s.getInputStream());
			System.out.println("Client is accepting");
			while (!s.isClosed()) {
				Response r;
				try {
					r = (Response) in.readObject();
					System.out.println(r.toString());
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
