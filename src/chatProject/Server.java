// Assignment: ChatProject
// Program:    Server
// Created:    Mar 8, 2017
// Author:     lcattle - Lauren Ribeiro
//
package chatProject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
	private ServerSocket ss = null;

	public Server(int port) {
		try {
			ss = new ServerSocket(port);
			System.out.println(ss);
			new Thread(this).start();
		} catch (IOException e) {
			System.out.println("serverSocket failed\n");
		}
	}

	@Override
	public void run() {
		try {
			Socket s = ss.accept();
			PrintWriter output = new PrintWriter(s.getOutputStream());
			Thread handler = new Thread(new ServerHandler(s, output));
			handler.start();
			System.out.println("serving");
		} catch (IOException e) {
			System.out.println("Accept failed\n");
		}

	}
}
