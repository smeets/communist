package protocol;

import java.io.Serializable;

import client.ChatClient;

public abstract class Response implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4977309440774100763L;

	public abstract void execute(ChatClient client);
}
