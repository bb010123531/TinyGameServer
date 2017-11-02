package tiny.gs.net;

import org.tiny.net.core.Acceptor;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import tiny.gs.config.Conf;
import tiny.gs.handler.child.C2GChannelInitializer;

/**
 * @author chunbo
 */

public class GameServer {
	public static void start() {
		
//		EventLoopGroup bossGroup = new NioEventLoopGroup();
//		EventLoopGroup workGroup = new NioEventLoopGroup();
//		
//		try {
//			ServerBootstrap b = new ServerBootstrap();
//			b.group(bossGroup, workGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 100)
//					.handler(new LoggingHandler(LogLevel.INFO)).childHandler(new C2GChannelInitializer());
//			
//			// start  the server
//			ChannelFuture f = b.bind(Conf.getServerPort()).sync();
//			
//			// wait for close
//			f.channel().closeFuture().sync();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			bossGroup.shutdownGracefully();
//			workGroup.shutdownGracefully();
//		}
		new Acceptor(Conf.getServerPort(), new C2GChannelInitializer());
	}
}
