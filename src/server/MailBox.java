package server;

public class MailBox {
	String line, from;

	public static class Message {
		public final String nick, message;

		public Message(String n, String m) {
			nick = n;
			message = m;
		}
	}

	public synchronized void write(String nick, String msg) {
		while (line != null) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		line = msg;
		from = nick;
		notifyAll();
	}

	public synchronized Message read() {
		while (line == null) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		String temp = line;
		String nick = from;

		from = null;
		line = null;

		notifyAll();
		return new Message(nick, temp);
	}

}
