package protocol;

import java.util.List;

import client.ChatClient;

// Server --> JoinPacket --> Client

public class RoomsResponse extends Response {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2847536062556074514L;
	private List<String> rooms;
	
	public RoomsResponse(List<String> roomNames) {
		rooms = roomNames;
	}

	@Override
	public void execute(ChatClient client) {
		// TODO Auto-generated method stub
		
	}
	
	public String toString(){
		return rooms.toString();
	}
	
}
