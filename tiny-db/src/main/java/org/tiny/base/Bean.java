package org.tiny.base;

public interface Bean extends Cloneable{
	
	Bean clone() throws CloneNotSupportedException;
	
	default public boolean _varify() {
		return true;
	};
}
