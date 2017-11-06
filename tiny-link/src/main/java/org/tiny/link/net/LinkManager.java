package org.tiny.link.net;

import org.tiny.link.init.C2LChannelInitializer;
import org.tiny.link.init.G2LChannelInitializer;
import org.tiny.link.init.handler.C2LHandlerAdapter;
import org.tiny.link.init.handler.G2LHandlerAdapter;
import org.tiny.net.core.AbstractManager;
import org.tiny.net.core.Acceptor;
import org.tiny.net.core.Connector;

public class LinkManager extends AbstractManager{
	
	private static final String locolhost = "127.0.0.1";
	
	public LinkManager () {
		build("connect-to-gs", new Connector("", locolhost, 8800, new G2LChannelInitializer(), new G2LHandlerAdapter()));
		build("listen-for-client", new Acceptor("", 8801, new C2LChannelInitializer(), new C2LHandlerAdapter()));
	}
}
