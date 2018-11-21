package helper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import utility.Constant;

public class RemoteFileLineFetcher {
	public int port;
	public InetAddress host;
	Socket socket;
	ObjectOutputStream oos;
    ObjectInputStream ois;

    //For unit testing
	public boolean debugMode = false;
	
	public RemoteFileLineFetcher(Integer port) {
		try {
			host = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {

			System.out.println("Error occured, couldn't get device IP.");
			System.exit(0);
		}
		this.port = port;
	}
	
	/**
	 * Open a socket and get the next line
	 */
	public String getNextLine() {
		if (debugMode == true) {
			return getMockNextLine();
		}else {
			String message = "";
				try {
		            //Establish socket connection to server
					socket = new Socket(host.getHostName(), port);
					
		            //Write to socket using ObjectOutputStream
		            oos = new ObjectOutputStream(socket.getOutputStream());
		            oos.writeObject("next");
		            
		            //Read the server response message
		            ois = new ObjectInputStream(socket.getInputStream());
		            message = (String) ois.readObject();
		            
		            //Close resources
		            ois.close();
		            oos.close();
		            
		            //Just catch your breath
		            Thread.sleep(100);
				} catch (UnknownHostException e) {
					System.out.println("An error occured while connecting to the server.");
//					e.printStackTrace();
					return null;
							
				} catch (IOException e) {

					System.out.println("An error occured while connecting to the server.");
//					e.printStackTrace();
					return null;
					
				} catch (ClassNotFoundException e) {

					System.out.println("An error occured while connecting to the server.");
//					e.printStackTrace();
					return null;

				} catch (InterruptedException e) {

					System.out.println("An error occured while connecting to the server.");
//					e.printStackTrace();
					return null;

				}
				return message;
		}
	}
	private String getMockNextLine() {
		/**
		 * For the unit test
		 */
		return Constant.socketCloseMagicWord;
	}
}
