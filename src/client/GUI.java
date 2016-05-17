package client;

import java.util.List;

import gui.ChatWindow;
import gui.ListWindow;
import gui.MenuWindow;
import server.ChatTCP;

public class GUI {

	private ChatClient client;
	private ChatView chat;
	private UserView users;
	private RoomView rooms;
	
	public GUI(String [] s){
		String host = s[0];
		String nick = s[2];
		int port = Integer.parseInt(s[1]);
		
		//new ChatTCP(port).start();
		client = new ChatClient(host, port, nick, this);
		client.run();

		chat = new ChatView(400, 210, "CommunistChat", client);
		chat.show();
		
		users = new UserView("Users", client);
		users.show();
		
		rooms = new RoomView("Rooms", client);		
		rooms.show();

		chat.add("Welcome "+ nick +"!\n");
	}
	
	public void setRoomList(List<String> rooms) {
		this.rooms.setList(rooms);
	}
	
	public void setUserList(List<String> users) {
		this.users.setList(users);
	}
	
	public void addMessage(String user, String message) {
		chat.add(user + ": " + message);
	}
	
	public void info(String message) {
		chat.add(message);
	}
}
