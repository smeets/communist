package protocol;

import java.io.Serializable;

import server.ChatTCP;
import server.ChatTCPHandler;

public abstract class Request implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5151503426027533353L;

	public abstract Response execute(ChatTCP server, ChatTCPHandler client);
}
