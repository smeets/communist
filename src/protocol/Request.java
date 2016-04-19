package protocol;

import server.ChatTCP;

public interface Request {
	public void execute(ChatTCP tcp);
}
