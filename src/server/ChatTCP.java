package server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ChatTCP {
	private ServerSocket ss;
	
	private Vector<Room> rooms;

	public ChatTCP(int port) {
		rooms = new Vector<Room>();
		try {
			ss = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while (!ss.isClosed()) {
			try {
				new ChatTCPHandler(ss.accept(), this).start();	
			} catch (IOException e) {
			}
		}
	}
	
	public Room createRoom(String name) {
		Room room = new Room(name, this);
		rooms.add(room);
		return room;
	}
	
	public Room getRoom(String name) {
		Room room = null;
		
		synchronized (rooms) {
			for (Room r : rooms) {
				if (r.getName().equals(name)) {
					room = r;
					break;
				}
			}
		}
		
		return room;
	}
	
	public void removeRoom(Room room) {
		synchronized (rooms) {
			rooms.remove(room);
		}
	}
	
	public static void main(String[] args) {
		new ChatTCP(30000).run();
	}

}
