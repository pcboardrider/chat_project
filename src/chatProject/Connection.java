// Assignment: ChatProject
// Program:    ConnectionGUI
// Created:    Mar 12, 2017
// Author:     lcattle - Lauren Ribeiro
//
package chatProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class Connection {
	ArrayList output;
	ArrayList<String> users;
	
	public class Handler implements Runnable {
		BufferedReader reader;
		Socket sock;
		PrintWriter client;
		
		public Handler(Socket socket, PrintWriter stream) {
			client = stream;
			try {
				sock = socket;
				reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}

		@Override
		public void run() {
			String message;
			String connect = "Connect";
			String chat = "Chat";
			String[] data;
			try {
				while ((message = reader.readLine()) != null) {
					data = message.split("~");
					
					if (data[2].equals(connect)) {
						sendOut((data[0] + "~" + data[1] + "~" + chat));
						addUser(data[0]);
					} else if (data[2].equals(chat)) {
						sendOut(message);
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
	
	public Connection() {
		Thread server = new Thread(new ServerStarter());
		server.start();
	}
	
	public class ServerStarter implements Runnable {
		public void run() {
			output = new ArrayList<>();
			users = new ArrayList<>();
			try {
				ServerSocket ss = new ServerSocket(8090);
				
				while (true) {
					Socket client = ss.accept();
					PrintWriter writer = new PrintWriter(client.getOutputStream());
					output.add(writer);
					Thread listener = new Thread(new Handler(client, writer));
					listener.start();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void addUser(String data) {
		String message;
		String add = "~ ~Connect";
		String name = data;
		users.add(name);
		String[] temp = new String[(users.size())];
		users.toArray(temp);
		for (String user : users) {
			message = (user + add);
			sendOut(message);
		}
	}
	
	private void sendOut(String message) {
		Iterator list = output.iterator();
		while (list.hasNext()) {
			try {
				PrintWriter writer = (PrintWriter) list.next();
				writer.println(message);
				writer.flush();
			} finally {
				// TODO
			}
		}
	}
}
