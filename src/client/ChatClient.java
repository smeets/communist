package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import protocol.Request;

public class ChatClient {
	public String name;
	private String room;
	private Socket s;
	private GUI gui;
	private ObjectOutputStream out;

	public ChatClient(String host, int port, String name, GUI gui) {
		this.gui = gui;
		this.name = name;
		try {
			s = new Socket(host, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendRequest(Request req) {
		if (out == null) {
			System.out.println("Wtf");
			return;
		}

		try {
			out.writeObject(req);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		new ResponseHandler(this, s).start();
		try {
			out = new ObjectOutputStream(s.getOutputStream());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public GUI getGUI() {
		return gui;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getRoom() {
		return room;
	}
}
