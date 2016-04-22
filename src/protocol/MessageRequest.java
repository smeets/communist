package protocol;

import server.ChatTCP;
import server.ChatTCPHandler;
import server.Room;

// Client --> JoinPacket --> server

public class MessageRequest implements Request{

	private String msg;
	
	public MessageRequest(String message) {
		this.msg = message;
	}
	
	public Response execute(ChatTCP server, ChatTCPHandler client) {
		Room room = client.getRoom();
		
		// Wtf u doin client??
		if (room == null)
			return null;
		
		room.getMailBox().write(client.getNickName(), msg);
		return null;
	}
}
