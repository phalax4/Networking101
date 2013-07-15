
public class Driver {

	public static void main(String[] args) {
		Client dave = new Client();
		Server bob = new Server();
		bob.start();
		dave.start();

	}

}
