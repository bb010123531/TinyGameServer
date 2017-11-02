package org.tiny.link.net;

import org.tiny.link.init.C2LChannelInitializer;
import org.tiny.link.init.G2LChannelInitializer;
import org.tiny.net.core.Acceptor;
import org.tiny.net.core.Connector;

public class LinkServer {
	
	public static void main(String[] args) {
		// 不能一直阻塞
		for (int i = 0 ; i < 2 ; i++) {
			if (i == 0) {
				// 连接gs 向gs发送消息
				new Connector("127.0.0.1", 8800, new G2LChannelInitializer());
				// 监听客户端的消息
				new Acceptor(8801, new C2LChannelInitializer());
			} else {
				
			}
		}
	}
}
