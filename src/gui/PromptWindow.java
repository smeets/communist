package gui;
import java.awt.*;
import java.awt.event.*;

/**
 * Implements pop-up input boxes which displays a message and prompts the
 * user for a text.<p>
 * Since the class only contains one static method and nothing else, it
 * should normally not be instantiated. The static method can be called
 * directly using <code>PromptWindow.displayPrompt("Title","A message.")</code>.
 *
 * @author Roger Henriksson
 * @version 1.0 (2002-04-29)
 */ 
public class PromptWindow {
   
    /**
     * Displays a prompt window containing a message and an input field.
     * The method blocks until the user enters a text.
     * <b>Note:</b> Depending on which window manager/window system
     * you use, both window title and position might be ignored.
     *
     * @param x        The x coordinate of the alert window.
     * @param y        The y coordinate of the alert window.
     * @param title    The string to be displayed in the title bar of
     *                 of the window associated with the alert box.
     * @param message  The string to be displayed in the alert box.
     * @return a string containing the entered text. 
     */
    public static String displayPrompt(int x,int y,String title,String message) {
	Frame theAlert = new Frame(title);
	theAlert.setLocation(x,y);

	Label theMessage = new Label(message);
	Panel messagePanel = new Panel();
	theAlert.add("Center",messagePanel);
	messagePanel.add(theMessage);

	Panel okPanel = new Panel();
	TextField inputField = new TextField("",30);
	okPanel.add(inputField);

	PromptHandler handler = new PromptHandler();
	inputField.addActionListener(handler);

	Button okButton = new Button("OK");
	okPanel.add(okButton);
	theAlert.add("South",okPanel);
	okButton.addActionListener(handler);

	theAlert.pack();
	theAlert.setVisible(true);
	
	handler.awaitSubmit();

	String result = inputField.getText();

	theAlert.dispose();
	return result;
    }

}

class PromptHandler implements ActionListener {
    boolean confirmed;
    
    public void actionPerformed(ActionEvent e) {
	confirmSubmit();
    }
    
    private synchronized void confirmSubmit() {
	confirmed = true;
	notify();
    }
    
    public synchronized void awaitSubmit() {
	if (!confirmed) {
	    try{
		wait();
	    } catch(InterruptedException e) { }
	}
    }
}
