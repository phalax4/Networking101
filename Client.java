import java.io.*;
import java.net.*;

public class Client {
	public static void main(String[] args) throws Exception{
	
	
		String sentence; String ipAddr;
		String modifiedSentence;
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader inIp = new BufferedReader(new InputStreamReader(System.in));
		
		ipAddr = inIp.readLine();
		
		Socket clientSocket = new Socket("localhost",4444);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		

		do{
		sentence = inFromUser.readLine();
		outToServer.writeBytes(sentence + '\n');
		}while(!(sentence.equals("stop")));

		
		clientSocket.close();
	}

}
//144.118.54.240