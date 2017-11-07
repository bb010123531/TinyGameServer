package tiny.client.handler.child;

import org.tiny.net.core.AbstractChannelInitializer;

import auto.proto.L2CMessageProto;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;

public class L2CChannelInitializer extends AbstractChannelInitializer{

	@Override
	public void initChannelBefore(SocketChannel ch) {
		ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());  
		ch.pipeline().addLast(new ProtobufDecoder(L2CMessageProto.L2CMessage.getDefaultInstance()));
//		ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
		ch.pipeline().addLast(new ProtobufEncoder()); 
	}
}
