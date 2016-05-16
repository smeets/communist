package client;

import gui.ChatWindow;
import gui.ListWindow;
import gui.MenuWindow;
import protocol.Response;
import protocol.RoomsRequest;
import server.ChatTCP;
import testing.TestingChatClient;

public class GUI {
	
	
	
	public GUI(String [] s){
		
		

		
		ChatWindow cw = new ChatWindow(400, 210, "CommunistChat");
		cw.show();
		ListWindow lw = new ListWindow("Users");
		lw.show();
		MenuWindow mw = new MenuWindow("Rooms");
		RoomsRequest r = new RoomsRequest();
		mw.add(r.toString());
		mw.show();
//		
		cw.add("Welcome "+ s[2] +"!\n");
		
		new ChatTCP(Integer.parseInt(s[1])).start();
		System.out.println("Serversocket on port " + Integer.parseInt(s[1]));
//		new ChatClient(s[0],Integer.parseInt(s[1])).run();
		new ChatClient(s[0], Integer.parseInt(s[1]), s[2]).start();
	}
}
