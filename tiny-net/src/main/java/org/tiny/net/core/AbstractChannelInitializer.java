package org.tiny.net.core;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public abstract class AbstractChannelInitializer extends ChannelInitializer<SocketChannel>{
	protected AbstractChannelHandlerAdapter channelHandler;
	
	public AbstractChannelInitializer () {
	}

	public AbstractChannelHandlerAdapter getChannelHandler() {
		return channelHandler;
	}

	public void setChannelHandler(AbstractChannelHandlerAdapter channelHandler) {
		this.channelHandler = channelHandler;
	}
	
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		initChannelBefore(ch);
		ch.pipeline().addLast(getChannelHandler());
	}
	
	public abstract void initChannelBefore(SocketChannel ch);
}
