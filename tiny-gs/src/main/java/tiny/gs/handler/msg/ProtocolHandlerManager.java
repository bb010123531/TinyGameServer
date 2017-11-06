package tiny.gs.handler.msg;

import org.tiny.net.core.AbstractChannelHandlerAdapter;

import io.netty.channel.ChannelHandlerContext;
import tiny.auto.proto.RoleProto;

public class ProtocolHandlerManager extends AbstractChannelHandlerAdapter {

	@Override
	public void doActice(ChannelHandlerContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doRead(ChannelHandlerContext ctx, Object msg) {
		RoleProto.C2SRoleInfo req = (RoleProto.C2SRoleInfo) msg;  
		System.err.println(req);
		System.err.println("Server=====channelRead");
	}
}
