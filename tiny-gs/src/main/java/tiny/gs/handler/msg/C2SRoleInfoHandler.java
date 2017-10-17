package tiny.gs.handler.msg;

import tiny.gs.handler.MsgHandler;
import tiny.gs.handler.ProtocolHandler;

@MsgHandler(msgName = "C2SRoleInfo")
public class C2SRoleInfoHandler implements ProtocolHandler{

	@Override
	public void process() {
		System.err.println("HaHa ~~ ");
	}
}
