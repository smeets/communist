package client;

import gui.ChatWindow;
import gui.ListWindow;
import gui.MenuWindow;
import server.ChatTCP;

public class GUI {
	
	
	
	public GUI(String [] s){
		
		
//		ChatClient cl = new ChatClient(s[0], Integer.parseInt(s[1]));
//		cl.run();
		
		ChatWindow cw = new ChatWindow(400, 210, "CommunistChat");
		cw.show();
		ListWindow lw = new ListWindow(260,210, "Users");
		lw.show();
		MenuWindow mw = new MenuWindow(260,410, "Rooms");
		mw.show();
		
		cw.messageEntered(s[2]);
	}
}
