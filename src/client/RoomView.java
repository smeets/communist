package client;

import java.util.List;

import gui.MenuWindow;
import gui.PromptWindow;
import protocol.JoinRequest;
import protocol.LeaveRequest;
import protocol.RoomsRequest;

public class RoomView extends MenuWindow {

	private ChatClient client;
	
	public RoomView(String title, ChatClient client) {
		super(title);
		this.client = client;
		reset();
	}
	
	public void setList(List<String> rooms) {
		reset();
		for (String room : rooms)
			add(room);
	}

	private void reset() {
		clear();
		add("[Create Room]");
		add("[Refresh]");
	}
	
	@Override
    public void menuItemSelected(String menuItem, int position) {
		System.out.println("Clicked " + menuItem + " " + position);
		
		if (position == 1) {
			client.sendRequest(new RoomsRequest());
			return;
		}	
		
		String room = menuItem;
		if (position == 0) {
			room = PromptWindow.displayPrompt(0, 0, "Create room", "Room name");
		}
		client.sendRequest(new LeaveRequest());
		client.sendRequest(new JoinRequest(client.name, room));
	}

}
