package org.tiny.db;

import org.tiny.net.thread.AbstractMessageThread;

public class DBThread extends AbstractMessageThread{
	
	private static final String NAME = "DB-THREAD";
	public static DBThread instance = new DBThread();
	
	private DBThread() {
		super(NAME);
	}
	
	public static DBThread getInstance() {
		return instance;
	}
}
