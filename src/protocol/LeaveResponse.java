package protocol;

import client.ChatClient;

public class LeaveResponse extends Response {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2607565002296159176L;
	
	private boolean success;
	public LeaveResponse(boolean left) {
		success = left;
	}

	@Override
	public void execute(ChatClient client) {
		if (success) {
			client.getGUI().info("Left room " + client.getRoom());
			client.setRoom(null);
		} else {
			if (client.getRoom() != null)
				client.getGUI().info("Coudln't leave room");
		}
	}

}
