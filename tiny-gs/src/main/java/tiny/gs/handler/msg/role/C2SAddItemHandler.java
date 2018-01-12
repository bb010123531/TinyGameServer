package tiny.gs.handler.msg.role;

import org.tiny.net.log.TinyLogger;

import com.google.protobuf.InvalidProtocolBufferException;

import auto.proto.Enum;
import auto.proto.RoleProto.C2SAddItem;
import tiny.gs.handler.MsgHandler;
import tiny.gs.handler.ProtocolHandler;
import tiny.gs.handler.msg.role.procedure.PAddItem;
import tiny.gs.task.UserTask;

@MsgHandler(msgName = "C2SAddItem", msgKey = Enum.PROTO_KEY.C2SAddItem_Key_VALUE)
public class C2SAddItemHandler implements ProtocolHandler{

	@Override
	public void process(UserTask userTask) throws InvalidProtocolBufferException {
		C2SAddItem c2s = C2SAddItem.parseFrom(userTask.getContentMsg());
		TinyLogger.LOG.debug("\nrec : " + c2s);
		
		new PAddItem(c2s.getItemId(), c2s.getItemNum()).submit();
	}
}
