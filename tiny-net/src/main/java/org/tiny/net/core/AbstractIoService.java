package org.tiny.net.core;

public abstract class AbstractIoService {
	
	protected AbstractChannelInitializer channelInitializer;
	protected AbstractChannelHandlerAdapter channelHandlerAdapter;
	
	private String name;
	
	public AbstractIoService(String name, AbstractChannelInitializer channelInitializer,
			AbstractChannelHandlerAdapter channelHandler) {
		this.channelInitializer = channelInitializer;

		channelHandler.setIoService(this);
		this.channelInitializer.setChannelHandler(channelHandler);
	}
	
	public AbstractChannelHandlerAdapter getChannelHandlerAdapter() {
		return channelHandlerAdapter;
	}

	public void setChannelHandlerAdapter(AbstractChannelHandlerAdapter channelHandlerAdapter) {
		this.channelHandlerAdapter = channelHandlerAdapter;
	}
	
	public abstract void start();
	public abstract void close();
}