package server;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import utility.Constant;

public class FileReadingServer {

    private ServerSocket server;
    
    public FileReadingServer (String serverName, Integer port, String fileName) {
    	

        BufferedReader reader;
    	
			try {
				//Read the file
				reader = new BufferedReader(
				     new InputStreamReader(
				     new FileInputStream(System.getProperty("user.dir")+"/"+fileName), "ISO-8859-1"));
				
		        //create the socket server object
		        server = new ServerSocket(port);
		        
        //keep listening indefinitely until the file lines run out
        while(true){
            System.out.println(serverName+" waiting for client request");
            
            //creating socket and waiting for client connection
            Socket socket = server.accept();
            
            //read from socket to ObjectInputStream object
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            
            //convert ObjectInputStream object to String
            @SuppressWarnings("unused")
			String message = (String) ois.readObject();
            
            //create ObjectOutputStream object
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            
            //write object to Socket
            String line = reader.readLine();

            if (line == null) {

            	//Send socket closing message to the client
            	oos.writeObject(Constant.socketCloseMagicWord);
            	
                //close resources
                ois.close();
                oos.close();
                socket.close();
            	break;
            }else {
            	//Send the next line
            	oos.writeObject(line);
        	}
        }
        System.out.println(serverName+" shutting down Socket server!!");
        
        //close the ServerSocket object
        reader.close();
        server.close();
        
		} catch (UnsupportedEncodingException e) {
			displayError("Error opening file, please, make sure the file is ISO-8859-1 formatted.", e.toString());
			System.exit(0);
		} catch (FileNotFoundException e) {
			displayError("File "+fileName+" not found. Please make sure it exists in ("+System.getProperty("user.dir")+").", e.toString());
			System.exit(0);
		} catch (IOException e) {
			displayError("Error reading file, Pleae try again later.", e.toString());
			System.exit(0);
		} catch (ClassNotFoundException e) {
			displayError("An error occurred, Pleae try again later.", e.toString());
			System.out.println(e.toString());
			System.exit(0);
		}
    }
    private void displayError(String error, String details) {
    	System.out.println(error);
    	promptForMoreDetails(details);
    }
    private void promptForMoreDetails(String details) {
    	System.out.println("Type anything for more details...");
	    Scanner scan = new Scanner(System.in);
	    scan.nextLine();
	    System.err.println(details);
	    scan.close();
    }
}
