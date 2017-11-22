package org.tiny.net.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * default
 * @author chunbo
 */
public class NamedThreadFactory implements ThreadFactory{
	
	private final AtomicInteger threadNumber = new AtomicInteger(1);
	private final String namePrefix;
	private final ThreadGroup group;
	
	public NamedThreadFactory(String name) {
		SecurityManager s = System.getSecurityManager();
		group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
		
		namePrefix = name + "-";
	}
	
	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
		
		if (t.isDaemon()) {
			t.setDaemon(false);
		}
		
		if (t.getPriority() != Thread.NORM_PRIORITY) {
			t.setPriority(Thread.NORM_PRIORITY);
		}
		
		return t;
	}
}
