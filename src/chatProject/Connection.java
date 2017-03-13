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
	private int port;
	private ArrayList<PrintWriter> output;
	private ArrayList<String> users;

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
			String deny = "DENY";
			String[] data;
			try {
				while ((message = reader.readLine()) != null) {
					data = message.split("~");

					if (data[2].equals(connect)) {
						if (users.contains(data[0])) {
							sendOut(data[0] + "~ ~" + deny);
						} else {
							sendOut(data[0] + "~ ~ACK");
							users.add(data[0]);
						}
					} else if (data[2].equals(chat)) {
						sendOut(message);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Connection(int p) {
		port = p;
		Thread server = new Thread(new ServerStarter());
		server.start();
	}

	public class ServerStarter implements Runnable {
		public void run() {
			output = new ArrayList<>();
			users = new ArrayList<>();
			ServerSocket ss = null;
			try {
				ss = new ServerSocket(port);
				while (true) {
					Socket client = ss.accept();
					PrintWriter writer = new PrintWriter(client.getOutputStream());
					output.add(writer);
					Thread listener = new Thread(new Handler(client, writer));
					listener.start();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					ss.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void sendOut(String message) {
		Iterator<PrintWriter> list = output.iterator();
		while (list.hasNext()) {
			try {
				PrintWriter writer = (PrintWriter) list.next();
				writer.println(message);
				writer.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
