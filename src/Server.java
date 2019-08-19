import java.net.*;
import java.io.*;

public class Server{
 private Socket          socket   = null;
 private ServerSocket    server   = null;
 private DataInputStream in       =  null;

 // constructor takes TCP port number
 public Server(int port){
     // starts server and listens for a connection
     try{
         server = new ServerSocket(port);
         System.out.println("Server started");

         System.out.println("Waiting for a client ...");

         socket = server.accept();
         System.out.println("Client accepted");

         // takes input from the client socket
         in = new DataInputStream(
             new BufferedInputStream(socket.getInputStream()));

         String line = "";

         // reads message from client until "Exit" is sent
         while (!line.equals("Exit")){
             try{
                 line = in.readUTF();
                 System.out.println(line); //Replace this line with rock paper scissor function 

             }
             catch(IOException i){
                 System.out.println(i);
             }
         }
         System.out.println("Closing connection");

         // closing connection
         socket.close();
         in.close();
     }
     catch(IOException i){
         System.out.println(i);
     }
 }

 // starting the server with credentials
 public static void main(String args[]){
     Server server = new Server(5000);
 }
}

