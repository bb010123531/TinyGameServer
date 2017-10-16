package tiny.client.handler.child;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import tiny.auto.proto.RoleProto;

public class G2SChannelInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel sc) throws Exception {
		 sc.pipeline().addLast(new ProtobufVarint32FrameDecoder());
         sc.pipeline().addLast(new ProtobufDecoder(RoleProto.Role.getDefaultInstance()));
         sc.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
         sc.pipeline().addLast(new ProtobufEncoder());
//         sc.pipeline().addLast(new SubReqClientHandler());
     
	}
}
