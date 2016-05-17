package server;

import protocol.MessageResponse;

public class MessageThread extends Thread {
	private Room room;

	public MessageThread(Room room) {
		this.room = room;
	}

	public void send(ChatTCPHandler c, MessageResponse p) {
		c.respond(p);
	}

	public void run() {
		while (!room.isEmpty()) {
			MailBox.Message msg = room.getMailBox().read();
			MessageResponse p = new MessageResponse(msg.nick, msg.message);
			for (ChatTCPHandler c : room.getClients())
				send(c, p);
		}
	}
}
