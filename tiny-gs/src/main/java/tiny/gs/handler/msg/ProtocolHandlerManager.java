package tiny.gs.handler.msg;

import org.tiny.net.core.AbstractChannelHandlerAdapter;
import org.tiny.net.log.TinyLogger;


import auto.proto.L2GMessageProto.L2GMessage;
import io.netty.channel.ChannelHandlerContext;
import tiny.gs.task.TaskPool;
import tiny.gs.task.UserTask;

public class ProtocolHandlerManager extends AbstractChannelHandlerAdapter {

	@Override
	public void doActice(ChannelHandlerContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doRead(ChannelHandlerContext ctx, Object msg) {
		L2GMessage l2g = (L2GMessage)msg;
		
		TinyLogger.LOG.debug("Server=====channelRead");
		
		int msgKey = l2g.getContentMsgType();
		
		UserTask task = new UserTask(msgKey, l2g.getContentMsg(), ctx.channel());
		
		//派发到用户的线程池中 让NIO线程及时被释放 处理其他的IO操作
		TaskPool.pool().execute(task);
	}
}
