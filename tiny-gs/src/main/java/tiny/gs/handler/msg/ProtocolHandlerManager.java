package tiny.gs.handler.msg;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import tiny.auto.proto.RoleProto;

public class ProtocolHandlerManager extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub
//		RoleProto.Role req = (RoleProto.Role) msg;  
//        System.err.println(req);
		System.err.println("Server=====channelRead");
		super.channelRead(ctx, msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		System.err.println("Server=====exceptionCaught");
		cause.printStackTrace();  
        ctx.close();  
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.err.println("Server=====channelActive");
		super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.err.println("Server=====channelInactive");
		super.channelInactive(ctx);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.err.println("Server=====channelReadComplete");
		super.channelReadComplete(ctx);
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		System.err.println("Server=====channelRegistered");
		super.channelRegistered(ctx);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		System.err.println("Server=====channelUnregistered");
		super.channelUnregistered(ctx);
	}

	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
		System.err.println("Server=====channelWritabilityChanged");
		super.channelWritabilityChanged(ctx);
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		System.err.println("Server=====userEventTriggered");
		super.userEventTriggered(ctx, evt);
	}
}
