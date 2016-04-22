package protocol;

import client.ChatClient;

public class MessageResponse implements Response {
	private String from;
	private String message;
	
	public MessageResponse(String from, String read) {
		message = read;
		this.from = from;
	}

	@Override
	public void execute(ChatClient client) {
		// client.printMessage(from, message);
	}

}
