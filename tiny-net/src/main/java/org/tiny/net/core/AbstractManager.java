package org.tiny.net.core;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractManager {
	
	protected Map<String, AbstractIoService> services = new HashMap<>();
	
	// inits
	public AbstractManager() {
	}
	
	protected void build (String name, AbstractIoService ioService) {
		services.put(name, ioService);
		
	}
	
	public void start() {
		for (AbstractIoService io : services.values()) {
			io.start();
		}
	}
	
	// 应该用不到吧 自己单独close吧
	
	public void close() {
		for (AbstractIoService io : services.values()) {
			io.close();
		}
	}
}
