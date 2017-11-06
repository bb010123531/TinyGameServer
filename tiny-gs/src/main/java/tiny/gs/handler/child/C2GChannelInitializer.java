package tiny.gs.handler.child;

import org.tiny.net.core.AbstractChannelInitializer;

import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import tiny.auto.proto.RoleProto;

public class C2GChannelInitializer extends AbstractChannelInitializer{
	
	@Override
	public void initChannelBefore(SocketChannel ch) {
		ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
		ch.pipeline().addLast(new ProtobufDecoder(RoleProto.C2SRoleInfo.getDefaultInstance()));
		ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
		ch.pipeline().addLast(new ProtobufEncoder());
	}
}
