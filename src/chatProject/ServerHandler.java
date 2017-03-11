// Assignment: ChatProject
// Program:    ServerHandler
// Created:    Mar 11, 2017
// Author:     lcattle - Lauren Ribeiro
//
package chatProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerHandler implements Runnable {
	BufferedReader reader;
	Socket socket;
	PrintWriter writer;

	public ServerHandler(Socket s, PrintWriter w) {
		writer = w;
		try {
			socket = s;
			reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String message;
		System.out.println("handling");
		try {
			while ((message = reader.readLine()) != null) {
				System.out.println("serv hand message = " +message);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
