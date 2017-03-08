// Assignment: ChatProject
// Program:    Client
// Created:    Mar 8, 2017
// Author:     lcattle - Lauren Ribeiro
//
package chatProject;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable {
	
	public Client(InetAddress iP, int port) {
		try (Socket s = new Socket(iP, port)) {
			new Thread(this).start();
			System.out.println("connected on " + s);
		} catch (UnknownHostException e) {
			System.out.println("Unknown host");
		} catch (IOException e) {
			System.out.println("Exception");
		}
	}

	@Override
	public void run() {
		System.out.println("running");
	}
}
