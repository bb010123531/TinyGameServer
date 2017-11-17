package org.tiny.link.init.handler;

import org.tiny.link.channel.ChannelManager;
import org.tiny.net.core.AbstractChannelHandlerAdapter;
import org.tiny.net.log.TinyLogger;

import io.netty.channel.ChannelHandlerContext;

public class G2LHandlerAdapter extends AbstractChannelHandlerAdapter{

	@Override
	public void doActice(ChannelHandlerContext ctx) {
		TinyLogger.LOG.debug("GS 连接成功");
		// 存下 GS的信息
		
		ChannelManager.getInstance().setGs(ctx.channel());
	}

	@Override
	public void doRead(ChannelHandlerContext ctx, Object msg) {
		
	}
}
