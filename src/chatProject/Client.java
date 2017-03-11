// Assignment: ChatProject
// Program:    Client
// Created:    Mar 8, 2017
// Author:     lcattle - Lauren Ribeiro
//
package chatProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable {
	private Socket s;
	private PrintWriter writer;
	private GraphicalChat g;
	BufferedReader reader;

	public Client(InetAddress iP, int port) {
		try {
			s = new Socket(iP, port);
			reader = new BufferedReader(new InputStreamReader(
					s.getInputStream()));
			writer = new PrintWriter(s.getOutputStream());
			g = new GraphicalChat(s);
			new Thread(this).start();
		} catch (UnknownHostException e) {
			System.out.println("Unknown host");
		} catch (IOException e) {
			System.out.println("Exception");
		}
	}

	@Override
	public void run() {
		Thread messenger = new Thread(new MessageClient(s, g, reader));
		messenger.start();
		System.out.println("client running");
		try {
			writer.println(g.getMessage(s));
			writer.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
