package org.tiny.base;

import org.tiny.db.DBThreadExecutor;
import org.tiny.db.TransactionThread;

public abstract class Procedure {
	public abstract boolean process();
	
	public void submit() {
		// TODO 创建一个线程 去做
		DBThreadExecutor.execute(new TransactionThread(this));
	}
}
