package tiny.gs.handler;

import java.util.HashMap;
import java.util.Map;

import tiny.gs.util.AssertUtil;

public class ProtocolHandlerRegisterManager {
	private static final Map<String, ProtocolHandler> handlerMap = new HashMap<>();
	
	public static void init() {
		
	}
	
	public static void addHandler(String msgName, ProtocolHandler handler) {
		handlerMap.put(msgName, handler);
	}
	
	public ProtocolHandler getHandler(String msgName) {
		return AssertUtil.getValue(handlerMap, msgName);
	}
}
