package protocol;

import server.ChatTCP;
import server.ChatTCPHandler;
import server.Room;

// Client --> JoinPacket --> server

public class JoinRequest implements Request{

	private String nick, room;
	
	public JoinRequest(String nickname, String room) {
		this.nick = nickname;
		this.room = room;
	}
	
	public Response execute(ChatTCP server, ChatTCPHandler client) {
		Room room = server.getRoom(this.room);
		
		// Room doesn't exist --> nick is not in room
		if (room == null) {
			room = server.createRoom(this.room);
			room.join(nick, client);
			return new JoinResponse(true, "success");
		}
		
		// Room exists --> try to join, but nick might be taken
		if (room.join(this.nick, client)) {
			return new JoinResponse(true, "success");
		}
		
		// Nick was taken
		return new JoinResponse(false, "nickname taken");
	}
}
