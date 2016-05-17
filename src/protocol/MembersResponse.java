package protocol;

import java.util.List;

import client.ChatClient;

// Server --> JoinPacket --> Client

public class MembersResponse extends Response {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2847536062556074514L;
	private List<String> nicknames;
	private String room;
	
	public MembersResponse(String room, List<String> nicks) {
		this.room = room;
		nicknames = nicks;
	}

	@Override
	public void execute(ChatClient client) {
		client.getGUI().setUserList(nicknames);
	}
	
	public String toString(){
		return room + ":" + nicknames.toString();
	}
	
}
