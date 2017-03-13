package chatProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class User extends javax.swing.JFrame {
	private static final long serialVersionUID = 3815623818891390942L;
	private String username;
	private InetAddress address;
	int port;
	private Socket sock;
	private BufferedReader reader;
	private PrintWriter writer;
	private JTextArea chatDisplay;
	private JTextArea chatType;
	static int windowOffset = 0;

	public User(InetAddress a, int p) {
		address = a;
		port = p;
		createGUI();
	}

	public class InputReader implements Runnable {
		public void run() {
			String[] data;
			String stream;
			
			try {
				while ((stream = reader.readLine()) != null) {
					data = stream.split("~");
					if (data[2].equals("ACK")) {
						if (chatDisplay.getText().equals("")) {
							generateChat();
						}
						chatDisplay.append(data[0] + " has connected\n");
					} else if (data[2].equals("DENY")) {
						if (data[0].equals(username)) {
							chatDisplay.append("That username is not available\n");
						}
					} else {
						chatDisplay.append(data[0] + ": " + data[1] + "\n");
						chatDisplay.setCaretPosition(chatDisplay.getDocument().getLength());
					}
				}
			} catch (IOException e) {
				chatDisplay.append("Problem reading incoming data");
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
		contentPanel.setLayout(new BorderLayout());
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
		contentPanel.add(requestUser, BorderLayout.NORTH);
		
		chatDisplay.setEditable(false);
		formatArea(chatDisplay);
		JScrollPane scrollOutput = new JScrollPane(chatDisplay);
		contentPanel.add(scrollOutput);
		
		JPanel inputArea = new JPanel();
		inputArea.setLayout(new BoxLayout(inputArea, BoxLayout.Y_AXIS));
		JLabel messageInput = new JLabel("Type your message below:");
		inputArea.add(messageInput);
		
		ArrayList<Integer> keys = new ArrayList<Integer>();
		chatType.setRows(5);
		chatType.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (!keys.contains(e.getKeyCode())) {
					keys.add(new Integer(e.getKeyCode()));
				}
				if (keys.contains(new Integer(KeyEvent.VK_CONTROL)) && keys.contains(new Integer(KeyEvent.VK_ENTER))) {
					sendChat();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				keys.remove(new Integer(e.getKeyCode()));
			}
		});
		formatArea(chatType);
		JScrollPane scrollInput = new JScrollPane(chatType);
		inputArea.add(scrollInput);
		
		JButton send = new JButton("Send");
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendChat();
			}
		});
		inputArea.add(send);
		contentPanel.add(inputArea, BorderLayout.SOUTH);
		
		setSize(new Dimension(500, 700));
		setLocation(windowOffset * 100, 125);
		setVisible(true);
		windowOffset++;
	}
	
	private void formatArea(JTextArea area) {
		Border blackline = BorderFactory.createLineBorder(Color.black, 1);
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		area.setBorder(blackline);
	}
	
	private void connect() {
		try {
			sock = new Socket(address, port);
			reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			writer = new PrintWriter(sock.getOutputStream());
			writer.println(username + "~ ~Connect");
			writer.flush();
		} catch (IOException e1) {
			chatDisplay.append("Connection failed");
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
	
	private void generateChat() {
		StudentSet thisSet = new StudentSet();
		Random rand = new Random();
		int randomGroup = rand.nextInt(thisSet.groups.size());
		for (Group g : thisSet.groups) {
			if (thisSet.groups.indexOf(g) == randomGroup) {
				chatDisplay.append(g.groupChat().toString());
			}
		}
	}
}