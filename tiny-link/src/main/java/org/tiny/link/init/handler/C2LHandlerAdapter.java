package org.tiny.link.init.handler;

import org.tiny.net.core.AbstractChannelHandlerAdapter;

import auto.proto.C2LMessageProto.C2LMessage;
import io.netty.channel.ChannelHandlerContext;

public class C2LHandlerAdapter extends AbstractChannelHandlerAdapter{

	@Override
	public void doActice(ChannelHandlerContext ctx) {
		// maybe need to restore
		System.err.println("收到Client的连接~");
	}

	@Override
	public void doRead(ChannelHandlerContext ctx, Object msg) {
		C2LMessage c2l = (C2LMessage)msg;
		
		// 转发给GS
	}
}
