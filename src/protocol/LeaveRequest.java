package protocol;

import server.ChatTCP;
import server.ChatTCPHandler;
import server.Room;

public class LeaveRequest extends Request {

	/**
	 * 
	 */
	private static final long serialVersionUID = -550966043558201976L;

	@Override
	public Response execute(ChatTCP server, ChatTCPHandler client) {
		Room room = client.getRoom();
		
		// Wtf u doin??
		if (room == null)
			return new LeaveResponse(false);
		
		room.leave(client);
		room.getClients().forEach(c -> c.respond(new UserLeaveEvent()));

		return new LeaveResponse(true);
	}

}
