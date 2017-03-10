package chatProject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

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
	
	private static final long serialVersionUID = 6980588082265785090L;
	private JPanel contentPanel;
	private JScrollPane scrollOutput;
	private JScrollPane scrollInput;
	private JTextArea chatOutput;
	private JTextArea chatInput;
	private JButton send;
	private ArrayList<Integer> keys;
	
	public GraphicalChat() {
		//no args
	}
	public GraphicalChat(Socket s) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		add(contentPanel);
		
		StudentSet thisSet = new StudentSet();
		Random rand = new Random();
		int randomGroup = rand.nextInt(thisSet.groups.size());
		
		chatOutput = new JTextArea(30,1);
		for (Group g : thisSet.groups) {
			if (thisSet.groups.indexOf(g) == randomGroup) {
				chatOutput.setText(g.groupChat().toString());
				}
			}
		chatOutput.setEditable(false);
		formatArea(chatOutput);
		
		scrollOutput = new JScrollPane(chatOutput);
		contentPanel.add(scrollOutput);
		
		chatInput = new JTextArea();
		formatArea(chatInput);
		keys = new ArrayList<Integer>();
		chatInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (!keys.contains(e.getKeyCode())) {
					keys.add(new Integer(e.getKeyCode()));
				}
				if (keys.contains(new Integer(KeyEvent.VK_CONTROL)) && keys.contains(new Integer(KeyEvent.VK_ENTER))) {
					//updateChat();
					getMessage(s);
					chatInput.setText("");
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
				//updateChat();
				getMessage(s);
				chatInput.setText("");
			}
		});
		contentPanel.add(send);
		
		setSize(new Dimension(500, 700));
		setVisible(true);
	}
	
	public PrintWriter getMessage(Socket s) {
		PrintWriter pw = null;
		try {
			System.out.println("this is the socket malfuctioning" + s);
			pw = new PrintWriter(s.getOutputStream());
			pw.println(chatInput.getText());
			pw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pw;
	}
	
	private void updateChat() {
		chatOutput.append("Comment: " + chatInput.getText() + "\n");
		chatInput.setText("");
	}
	
	private void formatArea(JTextArea area) {
		Border blackline = BorderFactory.createLineBorder(Color.black, 1);
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		area.setBorder(blackline);
	}

	public static void main(String[] args) {
		new GraphicalChat();
	}
}
