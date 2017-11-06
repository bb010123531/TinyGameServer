package org.tiny.net.core;

import org.tiny.net.log.Logger;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * server
 * 
 * @author chunbo
 */

public class Acceptor {
	
	private int port = 8800;
	private ChannelInitializer<SocketChannel> channelInitializer;
	
	private EventLoopGroup bossGroup = new NioEventLoopGroup();
	private EventLoopGroup workerGroup = new NioEventLoopGroup();
	
	public Acceptor(int port, ChannelInitializer<SocketChannel> channelInitializer) {
		this.port = port;
		this.channelInitializer = channelInitializer;
		
		start();
	}
	
	private void start() {
		
		try {
			ServerBootstrap bootStrap = new ServerBootstrap();
			
			// the parent (acceptor) and the child (client)
			bootStrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 100).handler(new LoggingHandler(LogLevel.INFO))
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
	
	// TODO 连接断开的时候 需要手动调用
	private void close() {
		bossGroup.shutdownGracefully();
		workerGroup.shutdownGracefully();
	}
}
