package org.tiny.net.core;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public abstract class AbstractChannelHandlerAdapter extends ChannelInboundHandlerAdapter {
	protected AbstractIoService ioService;
	
	public void setIoService(AbstractIoService ioService) {
		this.ioService = ioService;
	}
	
	public abstract void doActice(ChannelHandlerContext ctx);
	public abstract void doRead(ChannelHandlerContext ctx, Object msg);
	
	private void doInactive(ChannelHandlerContext ctx) {
		ioService.close();
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		System.err.println("=====channelActive");
		doActice(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.err.println("=====channelRead");
		doRead(ctx, msg);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		System.err.println("=====exceptionCaught");
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.err.println("=====channelInactive");
		super.channelInactive(ctx);
		doInactive(ctx);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.err.println("=====channelReadComplete");
		super.channelReadComplete(ctx);
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		System.err.println("=====channelRegistered");
		super.channelRegistered(ctx);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		System.err.println("=====channelUnregistered");
		super.channelUnregistered(ctx);
	}

	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
		System.err.println("=====channelWritabilityChanged");
		super.channelWritabilityChanged(ctx);
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		// TODO Auto-generated method stub
		System.err.println("=====userEventTriggered");
		super.userEventTriggered(ctx, evt);
	}
}
