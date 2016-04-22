package protocol;

import server.ChatTCP;
import server.ChatTCPHandler;

public interface Request {
	public Response execute(ChatTCP server, ChatTCPHandler client);
}
