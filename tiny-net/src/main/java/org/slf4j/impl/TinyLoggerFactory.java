package org.slf4j.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

public class TinyLoggerFactory implements ILoggerFactory{

	ConcurrentMap<String, Logger> loggerMap;
	
	public TinyLoggerFactory() {
		loggerMap = new ConcurrentHashMap<>();
		
	}
	
	@Override
	public Logger getLogger(String name) {
		Logger slf4gLogger = loggerMap.get(name);
		if (slf4gLogger != null) {
			return slf4gLogger;
		} else {
//			TinyLogger test = new TinyLogger(name);
			
			Logger newInstance = new TinyLoggerAdapter(name);
			Logger oldInstance = loggerMap.putIfAbsent(name, newInstance);
			
			return oldInstance == null ? newInstance : oldInstance;
		}
	}

}
