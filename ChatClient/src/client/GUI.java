package client;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI {
	
	private JTextArea chat;
	private JTextArea userList;
	public JTextArea getChat() {
		return chat;
	}
	public GUI() {
		JFrame frame = new JFrame("Chat Client");
		frame.setSize(565, 625);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(new Color(4, 152, 201));
		frame.setResizable(false);
		frame.setLayout(null);
		Button sendMessage = new Button("Send Message");
		sendMessage.setSize(100, 30);
		sendMessage.setLocation(300, 500);
		JTextField textBox = new JTextField();
		textBox.setSize(300, 30);
		textBox.setLocation(0, 500);
		textBox.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent keyEvent) {
				if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
					String message;
					if (!(message = textBox.getText()).equals("")) {
						Client.setMessage(message);
						textBox.setText("");
					}
				}
			}
			public void keyTyped(KeyEvent keyEvent) {}
			public void keyReleased(KeyEvent keyEvent) {}
		});
		sendMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message;
				if (!(message = textBox.getText()).equals("")) {
					Client.setMessage(message);
					textBox.setText("");
				}
			}
		});
		chat = new JTextArea();
		userList = new JTextArea();
		JScrollPane scroll = new JScrollPane(chat, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JScrollPane userScroll = new JScrollPane(userList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//chat.setSize(400, 500);
		//chat.setLocation(10, 0);
		chat.setLineWrap(true);
		chat.setLayout(null);
		chat.setEditable(false);
		chat.setForeground(Color.white);
		chat.setBackground(Color.black);
		userList.setLayout(null);
		userList.setEditable(false);
		userList.setForeground(Color.white);
		userList.setBackground(Color.black);
		scroll.setVisible(true);
		scroll.setSize(400, 500);
		scroll.setLocation(0, 0);
		userScroll.setVisible(true);
		userScroll.setSize(165, 500);
		userScroll.setLocation(400, 0);
		frame.add(sendMessage);
		frame.add(textBox);
		//frame.add(chat);
		frame.add(scroll);
		frame.add(userScroll);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public String getScreenName() {
		do {
		String user =  JOptionPane.showInputDialog(null, "Enter a screen name", "Screen Name");
		if (user == null || user.equals(""))
			continue;
		else 
			return user;
		}while (true);
	}
	public JTextArea getUserList() {
		return userList;
	}
	
}
