// Assignment: ChatProject
// Program:    Server
// Created:    Mar 8, 2017
// Author:     lcattle - Lauren Ribeiro
//
package chatProject;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
	private Thread thread = null;
	private ServerSocket ss = null;

	public Server(int port) {
		try {
			ss = new ServerSocket(port);
			System.out.println(ss);
			if (thread == null) {
				thread = new Thread(this);
				thread.start();
			}
		} catch (IOException e) {
			System.out.println("serverSocket failed\n");
		}
	}

	@Override
	public void run() {
		while (thread != null) {
			try {
				Socket s = ss.accept();
			} catch (IOException e) {
				System.out.println("Accept failed\n");
			}
		}
	}
}
