import java.io.*;
import java.net.*;
public class Server {

public static void main(String args[]) throws Exception{
	String clientSentence;String capitalizedSentence;
	ServerSocket listeningSocket = new ServerSocket(4444);
	
	while(true){
		Socket connectionSocket = listeningSocket.accept();
		BufferedReader inFromClient=new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
		DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
	
		do{
		clientSentence = inFromClient.readLine();
		capitalizedSentence = clientSentence.toUpperCase()+ '\n';
		System.out.println(capitalizedSentence);
		}while(!(clientSentence.equals("stop")));		

		//outToClient.writeBytes(capitalizedSentence);
	}
}
	
}
