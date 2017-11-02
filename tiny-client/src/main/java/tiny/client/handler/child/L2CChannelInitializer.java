package tiny.client.handler.child;

import auto.proto.L2CMessageProto;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import tiny.client.handler.msg.C2SMessageInit;

public class L2CChannelInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel sc) throws Exception {
		sc.pipeline().addLast(new ProtobufVarint32FrameDecoder());  
		sc.pipeline().addLast(new ProtobufDecoder(L2CMessageProto.L2CMessage.getDefaultInstance()));
		sc.pipeline().addLast(new ProtobufEncoder());  
        sc.pipeline().addLast(new C2SMessageInit());  
	}
}
