package org.tiny.link.init;

import org.tiny.net.core.AbstractChannelInitializer;

import auto.proto.G2LMessageProto;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class G2LChannelInitializer extends AbstractChannelInitializer{

	@Override
	public void initChannelBefore(SocketChannel ch) {
		ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
		ch.pipeline().addLast(new ProtobufDecoder(G2LMessageProto.G2LMessage.getDefaultInstance()));
		ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
		ch.pipeline().addLast(new ProtobufEncoder());
	}
}
