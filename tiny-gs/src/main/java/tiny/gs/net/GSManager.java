package tiny.gs.net;

import org.tiny.net.core.AbstractManager;
import org.tiny.net.core.Acceptor;

import tiny.gs.config.Conf;
import tiny.gs.handler.child.C2GChannelInitializer;
import tiny.gs.handler.msg.ProtocolHandlerManager;

public class GSManager extends AbstractManager{
	
	public GSManager () {
		build("listen-for-link", new Acceptor("", Conf.getServerPort(), new C2GChannelInitializer(), new ProtocolHandlerManager()));
	}
}
