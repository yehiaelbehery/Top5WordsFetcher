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
	public boolean debugMode = false;
	Socket socket;
	ObjectOutputStream oos;
    ObjectInputStream ois;
	
	public RemoteFileLineFetcher(Integer port) {
		try {
			host = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {

			System.out.println("Error occured, couldn't get device IP.");
//			System.out.println(e);
			System.exit(0);
		}
		this.port = port;
	}
	public String getNextLine() {
		if (debugMode == true) {
			return getMockNextLine();
		}else {
			String message = "";
//	        while (message.equals(Constant.socketCloseMagicWord) == false) {
				try {
		            //establish socket connection to server
					socket = new Socket(host.getHostName(), port);
					
		            //write to socket using ObjectOutputStream
		            oos = new ObjectOutputStream(socket.getOutputStream());
		            oos.writeObject("next");
		            
		            //read the server response message
		            ois = new ObjectInputStream(socket.getInputStream());
		            message = (String) ois.readObject();
		            //close resources
		            ois.close();
		            oos.close();
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
//	        }
		}
	}
	private String getMockNextLine() {
		return Constant.socketCloseMagicWord;
	}
}
