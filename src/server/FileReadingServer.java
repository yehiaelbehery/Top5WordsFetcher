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

import utility.Constant;

public class FileReadingServer {

    //static ServerSocket variable
    private ServerSocket server;
    
    public FileReadingServer (String serverName, Integer port, String fileName) {
    	

        BufferedReader reader;
    	
			try {
				reader = new BufferedReader(
				     new InputStreamReader(
				     new FileInputStream(System.getProperty("user.dir")+"/"+fileName), "ISO-8859-1"));
		        //create the socket server object
		        server = new ServerSocket(port);
        //keep listens indefinitely until receives 'exit' call or program terminates
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

            	oos.writeObject(Constant.socketCloseMagicWord);
                //close resources
                ois.close();
                oos.close();
                socket.close();
            	break;
            }else {
            	oos.writeObject(line);
        	}
        }
        System.out.println(serverName+" shutting down Socket server!!");
        //close the ServerSocket object
        reader.close();
        server.close();
        
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.toString());
			System.exit(0);
		} catch (FileNotFoundException e) {
			System.out.println("File "+fileName+" not found. Please make sure it exists in ("+System.getProperty("user.dir")+")");
			System.exit(0);
		} catch (IOException e) {

			System.out.println(e.toString());
			System.exit(0);
		} catch (ClassNotFoundException e) {

			System.out.println(e.toString());
			System.exit(0);
		}
    }
}
