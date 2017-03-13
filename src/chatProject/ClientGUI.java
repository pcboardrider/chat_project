// Assignment: ChatProject
// Program:    ClientGUI
// Created:    Mar 7, 2017
// Author:     lcattle - Lauren Ribeiro
//
package chatProject;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ClientGUI extends JFrame {

	private int port = 8090;
	private InetAddress address;

	public ClientGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		add(contentPanel);

		JLabel requestIP = new JLabel("Enter the IP address you wish to connect to:");
		contentPanel.add(requestIP);

		JTextField inputIP = new JTextField("   .   .   .   ");
		inputIP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == new Integer(KeyEvent.VK_ENTER)) {
					tryIP(inputIP.getText());
				}
				
			}
		});
		contentPanel.add(inputIP);

		JButton submitIP = new JButton("Submit");
		submitIP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tryIP(inputIP.getText());
			}
		});
		contentPanel.add(submitIP);

		setSize(new Dimension(300, 100));
		setVisible(true);
	}

	private void tryIP(String iP) {
		try {
			address = InetAddress.getByName(iP);
			if (serverListening(address, port)) {
				startClient();
			} else {
				startServer();
				startClient();
			}

		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "Couldn't recognize that IP\nTry again");
		}
	}

	private void startServer() {
		//new Server(port);
		new Connection();
	}

	private void startClient() {
		//new Client(address, port);
		new User();
	}

	private boolean serverListening(InetAddress address, int port) {
		Socket s = null;
		try {
			s = new Socket(address, port);
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (s != null) {
				try {
					s.close();
				} catch (Exception e) {
					System.out.println("Couldn't close test Socket");
				}
			}
		}
	}

	public static void main(String[] args) {
		new ClientGUI();
	}
}
