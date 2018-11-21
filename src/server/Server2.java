package server;

public class Server2 {

	public static void main (String[] args) {
		
		//Call the server with the name, port and file name
		@SuppressWarnings("unused")
		FileReadingServer server2 = new FileReadingServer("Server 2", 9877, "textfile2.txt");
	}
    
}
