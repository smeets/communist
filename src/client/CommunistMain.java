package client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class CommunistMain {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("CommunistChat");
		frame.setSize(250, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.add(panel);
		
		ImageIcon icon = new ImageIcon(CommunistMain.class.getResource("Soviet-flag.jpg"));
		JLabel label = new JLabel("", icon, JLabel.CENTER);
		panel.add( label, BorderLayout.CENTER );
		
		JLabel hostLabel = new JLabel("Host");
		hostLabel.setBounds(10, 10, 80, 25);
		panel.add(hostLabel);

		JTextField hostText = new JTextField(20);
		hostText.setBounds(100, 10, 160, 25);
		hostText.setText("localhost");
		panel.add(hostText);

		JLabel portLabel = new JLabel("Port");
		portLabel.setBounds(10, 40, 80, 25);
		panel.add(portLabel);

		JTextField port = new JTextField(20);
		port.setBounds(100, 40, 160, 25);
		port.setText("30000");
		panel.add(port);
		
		JLabel userLabel = new JLabel("KGB-Agent");
		userLabel.setBounds(10, 70, 80, 25);
		panel.add(userLabel);

		JTextField userText = new JTextField(20);
		userText.setBounds(100, 70, 160, 25);
		userText.setText("stalin");
		panel.add(userText);

		JButton loginButton = new JButton("login");
		loginButton.setBounds(10, 110, 80, 25);
		panel.add(loginButton);
		
		frame.setVisible(true);
		
		loginButton.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	     			frame.setVisible(false);
	     			frame.dispose(); 
	     			GUI gui = new GUI(hostText.getText(), Integer.parseInt(port.getText()), userText.getText());
	      }
	    });
	 
		            
		
	}

}
