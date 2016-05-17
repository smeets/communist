package protocol;

import server.ChatTCP;
import server.ChatTCPHandler;
import server.Room;

// Client --> JoinPacket --> server

public class JoinRequest extends Request{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4278775019324931328L;
	private String nick, room;
	
	public JoinRequest(String nickname, String room) {
		this.nick = nickname;
		this.room = room;
	}
	
	public Response execute(ChatTCP server, ChatTCPHandler client) {
		Room room = server.getRoom(this.room);
		
		// Room exists --> try to join, but nick might be taken
		if (room.join(this.nick, client)) {
			room.getClients().forEach(c -> c.respond(new UserJoinEvent()));
			return new JoinResponse(true, "success", this.room);
		}
		// Nick was taken
		return new JoinResponse(false, "nickname taken", this.room);
	}
}
