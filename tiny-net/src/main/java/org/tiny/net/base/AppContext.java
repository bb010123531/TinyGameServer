package org.tiny.net.base;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppContext {
	public static BeanFactory factory;
	
	public static void start() {
//		ApplicationContext appContext = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
//		BeanFactory factory = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");  
		
//		BeanFactory factory = ;  
		
        factory = new ClassPathXmlApplicationContext("applicationContext.xml");
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
