package tiny.gs.task;

import org.tiny.net.log.TinyLogger;

import com.google.protobuf.InvalidProtocolBufferException;

import io.netty.channel.Channel;
import tiny.gs.handler.ProtocolHandler;
import tiny.gs.handler.ProtocolHandlerRegisterManager;

public class UserTask implements Runnable{
	
	private int msgKey;
	private com.google.protobuf.ByteString contentMsg;
	private Channel channel;
	
	public UserTask(int msgKey, com.google.protobuf.ByteString contentMsg, Channel channel) {
		this.msgKey = msgKey;
		this.contentMsg = contentMsg;
		this.channel = channel;
	}
	
	@Override
	public void run() {
		ProtocolHandler handler = ProtocolHandlerRegisterManager.getHandler(msgKey);
		if (handler == null) {
			TinyLogger.LOG.error("unhandled msg type : " + msgKey);
			return;
		}
		
		try {
			handler.process(this);
		} catch (InvalidProtocolBufferException e) {
			TinyLogger.LOG.error("handler throw exception" + e.getMessage());
			e.printStackTrace();
		}
	} 
	
	public com.google.protobuf.ByteString getContentMsg() {
		return contentMsg;
	}
	
	public void sendBask(Object msg) {
		channel.writeAndFlush(msg);
	}
}
