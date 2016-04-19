package protocol;

import server.ChatTCP;

// Packet --> server
// 

public class JoinRequestPacket implements Request{

	private String nick, room;
	public JoinRequestPacket(String nickname, String room) {
		this.nick = nickname;
		this.room = room;
	}
	
	public void execute(ChatTCP server) {
		// server.getRoom(room) --> Room
		// room.getUser(nick) --> User
		// if user != null: 
		//		send error
		// else:
		//		room.addUser(nick);
		// 		send ok
	}
	
	public String getNickname() { return this.nick; }
	public String getRoom() { return this.room; }
}
