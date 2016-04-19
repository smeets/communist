package client;

import gui.ChatWindow;
import gui.ListWindow;
import gui.MenuWindow;

public class GUITest {
	public static void main(String[] args) {
		new ChatWindow(0, 0, "Test").show();
		new ListWindow(0, 0, "Test").show();
		new MenuWindow(0, 0, "Test").show();

	}
}
