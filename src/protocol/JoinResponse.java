package protocol;

import client.ChatClient;

// Server --> JoinPacket --> Client

public class JoinResponse implements Response {
	private boolean success;
	private String message;
	
	public JoinResponse(boolean success, String message) {
		this.success = success;
		this.message = message;
	}
	
	@Override
	public void execute(ChatClient client) {
		// TODO Auto-generated method stub
		
	}
	
}
