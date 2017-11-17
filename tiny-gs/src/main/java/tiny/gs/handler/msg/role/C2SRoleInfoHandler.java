package tiny.gs.handler.msg.role;

import org.tiny.net.log.TinyLogger;

import com.google.protobuf.InvalidProtocolBufferException;

import auto.proto.Enum;
import auto.proto.L2GMessageProto.L2GMessage;
import auto.proto.RoleProto.C2SRoleInfo;
import tiny.gs.handler.MsgHandler;
import tiny.gs.handler.ProtocolHandler;

@MsgHandler(msgName = "C2SRoleInfo", msgKey = Enum.PROTO_KEY.C2SRoleInfo_Key_VALUE)
public class C2SRoleInfoHandler implements ProtocolHandler{

	@Override
	public void process(L2GMessage l2g) throws InvalidProtocolBufferException {
		C2SRoleInfo c2s = C2SRoleInfo.parseFrom(l2g.getContentMsg());
		TinyLogger.LOG.debug("\nrec : " + c2s);
	}
}
