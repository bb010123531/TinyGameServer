package org.tiny.link.init.handler;

import org.tiny.net.core.AbstractChannelHandlerAdapter;

import io.netty.channel.ChannelHandlerContext;

public class G2LHandlerAdapter extends AbstractChannelHandlerAdapter{

	@Override
	public void doActice(ChannelHandlerContext ctx) {
		System.err.println("GS 连接成功");
		// 存下 GS的信息
	}

	@Override
	public void doRead(ChannelHandlerContext ctx, Object msg) {
		// TODO Auto-generated method stub
		
	}

}
