// @author : Abir Rahman
//Client - Server interaction referenced from : https://www.geeksforgeeks.org/socket-programming-in-java/

import java.net.*;
import java.io.*;
import java.util.Random;

public class Server{
 private Socket          socket   = null;
 private ServerSocket    server   = null;
 private DataInputStream in       =  null;
 private DataOutputStream out     = null;
 public String player ="";
 public String rules = "1 - ROCK, 2 - PAPER, 3 - SCISSORS, type EXIT to quit anytime!";
 
 // constructor takes TCP port number
 public Server(int port){
     // starts server and listens for a connection
     try{
         server = new ServerSocket(port);
         System.out.println("Rock Paper Scissor Game Server started");

         System.out.println("Waiting for a player ...");

         socket = server.accept();
         System.out.println("Player accepted");
         
         out = new DataOutputStream(socket.getOutputStream());
         String msg = "Please enter your name"; 
         out.writeUTF(msg);
         in = new DataInputStream(
                 new BufferedInputStream(socket.getInputStream()));
         String playerName = "";
         while(!playerName.equals("START")) {
        	 try {
        		 playerName = in.readUTF();
        		 out.writeUTF(playerName + " Registered, Start game by entering the following "+ rules);
        		 player = playerName; 
        		 playerName ="START";
        	 }
        	 catch(IOException i){
                 System.out.println(i);
             }
        	 //break;
         }
         

         // takes input from the client socket
         in = new DataInputStream(
             new BufferedInputStream(socket.getInputStream()));

         String line = "";

         // reads message from client until "Exit" is sent
         while (!line.equals("EXIT")){
             try{
                 line = in.readUTF();
                 System.out.println(line); //Replace this line with rock paper scissor function 
                 RockPaperScissor(line);	
             }
             catch(IOException i){
            	 break;
                 //System.out.println(i);
                 //System.out.println("Problem 1"); //DELETE
                 
             }
            
         }
         System.out.println("GAME OVER");
         System.out.println("Closing connection");

         // closing connection
         socket.close();
         in.close();
         out.close();
     }
     catch(IOException i){
         System.out.println(i);
     }
 }
 
 // Generates random move between 1 and 3
 public int randomGenerator() {
	 Random rand = new Random();
	 int randomNumber =rand.nextInt((3 - 1) + 1) + 1;
	return randomNumber;
 }
 
 // Rock Paper Scissors game logic [very stupid but works]
 public void RockPaperScissor(String x) {
	 
	 int computer =  randomGenerator(); //computer move
	 int userMove = Integer.parseInt(x); //user move
	 if(computer == userMove) {
		 try {
			out.writeUTF(player + " tied with Computer");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 else if( computer == 1 && userMove == 3) {
		 //Computer wins 
		 try {
				out.writeUTF(player + "(SCISSORS) lost to the Computer(ROCK)");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	 
	 else if( computer == 1 && userMove == 2) {
		 //User wins
		 try {
				out.writeUTF(player + "(PAPER) won with the Computer(ROCK)");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	 else if(computer == 2  && userMove == 3) {
		 //USer wins
		 try {
				out.writeUTF(player + "(SCISSOR) won with the Computer(PAPER)");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	 else if(computer == 2 && userMove == 1) {
		 //computer wins
		 try {
				out.writeUTF(player + "(ROCK) lost to the Computer(PAPER)");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	 else if(computer == 3 && userMove == 1) {
		 //User wins
		 try {
				out.writeUTF(player + "(ROCK) won with the Computer(SCISSOR)");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	 else if(computer == 3 && userMove == 2) {
		 //Computer wins
		 try {
				out.writeUTF(player + "(PAPER) lost to the Computer(SCISSORS)");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	 }
	 
	 
 }

 // starting the server with credentials
 public static void main(String args[]){
     Server server = new Server(5000);
 }
}

