package org.tiny.net.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedThreadFactory implements ThreadFactory{
	
	private AtomicInteger index = new AtomicInteger(0);
	private String name;
	
	private ThreadGroup threadGroup;
	
	public NamedThreadFactory(String name) {
		
		threadGroup = Thread.currentThread().getThreadGroup();
		
		this.name= name;
	}
	
	
	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(threadGroup, r, name + "-" + index.getAndIncrement());
		
		
		return t;
	}

}
