package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Vector;

public class ChatTCP extends Thread {
	private ServerSocket ss;

	private Vector<Room> rooms;

	public ChatTCP(int port) {
		rooms = new Vector<Room>();
		try {
			ss = new ServerSocket(port);
			System.out.println("Chat server listening on port " + port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (!ss.isClosed()) {
			try {
				new ChatTCPHandler(ss.accept(), this).start();
				System.out.println("New client connected");
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}

	public synchronized Room getRoom(String name) {
		for (Room r : rooms) {
			if (r.getName().equals(name)) {
				return r;
			}
		}
		Room room = new Room(name, this);
		rooms.add(room);
		return room;
	}
	
	public synchronized Vector<Room> getRooms() {
		return rooms;
	}

	public synchronized void removeRoomIfEmpty(Room room) {
		if (room.isEmpty())
			rooms.remove(room);
	}

	public static void main(String[] args) {
		int port = 30000;
		if (args.length == 1) {
			port = Integer.parseInt(args[0]);
		} else {
			System.out.println("Defaulting to port " + port);
		}
		new ChatTCP(port).run();
	}

}
