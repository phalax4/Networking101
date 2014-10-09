import java.io.*;
import java.net.*;
public class Server extends Thread{
	
	String clientSentence;String capitalizedSentence;
public void run(){
	
	ServerSocket listeningSocket;
	try {
		listeningSocket = new ServerSocket(4444);
		//Public key is: e: 13727 c: 4717
		//private:d: 4575 c:4717
		while(true){
			Socket connectionSocket = listeningSocket.accept();
			BufferedReader inFromClient=new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
		
			do{
			clientSentence = inFromClient.readLine();
			capitalizedSentence = RSA.decryptWord(clientSentence, 4575, 4717)+ '\n';
			System.out.println(capitalizedSentence);
			}while(!(clientSentence.equals("STOP")));		

			//outToClient.writeBytes(capitalizedSentence);
		}
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	
	
}
	
}
