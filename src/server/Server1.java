package server;

public class Server1 {

	public static void main (String[] args) {
		
		//Call the server with the name, port and file name
		@SuppressWarnings("unused")
		FileReadingServer server1 = new FileReadingServer("Server 1", 9876, "textfile1.txt");//"largeFile.txt"
	}
    
}
