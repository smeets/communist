package protocol;

import client.ChatClient;

public class UserJoinEvent extends Response {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5872613238753828540L;

	public UserJoinEvent() {}

	@Override
	public void execute(ChatClient client) {
		if (client.getRoom() != null)
			client.sendRequest(new MembersRequest(client.getRoom()));
	}

}
