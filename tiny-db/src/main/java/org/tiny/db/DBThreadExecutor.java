package org.tiny.db;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DBThreadExecutor {
	
	private static ExecutorService executor = Executors.newCachedThreadPool();
	
	public static void execute(Runnable command) {
		executor.execute(command);
	}
}
