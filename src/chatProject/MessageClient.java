// Assignment: ChatProject
// Program:    MessageClient
// Created:    Mar 8, 2017
// Author:     lcattle - Lauren Ribeiro
//
package chatProject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MessageClient implements Runnable {
	private Socket socket;
	private GraphicalChat gc;
	private BufferedReader reader;
	
	public MessageClient(Socket s, GraphicalChat g, BufferedReader r) {
		socket = s;
		gc = g;
		reader = r;
	}
	
	public void run() {
        //String[] data;
        String stream;
        System.out.println("mc running");

        try {
        	while ((stream = reader.readLine()) != null) {

                //data = stream.split(":");


                    System.out.println(stream + "=stream\n");  // not working
             
            }
       } catch(Exception ex) {
       }
    }
}
