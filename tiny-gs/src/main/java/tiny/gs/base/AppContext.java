package tiny.gs.base;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import tiny.gs.handler.MsgHandler;
import tiny.gs.handler.ProtocolHandler;
import tiny.gs.handler.ProtocolHandlerRegisterManager;

public class AppContext {
	
	
	public static void start() {
		ApplicationContext appContext = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
		Map<String, Object> handlerInstanceMap = appContext.getBeansWithAnnotation(MsgHandler.class);
		for (Map.Entry<String, Object> entry : handlerInstanceMap.entrySet()) {
            MsgHandler msgHandler = entry.getValue().getClass().getAnnotation(MsgHandler.class);
            if (null != msgHandler) {
            	String msgName = msgHandler.msgName();
            	ProtocolHandlerRegisterManager.addHandler(msgName, (ProtocolHandler)entry.getValue());
            }
        }
		
		// resolve resource leak
		((FileSystemXmlApplicationContext)appContext).close();
	}
}
