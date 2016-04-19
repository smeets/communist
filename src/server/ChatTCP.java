package server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ChatTCP {
	private ServerSocket ss;
	private MailBox mail;
	private Vector<Socket> clients;

	public ChatTCP(int port) {
		mail = new MailBox();
		clients = new Vector<Socket>();
		
		try {
			ss = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		new MessageThread(mail, clients).start();
		while (!ss.isClosed()) {
			try {
				Socket s = ss.accept();
				System.out.println("Client connected: " + s.getInetAddress());
				new ChatTCPHandler(s, mail, clients).start();	
			} catch (IOException e) {}
		}
	}

	public static void main(String[] args) {
		new ChatTCP(30000).run();
	}
}
