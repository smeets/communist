package server;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Room {
	private MailBox mail;
	private MessageThread printer;
	private Map<String, ChatTCPHandler> nickToClient;
	private String name;
	private ChatTCP server;

	public Room(String name, ChatTCP server) {
		this.name = name;
		this.server = server;
		mail = new MailBox();
		nickToClient = new HashMap<String, ChatTCPHandler>();
		printer = new MessageThread(this);
	}

	public MailBox getMailBox() {
		return mail;
	}

	public synchronized Collection<ChatTCPHandler> getClients() {
		return nickToClient.values();
	}

	public String getName() {
		return name;
	}

	public synchronized boolean join(String nick, ChatTCPHandler client) {
		boolean wasEmpty = isEmpty();
		if (nickToClient.containsKey(nick)) {
			return false;
		}
		nickToClient.put(nick, client);
		// Prevent race-condition on create-room/client-join and message
		// thread exit
		if (wasEmpty) {
			printer.start();
		}
		client.setNickName(nick);
		client.setRoom(this);
		return true;

	}

	public synchronized void leave(ChatTCPHandler client) {
		String nick = client.getNickName();
		if (nickToClient.containsKey(nick)) {
			nickToClient.remove(nick);
			client.setRoom(null);
			client.setNickName(null);
			if (isEmpty()) {
				server.removeRoom(this);
			}
		}
	}

	public synchronized boolean isEmpty() {
		return nickToClient.isEmpty();
	}
}
