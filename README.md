# Top5WordsFetcher

This is a small project written in Java implementing a client connecting in parallel with two servers via sockets to get the most repeated words in two ISO 8859-1 encoded files that the servers read and send back to the client. 


## App archicture
## Client
![Alt text](client2.jpeg)

## Server
![Alt text](server.jpeg)

### Prerequisites

- Java SE Development Kit 8u191


### Installing

1- Run from the source code:
 - Clone or download the repository.
 - Open the project in Eclipse or your favorite IDE.
 - Right-click one src > server > Server1.java, choose "Run As" > "Java Application". The console will print "Server 1 waiting for client request".
 - Repeat for src > server > Server2.java to get output > "Server 2 waiting for client request".
 - Repeat for src > client > MainClass.java.
 - Watch the magic unfold.
 
 2- Run from jar files:
  - Copy "server1.jar", "server2.jar", "textfile1.txt", "textfile2.txt" and "client.jar" to any folder you like.
  - Open the console, move to that folder.
  - Run "java -jar server1.jar".
  - Open a new console window and move to the same folder and Run "java -jar server2.jar".
  - Open a third console window and move to the same folder and Run "java -jar client.jar".
