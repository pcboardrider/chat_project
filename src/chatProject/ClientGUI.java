// Assignment: ChatProject
// Program:    ClientGUI
// Created:    Mar 7, 2017
// Author:     lcattle - Lauren Ribeiro
//
package chatProject;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ClientGUI extends JFrame {
	public ClientGUI () {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		add(contentPanel);
		
		JLabel requestIP = new JLabel("Enter the IP address you wish to connect to:");
		contentPanel.add(requestIP);
		
		JTextField inputIP = new JTextField("   .   .   .   ");
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
			InetAddress address = InetAddress.getByName(iP);
			System.out.println(address);
		} catch (UnknownHostException e) {
			System.out.println("couldn't recognize that IP\n");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ClientGUI();
	}
}
