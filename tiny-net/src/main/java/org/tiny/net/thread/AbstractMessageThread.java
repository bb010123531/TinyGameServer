package org.tiny.net.thread;

import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.tiny.net.base.IMessage;

public abstract class AbstractMessageThread extends Thread{
	
	protected String name;
	
	protected boolean isRunning;
	
	protected BlockingQueue<IMessage> waitingQueue = new LinkedBlockingQueue<>(1000);
	
	protected Collection<IMessage> runningQueue = new LinkedList<>();
	
	public AbstractMessageThread (String name) {
		super(name);
		this.isRunning = true;
	}
	
	public boolean isRunning() {
		return isRunning;
	}
	
	public void addTask(IMessage e) {
		waitingQueue.offer(e);
	}
	
	@Override
	public void run() {
		
		while (isRunning() || !waitingQueue.isEmpty()) {
			
			try {
				waitingQueue.drainTo(runningQueue);
				
				if (runningQueue.isEmpty()) {
					sleep(5);
					continue;
				}
				
				for (IMessage m : runningQueue) {
					m.process();
				}
				
			} catch (Throwable th) {
				th.printStackTrace();
			} finally {
				runningQueue.clear();
			}
		}
	}
}
