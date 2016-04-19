package server;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

public class MessageThread extends Thread {
	private MailBox mail;
	private Vector<Socket> clients;
	
	public MessageThread(MailBox mail, Vector<Socket> clients) {
		this.clients = clients;
		this.mail = mail;
	}

	public void run() {
		while (true) {
			 String msg = mail.read();
			 for (Socket s : clients)
				try {
					s.getOutputStream().write(msg.getBytes());
				} catch (IOException e) {}

		}
	}
}
