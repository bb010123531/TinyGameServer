package tiny.client.net;

import org.tiny.net.core.Connector;

import tiny.client.handler.child.G2SChannelInitializer;

public class ClientServer {
	
	public static void start() {
        new Connector("127.0.0.1", 8800, new G2SChannelInitializer());
	}
}
