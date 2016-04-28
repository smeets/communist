package protocol;

import java.io.Serializable;

import client.ChatClient;

public abstract class Response implements Serializable {
	public abstract void execute(ChatClient client);
}
