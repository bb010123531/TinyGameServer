package tiny.client.net;

import org.tiny.net.core.Connector;

import tiny.client.handler.child.L2CChannelInitializer;

public class ClientServer {
	
	public static void start() {
        new Connector("127.0.0.1", 8801, new L2CChannelInitializer());
	}
}
