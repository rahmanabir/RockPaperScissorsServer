import java.net.*;
import java.io.*;

public class Client{

 private Socket socket            = null;
 private DataInputStream  input   = null;
 private DataOutputStream out     = null;

 // constructor takes input address and port number
 public Client(String address, int port){
     //Creating a new connection with server
     try{
         socket = new Socket(address, port);
         System.out.println("Connected");

         // input from console/terminal 
         input  = new DataInputStream(System.in);

         // sends user input to server socket 
         out    = new DataOutputStream(socket.getOutputStream());
     }
     catch(UnknownHostException u){
         System.out.println(u);
     }
     catch(IOException i){
         System.out.println(i);
     }

     // string to store message from input 
     String line = "";

     // keep reading until "Exit" is input
     while (!line.equals("Exit")){
         try{
             line = input.readLine();
             out.writeUTF(line);
         }
         catch(IOException i){
             System.out.println(i);
         }
     }

     // closing the connection 
     try{
         input.close();
         out.close();
         socket.close();
     }
     catch(IOException i){
         System.out.println(i);
     }
 }
//main function to start the client 
 public static void main(String args[]){
     Client client = new Client("127.0.0.1", 5000);
 }
}
