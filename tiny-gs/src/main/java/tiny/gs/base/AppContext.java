package tiny.gs.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.tiny.net.log.TinyLogger;

import tiny.auto.bean.Role;
import tiny.gs.db.RoleDao;
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
//            	ProtocolHandlerRegisterManager.addHandler(msgName, (ProtocolHandler)entry.getValue());
            	ProtocolHandlerRegisterManager.addHandler(msgHandler.msgKey(), (ProtocolHandler)entry.getValue());
            	TinyLogger.LOG.error("register handler " + msgHandler.msgKey() + " " + msgName);
            }
        }
		
		RoleDao roleDaoImpl = (RoleDao) appContext.getBean("roleDaoImpl");   
		String collectionName ="roles";  
		
		//添加一百个role  
//        for(int i=0;i<100;i++){  
//            Role role =new Role();  
//            role.setId(""+i);  
//            role.setAge(i);  
//            role.setName("zcy"+i);  
//            role.setPassword("zcy"+i);  
//            roleDaoImpl.insert(role,collectionName);  
//        }  
        
        Map<String,Object> params=new HashMap<String,Object>();  
        params.put("maxAge", 50);  
        
        List<Role> list=roleDaoImpl.findAll(params,collectionName);  
        
        TinyLogger.LOG.error("role.count() == " + list.size());
        
		// resolve resource leak
		((FileSystemXmlApplicationContext)appContext).close();
	}
}
