package protocol;

import client.ChatClient;

public class MessageResponse extends Response {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5965567498383859252L;
	private String from;
	private String message;
	
	public MessageResponse(String from, String read) {
		message = read;
		this.from = from;
	}

	@Override
	public void execute(ChatClient client) {
		client.getGUI().addMessage(from, message);
	}
	
	@Override
	public String toString() {
		return from + ": " + message;
	}

}
