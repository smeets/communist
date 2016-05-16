package protocol;

import java.util.List;

import server.ChatTCP;
import server.ChatTCPHandler;
import static java.util.stream.Collectors.toList;

// Client --> JoinPacket --> server

public class RoomsRequest extends Request{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4278775019324931328L;
	
	public RoomsRequest() {}
	
	public Response execute(ChatTCP server, ChatTCPHandler client) {
		List<String> roomNames = server.getRooms().stream().map(room -> room.getName()).collect(toList());
		return new RoomsResponse(roomNames);
	}
}
