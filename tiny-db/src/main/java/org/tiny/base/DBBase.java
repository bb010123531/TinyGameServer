package org.tiny.base;

/**
 *  存储序列化后的数据，可以直接存储对象
 *  key   = byte[]
 *  value = byte[]
 * @author chunbo
 */
public interface DBBase {
	
	public void init();
	
	public boolean insert(String collectionName, byte[] key, byte[] value);
	
	public boolean delete(String collectionName, byte[] key);
	
	public boolean update(String collectionname, byte[] key, byte[] value);
	
	public byte[] find(String collectionName, byte[] key);
	
	public void putInCache(String collectionName, ICacheTrigger cache);
}
