package client;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Participants {

	ArrayList<Socket> socketList;

	public Participants() {
		socketList = new ArrayList<Socket>();
	}

	public void addSocket(Socket socket) {
		socketList.add(socket);
	}

	public void removeSocket(Socket socket) {
		socketList.remove(socket);
	}

	public void broadcast(String message) {
		for (Socket socket : socketList) {
			try {
				socket.getOutputStream().write(message.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

