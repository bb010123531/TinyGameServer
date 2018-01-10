package org.tiny.base;

import java.lang.reflect.ParameterizedType;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.tiny.net.log.TinyLogger;
import org.tiny.util.SerializationUtil;

public abstract class Table <K, V extends Bean>{
	
	public abstract String getTableName(); 
	
	protected Map<K, V> cache = new ConcurrentHashMap<>();
	
	// get tmp store
	protected Map<K, V> dummy = new ConcurrentHashMap<>();
	
	public V select(K k) {
		return cache.get(k);
	}
	
	public V get(K k) {
		_safeVerify();
		Transaction t = Transaction.currentTransaction();
		
		V v = dummy.get(k);
		t.storeTmpBean(getTableName(), k, v);
		
		return cache.get(k);
	}
	
	// TODO when transaction done, need to modify cache
	public void restoreCache(K k, V v) {
		cache.put(k, v);
	}
	
	@SuppressWarnings("unchecked")
	private <T> Class<T> getGenericClass(int index) {
		Class <T> entityClass = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[index]; 
		return entityClass;
	} 
	
	public void loadDB2Cache() {
		
		Tables.db.putInCache(getTableName(), (k, v)-> {
			cache.put(SerializationUtil.deserialize(k, getGenericClass(0)), SerializationUtil.deserialize(v, getGenericClass(1)));
			dummy.put(SerializationUtil.deserialize(k, getGenericClass(0)), SerializationUtil.deserialize(v, getGenericClass(1)));
		});
		
		TinyLogger.LOG.info("load " + getTableName() + " success ~~~ ");
	}
	
	protected boolean _safeVerify() {
		if (Transaction.currentTransaction() == null) {
			return false;
		}
		
		return true;
	}
}
