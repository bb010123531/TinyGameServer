package tiny.gs.handler.msg;

import org.tiny.net.core.AbstractChannelHandlerAdapter;

import com.google.protobuf.InvalidProtocolBufferException;

import auto.proto.L2GMessageProto.L2GMessage;
import io.netty.channel.ChannelHandlerContext;
import tiny.gs.handler.ProtocolHandler;
import tiny.gs.handler.ProtocolHandlerRegisterManager;

public class ProtocolHandlerManager extends AbstractChannelHandlerAdapter {

	@Override
	public void doActice(ChannelHandlerContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doRead(ChannelHandlerContext ctx, Object msg) {
		L2GMessage l2g = (L2GMessage)msg;
		
		System.err.println("Server=====channelRead");
		
		int msgKey = l2g.getContentMsgType();
		
		ProtocolHandler handler = ProtocolHandlerRegisterManager.getHandler(msgKey);
		if (handler == null) {
			System.err.println("unhandled msg type : " + msgKey);
			return;
		}
		
		try {
			handler.process(l2g);
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
	}
}
