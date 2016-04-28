package server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import protocol.Request;
import protocol.Response;

public class ChatTCPHandler extends Thread {
	private Socket s;
	
	private ChatTCP server;
	
	private Room room;
	private String nickname;
	
	public ChatTCPHandler(Socket s, ChatTCP server) {
		this.s = s;
		this.server = server;
	}

	public void run() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			out.flush();
			ObjectInputStream in = new ObjectInputStream(s.getInputStream());
			while (!s.isClosed()) {
				Request req = (Request) in.readObject();
				Response res = req.execute(server, this);
				// Some requests don´t generate responses, like Message
				if (res != null)
					out.writeObject(res);
			}
		} catch (IOException e) {
			// client disconnected
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		
		if (room != null)
			room.leave(this);
		System.out.println("Client exited");
	}

	public String getNickName() {
		return nickname;
	}

	public void setNickName(String nick) {
		nickname = nick;
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}
	
	public Room getRoom() {
		return this.room;
	}
	
	public Socket getSocket() {
		return s;
	}
}