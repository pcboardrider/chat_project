// Assignment: ChatProject
// Program:    Client
// Created:    Mar 8, 2017
// Author:     lcattle - Lauren Ribeiro
//
package chatProject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable {
	//private Socket s;
	private PrintWriter output;
	private GraphicalChat g;
	
	public Client(InetAddress iP, int port) {
		try (Socket s = new Socket(iP, port)) {
			new Thread(this).start();
			System.out.println("connected on " + s);
			new MessageClient(s);
			//g = new GraphicalChat(this);
			//Thread MessageClient = new Thread(new MessageClient(s));
			//MessageClient.start();
		} catch (UnknownHostException e) {
			System.out.println("Unknown host");
		} catch (IOException e) {
			System.out.println("Exception");
		}
	}
	

	@Override
	public void run() {
		System.out.println("running");
		
		
		
//		try {
//			output = new PrintWriter(g.getMessage());
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
	}
}
