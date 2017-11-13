package org.tiny.net.base;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppContext {
	public static BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	public static void start() {
//		ApplicationContext appContext = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
//		BeanFactory factory = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");  
		
//		BeanFactory factory = ;  
		
        
		// resolve resource leak
//		((FileSystemXmlApplicationContext)appContext).close();
	}
	
	public static BeanFactory getBeanFactory() {
		return factory;
	}
	
	public static void main(String[] args) {
		AppContext.start();
	}
}
