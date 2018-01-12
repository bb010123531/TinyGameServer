package org.tiny.base;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.tiny.db.DBBootstrap;
import org.tiny.db.DBMessage;
import org.tiny.db.DBOption;
import org.tiny.db.DBThread;
import org.tiny.net.tool.CollectionUtil;
import org.tiny.util.SerializationUtil;

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
	
	public void storeTmpBean(String tableName, Object key, Bean bean) throws CloneNotSupportedException {
		addOldData(tableName, key, bean);
		CollectionUtil.add2MapMap(modifiedData, tableName, key, bean);
	}
	
	// only store first time
	private void addOldData(String tableName, Object key, Bean bean) throws CloneNotSupportedException {
		if (!oldData.containsKey(tableName) || !oldData.get(tableName).containsKey(key)) {
			CollectionUtil.add2MapMap(oldData, tableName, key, bean.clone());
			System.err.println();
		}
	}
	
	// TODO to commit from the stored beans
	public void commit() {
		for (Entry<String, Map<Object, Bean>> e : modifiedData.entrySet()) {
			String tableName = e.getKey();
			
			for (Entry<Object, Bean> map : e.getValue().entrySet()) {
				byte[] key = SerializationUtil.serialize(map.getKey());
				byte[] value = SerializationUtil.serialize(map.getValue());
				
				DBThread.getInstance().addTask(new DBMessage(DBOption.Update, tableName, key, value));
				DBBootstrap.getInstance().getTables().getTableByName(tableName).restoreCache(map.getKey(), map.getValue());
			}
		}
	}
	
	public void rollback() {
		for (Entry<String, Map<Object, Bean>> e : modifiedData.entrySet()) {
			String tableName = e.getKey();
			
			for (Entry<Object, Bean> map : e.getValue().entrySet()) {
				DBBootstrap.getInstance().getTables().getTableByName(tableName).rollback(map.getKey(), map.getValue());
			}
		}
	}
	
	public void rollback0() {
		for (Entry<String, Map<Object, Bean>> e : oldData.entrySet()) {
			String tableName = e.getKey();
			
			for (Entry<Object, Bean> map : e.getValue().entrySet()) {
				DBBootstrap.getInstance().getTables().getTableByName(tableName).rollback0(map.getKey(), map.getValue());
			}
		}
	}
	
}
