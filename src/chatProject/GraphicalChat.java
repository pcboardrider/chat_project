package chatProject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;


public class GraphicalChat extends JFrame {
	
	private JPanel contentPanel;
	private JScrollPane scrollOutput;
	private JScrollPane scrollInput;
	private JTextArea chatOutput;
	private JTextArea chatInput;
	private JButton send;
	private ArrayList<Integer> keys;
	
	public GraphicalChat() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		add(contentPanel);
		
		keys = new ArrayList<Integer>();
		Border blackline = BorderFactory.createLineBorder(Color.black, 1);
		
		chatOutput = new JTextArea(30,1);
		chatOutput.setText("output ends up here");
		chatOutput.setEditable(false);
		chatOutput.setLineWrap(true);
		chatOutput.setWrapStyleWord(true);
		chatOutput.setBorder(blackline);
		
		scrollOutput = new JScrollPane(chatOutput);
		contentPanel.add(scrollOutput);
		
		chatInput = new JTextArea();
		chatInput.setBorder(blackline);
		chatInput.setText("input goes here");
		chatInput.setLineWrap(true);
		chatInput.setWrapStyleWord(true);
		chatInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (!keys.contains(e.getKeyCode())) {
					keys.add(new Integer(e.getKeyCode()));
				}
				if (keys.contains(new Integer(KeyEvent.VK_CONTROL)) && keys.contains(new Integer(KeyEvent.VK_ENTER))) {
					updateChat();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				keys.remove(new Integer(e.getKeyCode()));
			}
		});
		
		scrollInput = new JScrollPane(chatInput);
		contentPanel.add(scrollInput);
		
		send = new JButton("Send");
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateChat();
			}
		});
		contentPanel.add(send);
		
		setSize(new Dimension(500, 700));
		setVisible(true);
	}
	
	private void updateChat() {
		chatOutput.append("Comment: " + chatInput.getText() + "\n");
		chatInput.setText("");
	}

	public static void main(String[] args) {
		new GraphicalChat();
	}
}