package org.tiny.link.init;

import org.tiny.net.core.AbstractChannelInitializer;

import auto.proto.C2LMessageProto;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;

public class C2LChannelInitializer extends AbstractChannelInitializer{

	@Override
	public void initChannelBefore(SocketChannel ch) {
		ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
		ch.pipeline().addLast(new ProtobufDecoder(C2LMessageProto.C2LMessage.getDefaultInstance()));
//		ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
		ch.pipeline().addLast(new ProtobufEncoder());
	}

}
