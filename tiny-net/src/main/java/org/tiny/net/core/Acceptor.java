package org.tiny.net.core;

import org.tiny.net.log.Logger;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * server
 * 
 * @author chunbo
 */

public class Acceptor extends AbstractIoService{
	
	private int port = 8800;
	
	private EventLoopGroup bossGroup = new NioEventLoopGroup();
	private EventLoopGroup workerGroup = new NioEventLoopGroup();
	
	public Acceptor(String name, int port, AbstractChannelInitializer channelInitializer, AbstractChannelHandlerAdapter channelHandler) {
		super(name, channelInitializer, channelHandler);
		this.port = port;
	}
	
	@Override
	public void start() {
		
		try {
			ServerBootstrap bootStrap = new ServerBootstrap();
			
			// the parent (acceptor) and the child (client)
			bootStrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 100).option(ChannelOption.SO_KEEPALIVE, true).handler(new LoggingHandler(LogLevel.INFO))
					.childHandler(channelInitializer);
			
			ChannelFuture f = bootStrap.bind(port).sync();
			if (f.isSuccess()) {
				Logger.LOG.info("listen port{}success", port);
			}
			
//			f.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
			close();
		}
	}
	
	@Override
	public void close() {
		bossGroup.shutdownGracefully();
		workerGroup.shutdownGracefully();
	}
}
