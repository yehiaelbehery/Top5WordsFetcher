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
    
    public boolean encounteredAnError = false;
    public String errorMessage;
    public String errorDetails;

    //For unit testing
	public boolean debugMode = false;
	
	public RemoteFileLineFetcher(Integer port) {
		try {
			host = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {

			logError("Error occured, couldn't get device IP.", e.toString());
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
		            if (message.equals(Constant.socketCloseMagicWord)) {
		            	return null;
		            }
		            //Close resources
		            ois.close();
		            oos.close();
		            
		            //Catch your breath
		            Thread.sleep(100);
				} catch (IOException | ClassNotFoundException e) {

					logError("Error reading from the server, please try again later.", e.toString());
					return null;

				} catch (InterruptedException e) {
					//do nothing
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
	private void logError(String error, String details) {
		encounteredAnError = true;
		this.errorMessage = error;
		this.errorDetails = details;
	}
}
