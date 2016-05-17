package client;

import java.util.List;

import gui.ListWindow;

public class UserView extends ListWindow {
	private ChatClient client;
	
	public UserView(String title, ChatClient client) {
		super(title);
		this.client = client;
	}

	public void setList(List<String> users) {
		clear();
		for (String user : users)
			add(user);
	}

}
