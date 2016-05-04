package gui;
import java.awt.*;
import java.awt.event.*;

/**
 * A ListWindow is a window which presents a list of strings and lets the user
 * select one of them. The ListWindow can for example be used for presenting
 * a list of chat participants and selecting one participant might be used
 * to indicate that a  private chat should be initiated. Input, that is
 * selecting a string in the list, is handled by
 * the <code>elementSelected</code> method which is called automatically
 * whenever the user selects an item in the list. The intention is that the
 * ListWindow class should be used as a super class to another class which
 * overloads the <code>elementSelected</code> method. The overloaded method
 * should contain the Java code to be executed, if any, when an element is
 * selected. It is also possible to ask the ListWindow at any time which
 * element is currently selected.
 * Another possible way of using the class is to modify the ListWindow class
 * directly, putting the code to be executed when a new message is entered
 * directly in the <code>elementSelected</code> method.
 *
 * @author  Roger Henriksson
 * @version 1.0 (2002-05-03)
 */
public class ListWindow {
    
    private Frame            theWindow;
    private List             theList;
    private boolean          destroyed;
    private ListHandler      handler;
    private SelectionHandler selector;

    /**
     * Constructs a ListWindow at a given position on the screen and with
     * a given title which is displayed in the title bar of the window. Note
     * that the window is initially invisible. You must call the
     * <code>show</code> method in order to display the window.<p>
     * <b>Note:</b> Depending on which window manager/window system
     * you use, both window title and position might be ignored.
     *
     * @param x      the x coordinate of the position of the window
     * @param y      the y coordinate of the position of the window
     * @param title  a String containing the title to be displayed in
     *               the title bar of the window
     */
    public ListWindow(String title) {
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	theWindow = new Frame(title);
	theWindow.setBounds(0, 0, 200, 472);
	theWindow.setLocation((int)(dim.getWidth()/2)+472,(int)(dim.getHeight()/2)-250);
	theList = new List(10,false);
	handler = new ListHandler();
	theList.addItemListener(handler);
	theWindow.add("Center",theList);
	theWindow.pack();
	destroyed = false;
  	selector = null;
  }
    
    /**
     * Adds a new element to the end of the list presented in the ListWindow.
     * 
     * @param element  a string containing the text which is to be displayed
     *                 in the ListWindow
     */
    public void add(String element) {
	if (!destroyed) {
	    theList.add(element);
	}
    }
    
    /**
     * Clears the contents of the ListWindow and removes all elements
     * in the list.
     */
    public void clear() {
	if (!destroyed) {
	    theList.removeAll();
	}
    }
    
    /**
     * Makes the ListWindow invisible. Call <code>show</code> to make the
     * window visible again.
     *
     * @see ListWindow#show
     */
    public void hide() {
	if (!destroyed) {
	    theWindow.setVisible(false);
	}
    }
    
    /**
     * Makes the ListWindow visible. ListWindows is initially invisible
     * when having been created. To actually make it visible you must call
     * the <code>show</code> method. Call <code>hide</code> to make the
     * window invisible again.
     *
     * @see ListWindow#hide
     */
    public void show() {
	if (!destroyed) {
	    theWindow.setVisible(true);
	}
    }
    
    /**
     * Destroys the ListWindow and frees all resources associated with it.
     * The <code>destroy</code> method should be called when the ListWindow
     * is not needed anymore. Note that it is <b>ILLEGAL</b> to call any
     * method of the ListWindow object once <code>destroy</code> has
     * been called.
     */
    public void destroy() {
	if (!destroyed) {
	    destroyed = true;
	    theWindow.dispose();
	}
    }
    
    /**
     * Returns the index of the currently selected item, if any, in the
     * list. The first item is numbered 0.
     *
     * @return the index of the selected item. If no item is selected
               the method returns -1.
     */
    public int getSelectedPosition() {
	int result;

	if (!destroyed) {
	    result = theList.getSelectedIndex();
	} else {
	    result = -1;
	}
	return result;
    }

    /**
     * Returns the text associated with the currently selected item.
     *
     * @return a string containing the text of the selected element. If
     *         no item is selected, null is returned.
    public String getSelected() {
	String result;
	if (!destroyed) {
	    result = theList.getSelectedItem();
	} else {
	    result = null;
	}
	return result;
    }


    /**
    * The <code>elementSelected</code> method is called whenever the user
    * selects an element in the ListWindow.
    * The intention is that this method should be overloaded in a
    * subclass to ListWindow. The default implementation found in the
    * ListWindow class does nothing.
    *
    * @param element   a String containing the text of the selected element
    * @param position  the index of the selected element 
    */
    public void elementSelected(String element,int position) {

    }
    
    private class ListHandler implements ItemListener {
	public void itemStateChanged(ItemEvent e) {
	    if (selector==null || !selector.isAlive()) {
		selector = new SelectionHandler(theList.getSelectedItem(),
			                        theList.getSelectedIndex());
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
	    elementSelected(item,pos);
	}
    }

}


