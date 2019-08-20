// @author : Abir Rahman
//Client - Server interaction referenced from : https://www.geeksforgeeks.org/socket-programming-in-java/

import java.net.*;
import java.io.*;

public class Client{

 private Socket socket            = null;
 private DataInputStream  input   = null;
 private DataOutputStream out     = null;
 private DataInputStream in       =  null;

 // constructor takes input address and port number
 public Client(String address, int port){
     //Creating a new connection with server
     try{
         socket = new Socket(address, port);
         System.out.println("Connected to Rock Paper Scissors Server");

         // input from console/terminal 
         input  = new DataInputStream(System.in);

         // sends user input to server socket 
         out    = new DataOutputStream(socket.getOutputStream());
         
         in = new DataInputStream(
                 new BufferedInputStream(socket.getInputStream()));
         String computer = "";
        	 try {
        		 computer = in.readUTF();
        		 System.out.println(computer);
        	 }
        	 catch(IOException i){
                 System.out.println(i);
             }
        
     }
     catch(UnknownHostException u){
         System.out.println(u);
     }
     catch(IOException i){
         System.out.println(i);
     }
     
     String name = "";
     
     while (!name.equals("START")){
         try{
             name = input.readLine();
      
            	 out.writeUTF(name);
            	 
            	 String inputMsg = "";
              	 try {
              		 inputMsg = in.readUTF();
              		 System.out.println(inputMsg);
              		
              	 }
              	 catch(IOException i){
                       System.out.println(i);
                 }
              	 name = "START";
              	 
         }
         
         catch(IOException i){
             System.out.println(i);
         }
         
       
     }
     

     // string to store message from input 
     String line = "";

     // keep reading until "Exit" is input
     while (!line.equals("EXIT")){
         try{
             line = input.readLine();
             if(line.equals("1") || line.equals("2") || line.equals("3")) {
            	 out.writeUTF(line);
            	 String msg = "";
              	 try {
              		msg = in.readUTF();
              		 System.out.println(msg);
              	 }
              	 catch(IOException i){
                       System.out.println(i);
                 }
             }
             else {
            	 System.out.println("Invalid input, try again!"); 
             }
         }
         catch(IOException i){
             System.out.println(i);
         }
         
     }

     System.out.println("GAME OVER");
     
     // closing the connection 
     try{
    	 in.close();
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
