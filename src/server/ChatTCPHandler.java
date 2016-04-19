package server;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class ChatTCPHandler extends Thread {
	private Socket s;
	private MailBox mail;
	private Vector<Socket> clients;

	public ChatTCPHandler(Socket s, MailBox mail, Vector<Socket> clients) {
		this.s = s;
		this.mail = mail;
		this.clients = clients;
		clients.add(s);
	}

	public void run() {
		try {
			Scanner in = new Scanner(s.getInputStream());
			OutputStream out = s.getOutputStream();
			while (!s.isClosed()) {
				String msg = in.nextLine() + '\n';
				if (msg.startsWith("Q:")) {
					s.close();
					break;
				} else if (msg.startsWith("M:")) {
					mail.write(msg.substring(3));
				} else if (msg.startsWith("E:")) {
					out.write(msg.getBytes());
				}

			}
		} catch (IOException e) {}
		clients.remove(s);
	}
}