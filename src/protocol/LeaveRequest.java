package protocol;

import server.ChatTCP;
import server.ChatTCPHandler;
import server.Room;

public class LeaveRequest extends Request {

	@Override
	public Response execute(ChatTCP server, ChatTCPHandler client) {
		Room room = client.getRoom();
		
		// Wtf u doin??
		if (room == null)
			return null;
		
		room.leave(client);

		return null;
	}

}
