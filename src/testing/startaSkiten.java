package testing;

import server.ChatTCP;

public class startaSkiten {

	public static void main(String[] args) {
		new ChatTCP(30000).start();
		new TestingChatClient("localhost", 30000).start();
		
	}
	
	
}
