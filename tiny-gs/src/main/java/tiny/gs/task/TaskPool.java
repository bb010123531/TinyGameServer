package tiny.gs.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskPool {
	
	private static TaskPool taskPool = new TaskPool();
	
	private TaskPool() {}
	
	public static TaskPool pool() {
		return taskPool;
	}
	
//	private ExecutorService executor = Executors.newFixedThreadPool(16);
	// if load is heavy, may need to control the num of thread or self-diy to construct ThreadPoolExecutor
	private ExecutorService executor = Executors.newCachedThreadPool();
	
	public void execute(Runnable task) {
		executor.execute(task);
	}
}
