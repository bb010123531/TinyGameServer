package org.tiny.base;

import java.util.HashMap;
import java.util.Map;

import org.tiny.net.tool.CollectionUtil;

/**
 * copy before transaction
 * if true :
 * 	commit() -> db.update()
 * else :
 * 	rollback() -> may be need to do nothing 
 *
 * @author chunbo
 */
public class Transaction {
	
	private Map<String, Map<Object, Bean>> modifiedData = new HashMap<>();
	
	private Map<String, Map<Object, Bean>> oldData = new HashMap<>();
	
	
	private static ThreadLocal<Transaction> runtimeTransaction = new ThreadLocal<Transaction>();
	
	public static Transaction create() {
		Transaction cur = currentTransaction();
		if (cur == null) {
			cur = new Transaction();
			runtimeTransaction.set(cur);
		} else {
			System.err.println(" creat 前 竟然还会有  transaction ？");
		}
		return cur;
	}
	
	public static Transaction currentTransaction() {
		return runtimeTransaction.get();
	}
	
	public static void end() {
		runtimeTransaction.set(null);
	}
	
	public void storeTmpBean(String tableName, Object key, Bean bean) {
		CollectionUtil.add2MapMap(modifiedData, tableName, key, bean);
	}
	
	// TODO to commit from the stored beans
	public void commit() {
		
	}
	
}
