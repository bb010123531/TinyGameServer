package tiny.client.handler.msg;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import tiny.auto.proto.RoleProto;

public class C2SMessageInit extends ChannelInboundHandlerAdapter {
	
	@Override  
    public void channelActive(ChannelHandlerContext ctx) {  
//        System.out.println("=======================================");  
//        
		System.err.println("=====channelActive");
//        RoleProto.Role.Builder builder = RoleProto.Role.newBuilder();
//        builder.setId(1);
//        builder.setLevel(10);
//        builder.setNickName("first");

		RoleProto.C2SRoleInfo.Builder builder = RoleProto.C2SRoleInfo.newBuilder();
		builder.setId(11);
		builder.setContent("Hello World");
		
        ctx.writeAndFlush(builder.build());  
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
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.err.println("=====channelRead");
		super.channelRead(ctx, msg);
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
