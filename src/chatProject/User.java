// Assignment: ChatProject
// Program:    User
// Created:    Mar 12, 2017
// Author:     lcattle - Lauren Ribeiro
//
package chatProject;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class User extends javax.swing.JFrame {
	String username;
	String address;
	String serverIP = "localhost";
	int port = 8090;
	Socket sock;
	BufferedReader reader;
	PrintWriter writer;
	Boolean online = false;
	private JTextArea chatDisplay;
	private JTextArea chatType;
	static int windowOffset = 0;

	public User() {
		createGUI();
	}

	public class InputReader implements Runnable {
		public void run() {
			String[] data;
			String stream;
			
			try {
				while ((stream = reader.readLine()) != null) {
					data = stream.split("~");
					chatDisplay.append(data[0] + ": " + data[1] + "\n");
					chatDisplay.setCaretPosition(chatDisplay.getDocument().getLength());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}

	public void createListener() {
		Thread inputReader = new Thread(new InputReader());
		inputReader.start();
	}
	
	private void createGUI() {
		chatDisplay = new JTextArea();
		chatType = new JTextArea();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		add(contentPanel);

		JPanel requestUser = new JPanel();
		JLabel userLabel = new JLabel("Enter your username: ");
		JTextField userName = new JTextField();
		userName.setColumns(20);
		userName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == new Integer(KeyEvent.VK_ENTER)) {
					username = userName.getText();
					connect();
				}
			}
		});
		JButton enter = new JButton("Enter");
		enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username = userName.getText();
				connect();
			}
		});
		requestUser.add(userLabel);
		requestUser.add(userName);
		requestUser.add(enter);
		contentPanel.add(requestUser);
		
		contentPanel.add(chatDisplay);
		JLabel messageInput = new JLabel("Type your message below:");
		contentPanel.add(messageInput);
		chatType.setRows(4);
		contentPanel.add(chatType);
		
		JButton send = new JButton("Send");
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendChat();
			}
		});
		contentPanel.add(send);
		
		setSize(new Dimension(500, 700));
		setLocation(windowOffset * 100, 125);
		setVisible(true);
		windowOffset++;
	}
	
	private void connect() {
		try {
			sock = new Socket(serverIP, port);
			reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			writer = new PrintWriter(sock.getOutputStream());
			writer.flush();
			//online = true;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		createListener();
	}
	
	private void sendChat() {
		try {
            writer.println(username + "~" + chatType.getText() + "~Chat");
            writer.flush(); 
         } catch (Exception ex) {
             chatDisplay.append("Message was not sent. \n");
         }
         chatType.setText("");
	}

	
	

//	private javax.swing.JTextArea chatDisplay;
//	private javax.swing.JTextArea chatType;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JButton sendButton;
	private javax.swing.JTextField usernameField;
	private javax.swing.JTextArea usersList;
	// End of variables declaration

}