package org.tiny.link.init.handler;

import org.tiny.link.channel.ChannelManager;
import org.tiny.net.core.AbstractChannelHandlerAdapter;
import org.tiny.net.log.TinyLogger;

import auto.proto.C2LMessageProto.C2LMessage;
import io.netty.channel.ChannelHandlerContext;
import tools.ProtocolUtil;

public class C2LHandlerAdapter extends AbstractChannelHandlerAdapter{


	@Override
	public void doActice(ChannelHandlerContext ctx) {
		// maybe need to restore
		TinyLogger.LOG.debug("收到Client的连接");
		String clientChannelId = ctx.channel().id().asLongText();
		ChannelManager.getInstance().addToClientChannel(clientChannelId, ctx.channel());
	}

	@Override
	public void doRead(ChannelHandlerContext ctx, Object msg) {
		C2LMessage c2l = (C2LMessage)msg;
		
		String clientChannelId = ctx.channel().id().asLongText();
		// 转发给GS
//		System.err.println("发给Gs~");
		ChannelManager.getInstance().getGs().writeAndFlush(ProtocolUtil.toL2GMessage(clientChannelId, c2l.getContentMsgType(), c2l.getContentMsg()));
	}
}
