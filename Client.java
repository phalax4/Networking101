import java.io.*;
import java.net.*;

public class Client {
	public static void main(String[] args) throws Exception{
	
	
		String sentence;int count = 0
		String modifiedSentence;
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		Socket clientSocket = new Socket("144.118.102.167",4444);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		

		do{
		sentence = inFromUser.readLine();
		outToServer.writeBytes(sentence + '\n');
		count++;
		}while(!(sentence.equals("stop")));

		//modifiedSentence = inFromServer.readLine();
		//System.out.println("FROM SERVER: " + modifiedSentence);
		clientSocket.close();
	}

}
//144.118.54.240