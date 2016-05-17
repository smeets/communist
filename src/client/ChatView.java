package client;

import gui.ChatWindow;
import protocol.MessageRequest;

public class ChatView extends ChatWindow {
	private ChatClient client;
	
	public ChatView(int x, int y, String title, ChatClient client) {
		super(x, y, title);
		this.client = client;
	}
	
	@Override
	public void messageEntered(String msg) {
		if (client.getRoom() == null)
			return;
		client.sendRequest(new MessageRequest(msg));
	}

}
