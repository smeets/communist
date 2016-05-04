package client;

import gui.ChatWindow;
import gui.ListWindow;
import gui.MenuWindow;
import server.ChatTCP;
import testing.TestingChatClient;

public class GUI {
	
	
	
	public GUI(String [] s){
		
		

		
		ChatWindow cw = new ChatWindow(400, 210, "CommunistChat");
		cw.show();
		ListWindow lw = new ListWindow("Users");
		lw.show();
		MenuWindow mw = new MenuWindow("Rooms");
		mw.show();
//		
//		new ChatTCP(Integer.parseInt(s[1])).start();
//		new ChatClient(s[0],Integer.parseInt(s[1])).run();
		
	}
}
