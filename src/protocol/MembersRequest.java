package protocol;

import static java.util.stream.Collectors.toList;

import java.util.List;

import server.ChatTCP;
import server.ChatTCPHandler;
import server.Room;

// Client --> JoinPacket --> server

public class MembersRequest extends Request{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4278775019324931328L;
	
	private String roomName;
	public MembersRequest(String room) {
		roomName = room;
	}
	
	public Response execute(ChatTCP server, ChatTCPHandler client) {
		Room room = server.getRoom(roomName);
		List<String> nicknames = room.getClients().stream().map(user -> user.getNickName()).collect(toList());
		
		// room might've been created if it didn't exist, so try and remove it
		server.removeRoomIfEmpty(room);
		
		return new MembersResponse(roomName, nicknames);
	}
}
