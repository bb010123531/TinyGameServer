package tiny.client;

import org.tiny.net.log.TinyLogger;

import tiny.client.net.ClientServer;

public class ClientMain {
	public static void main(String[] args) {
		try {
			ClientServer.start();
			TinyLogger.LOG.debug("Client Start SUCCESS !");
		} catch (Exception e) {
			TinyLogger.LOG.error("Client Start ERROR !");
			e.printStackTrace();
		}
	}
}
