package tiny.client;

import tiny.client.net.ClientServer;

public class ClientMain {
	public static void main(String[] args) {
		try {
			ClientServer.start();
			System.err.println("Client Start SUCCESS !");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("Client Start ERROR !");
			e.printStackTrace();
		}
	}
}
