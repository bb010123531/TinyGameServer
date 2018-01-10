package org.tiny.base;

/**
 * 
 * @author chunbo
 */
public interface ICacheTrigger {
	public void trigger(byte[] key, byte[] value);
}
