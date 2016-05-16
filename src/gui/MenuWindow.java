package gui;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


/**
 * A MenuWindow implements a programmerspecifiable menu window. The window
 * contains of a number of buttons, one for each menu alternative specified
 * by the application program. When the user clicks on one of the buttons,
 * the <code>menuItemSelected</code> method is automatically called.
 * The intention is that the MenuWindow class should be used as a super
 * class to another class which overloads the <code>menuItemSelcted</code>
 * method. The overloaded method should contain the Java code to be executed
 * when a menu item is selected. 
 * Another possible way of using the class is to modify the MenuWindow class
 * directly, putting the code to be executed when a new message is entered
 * directly in the <code>menuItemSelected</code> method.
 */
public class MenuWindow {
    
    private Frame theMenu;
    private GridLayout theLayout;
    private int menuCnt;
    private Vector<Button> buttons;
    private boolean destroyed;
    private ActionHandler handler;
    private SelectionHandler selector;

    /**
     * Creates an empty MenuWindow containing no menu items. The title
     * displayed in the title bar of the window and the initial position
     * of the window is given by the parameters. Note
     * that the window is initially invisible. You must call the
     * <code>show</code> method in order to display the window.<p>
     * <b>Note:</b> Depending on which window manager/window system
     * you use, both window title and position might be ignored.
     * 
     *
     * @param x      the x coordinate of the position of the window
     * @param y      the y coordinate of the position of the window
     * @param title  a String containing the title to be displayed in
     *               the title bar of the window
     */
    public MenuWindow(String title) {
	theMenu = new Frame(title);
	theMenu.setBounds(0, 0, 200, 470);
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	theMenu.setLocation((int) (dim.getWidth() / 2) - 475 - theMenu.getWidth(),
			(int) (dim.getHeight() / 2) - 250);
	theLayout = new GridLayout(1,1);
	theMenu.setLayout(theLayout);
	menuCnt = 0;
	buttons = new Vector<Button>();
	destroyed = false;
	handler = new ActionHandler();
	selector = null;
	this.addDefaults();
    }
    
    /**
     * Clears the menu from all menu items.
     */
    public void clear() {
	if (!destroyed) {
	    theLayout.setRows(1);
	    for(int i=0;i<buttons.size();i++) {
		theMenu.remove((Component)buttons.elementAt(i));
	    }
	    menuCnt = 0;
	    buttons.clear();
	}
    }
    
    public void addDefaults() {
//		add("[Create]");
//		add("[Delete]");
	}
    
    /**
     * Adds a new menu item to the bottom of the menu.
     *
     * @param menuItem  A string containing the text to be displayed
     *                  for this menu item.
     */
    public void add(String menuItem) {
	if(!destroyed) {
	    menuCnt++;
	    theLayout.setRows(menuCnt);
	    Button b = new Button(menuItem);
	    buttons.add(b);
	    theMenu.add(b);
	    b.addActionListener(handler);
	    theMenu.pack();
	    theMenu.setSize(200, 470);
	}
    }
    
    /**
     * Destroys the MenuWindow and frees all resources associated with it.
     * The <code>destroy</code> method should be called when the MenuWindow
     * is not needed anymore. Note that it is <b>ILLEGAL</b> to call any
     * method of the MenuWindow object once <code>destroy</code> has
     * been called.
     */
    public void destroy() {
	if (!destroyed) {
	    destroyed = true;
	    theMenu.dispose();
	}
    }
    
    /**
     * Makes the MenuWindow invisible. Call <code>show</code> to make the
     * window visible again.
     *
     * @see MenuWindow#show
     */
    public void hide() {
	if (!destroyed) {
	    theMenu.setVisible(false);
	}
    }
    
    /**
     * Makes the MenuWindow visible. MenuWindows is initially invisible
     * when having been created. To actually make it visible you must call
     * the <code>show</code> method. Call <code>hide</code> to make the
     * window invisible again.
     *
     * @see MenuWindow#hide
     */
    public void show() {
	if (!destroyed) {
	    theMenu.setVisible(true);
	}
    }
    
    /**
     * The <code>menuItemSelected</code> method is called whenever the user
     * clicks on one of the buttons in the menu window.
     * The intention is that this method should be overloaded in a
     * subclass to MenuWindow. The default implementation found in the
     * MenuWindow class does nothing.
     *
     * @param menuItem  a string containing the text on the selected button
     * @param position  the position of the button within the menu. The
     *                  first button is numbered 0.
     */
    public void menuItemSelected(String menuItem,int position) {
	
    }


    private class ActionHandler implements ActionListener {
	
	public void actionPerformed(ActionEvent e) {
	    int i = 0;
	    boolean found = false;
	    while(!found && i<buttons.size()) {
		if (((Button) buttons.elementAt(i))==((Button) e.getSource()))
		    found = true;
		else
		    i++;
	    }
            if (found && (selector==null || !selector.isAlive())) {
		selector = new SelectionHandler(((Button) e.getSource()).getLabel(),i);
		selector.start();
	    }
	}
    }
 
    private class SelectionHandler extends Thread {
	private String item;
	private int pos;

	public SelectionHandler(String item,int pos) {
	    this.item = item;
	    this.pos = pos;
	}

	public void run() {
	    menuItemSelected(item,pos);
	}
    }
   
}
