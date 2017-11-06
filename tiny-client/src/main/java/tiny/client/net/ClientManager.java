package tiny.client.net;

import org.tiny.net.core.AbstractManager;
import org.tiny.net.core.Connector;

import tiny.client.handler.child.L2CChannelInitializer;
import tiny.client.handler.msg.C2SMessageInit;

public class ClientManager extends AbstractManager{
	public ClientManager () {
		build("connect-to-link", new Connector("", "127.0.0.1", 8801, new L2CChannelInitializer(), new C2SMessageInit()));
	}
}
