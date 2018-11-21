package server;

public class Server1 {

	public static void main (String[] args) {
		
		@SuppressWarnings("unused")
		FileReadingServer server1 = new FileReadingServer("Server 1", 9876, "textfile1.txt");
		//FileReadingServer server1 = new FileReadingServer("Server 1", 9876, "largeFile.txt");
	}
    
}
