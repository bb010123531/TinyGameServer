package tiny.gs.handler.msg.role;

import org.tiny.net.log.TinyLogger;

import com.google.protobuf.InvalidProtocolBufferException;

import auto.proto.Enum;
import auto.proto.RoleProto.C2SComplexTest;
import tiny.gs.handler.MsgHandler;
import tiny.gs.handler.ProtocolHandler;
import tiny.gs.task.UserTask;

@MsgHandler(msgName = "C2SComplexTest", msgKey = Enum.PROTO_KEY.C2SComplexTest_Key_VALUE)
public class C2SComplexTestHandler implements ProtocolHandler{

	@Override
	public void process(UserTask userTask) throws InvalidProtocolBufferException {
		// TODO Auto-generated method stub
		C2SComplexTest c2s = C2SComplexTest.parseFrom(userTask.getContentMsg());
		TinyLogger.LOG.debug("\nrec :" + c2s);
	}
}
