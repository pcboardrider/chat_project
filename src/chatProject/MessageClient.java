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

public class MessageClient implements Runnable{
	private Socket socket;
	private GraphicalChat gc;
	PrintWriter output;
	public MessageClient(Socket s, GraphicalChat g) {
		socket = s;
		gc = g;
	}
	
	public void run() {
        //String[] data;
        String stream;
        

        try {
        	BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while ((stream = reader.readLine()) != null) {

                //data = stream.split(":");

                 

                    System.out.println(stream + "\n");
                    
                    output = gc.getMessage();
					
					System.out.println(output);
                
             
            }
       }catch(Exception ex) {
       }
    }
	
	
//	private Socket socket;
//	private BufferedReader reader;
//
//	public MessageClient(Socket s) {
//		socket = s;
//		try {
//			reader  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		new Thread(this).start();
//		System.out.println("started a message thread");
//	}
//
//
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		System.out.println(reader);
//		
//	}

}
