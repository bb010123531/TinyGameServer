package tiny.gs.handler.child;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import tiny.auto.proto.RoleProto;
import tiny.gs.handler.msg.ProtocolHandlerManager;

public class C2GChannelInitializer extends ChannelInitializer<SocketChannel>{
	@Override
	protected void initChannel(SocketChannel sc) throws Exception {
		// TODO Auto-generated method stub
		 sc.pipeline().addLast(new ProtobufVarint32FrameDecoder());
         sc.pipeline().addLast(new ProtobufDecoder(RoleProto.C2SRoleInfo.getDefaultInstance()));
         sc.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
         sc.pipeline().addLast(new ProtobufEncoder());
         sc.pipeline().addLast(new ProtocolHandlerManager());
	}
}
