
package protocol;
import client.ChatClient;

public class UserLeaveEvent extends Response {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5872613238753828540L;

	public UserLeaveEvent() {}

	@Override
	public void execute(ChatClient client) {
		client.sendRequest(new MembersRequest(client.getRoom()));
	}

}
