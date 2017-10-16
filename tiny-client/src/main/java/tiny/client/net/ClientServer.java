package tiny.client.net;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import tiny.client.handler.msg.C2SMessageInit;

public class ClientServer {
	
	public static void start() {
		// 配置客户端NIO线程组  
        EventLoopGroup group = new NioEventLoopGroup();  
        try {  
            Bootstrap b = new Bootstrap();  
            b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)  
                    .handler(new ChannelInitializer<SocketChannel>() {  
                        @Override  
                        public void initChannel(SocketChannel ch) throws Exception {  
                            ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());  
                            ch.pipeline().addLast(new ProtobufEncoder());  
                            ch.pipeline().addLast(new C2SMessageInit());  
                        }  
                    });  
  
            // 发起异步连接操作  
            ChannelFuture f = b.connect("127.0.0.1", 8800).sync();  
  
            // 当代客户端链路关闭  
            f.channel().closeFuture().sync();  
        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {  
            // 优雅退出，释放NIO线程组  
            group.shutdownGracefully();  
        }  
	}
}
