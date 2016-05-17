package client;

import java.util.List;

public class GUI {

	private ChatClient client;
	private ChatView chat;
	private UserView users;
	private RoomView rooms;
	
	public GUI(String host, int port, String nick){
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
