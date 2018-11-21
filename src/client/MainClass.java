package client;

import server.FileReadingServer;

public class MainClass {
	
	public static void main (String[] args) {
		
		@SuppressWarnings("unused")
//		ClientView view = new ClientView();
//		FileReadingServer server1 = new FileReadingServer("Server 1", 9876, "textfile1.txt");
		FileReadingServer server2 = new FileReadingServer("Server 2", 9877, "textfile2.txt");
	}
}
