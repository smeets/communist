package protocol;

import client.ChatClient;

public class JoinResponsePacket implements Response {
	private boolean success;
	private String error;
	
	public JoinResponsePacket(boolean success, String error) {
		this.success = success;
		this.error = error;
	}
	
	@Override
	public void execute(ChatClient client) {
		// TODO Auto-generated method stub
		
	}
	
}
