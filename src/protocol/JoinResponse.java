package protocol;

import client.ChatClient;

// Server --> JoinPacket --> Client

public class JoinResponse extends Response {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7671488413310383217L;
	private boolean success;
	private String message, room;
	
	public JoinResponse(boolean success, String message, String room) {
		this.success = success;
		this.message = message;
		this.room = room;
	}
	
	@Override
	public void execute(ChatClient client) {
		if (success) {
			client.getGUI().info("Joined " + room);
			client.setRoom(room);
			client.sendRequest(new MembersRequest(room));
		} else
			client.getGUI().info(message);
		
		client.sendRequest(new RoomsRequest());
	}
	
	public String toString(){
		return message;
	}
	
}
