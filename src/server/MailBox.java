package server;

public class MailBox {
	String line;

	public synchronized void write(String s) {
		while (line != null) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 
		
		line = s;
		notifyAll();
	}

	public synchronized String read() {
		while (line == null) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 
		
		String temp = line;
		line = null;
		notifyAll();
		return temp;
	}

}
