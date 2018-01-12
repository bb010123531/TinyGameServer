package org.tiny.base;

import java.lang.reflect.ParameterizedType;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.tiny.net.log.TinyLogger;
import org.tiny.util.SerializationUtil;

public abstract class Table <K, V extends Bean>{
	
	public abstract String getTableName(); 
	
	protected Map<K, Bean> cache = new ConcurrentHashMap<>();
	
	// get tmp store
	protected Map<K, V> dummy = new ConcurrentHashMap<>();
	
	@SuppressWarnings("unchecked")
	public V select(K k) {
		return (V)cache.get(k);
	}
	
	public V get(K k) {
		_safeVerify();
		Transaction t = Transaction.currentTransaction();
		
		V v = dummy.get(k);
		try {
			t.storeTmpBean(getTableName(), k, v);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return v;
	}
	
	// TODO when transaction done, need to modify cache
	@SuppressWarnings("unchecked")
	public void restoreCache(Object k, Bean v) {
		cache.put((K)k, v);
	}
	
	@SuppressWarnings("unchecked")
	public void rollback(Object obj, Bean bean) {
		K k = (K)obj;
		Bean old = cache.get(k);
		if (null == old) {
			V v = dummy.remove(k);
			if (null == v) {
				throw new RuntimeException(getTableName() + " roll back error");
			}
		} else {
			dummy.put(k, (V)old);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void rollback0(Object obj, Bean oldBean) {
		K k = (K)obj;
		cache.put(k, oldBean);
		dummy.put(k, (V)oldBean);
	}
	
	@SuppressWarnings("unchecked")
	private <T> Class<T> getGenericClass(int index) {
		Class <T> entityClass = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[index]; 
		return entityClass;
	} 
	
	public void loadDB2Cache() {
		
		Tables.db.putInCache(getTableName(), (k, v)-> {
			K key = SerializationUtil.deserialize(k, getGenericClass(0));
			V value = SerializationUtil.deserialize(v, getGenericClass(1));
			
			cache.put(key, value);
			dummy.put(key, value);
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
