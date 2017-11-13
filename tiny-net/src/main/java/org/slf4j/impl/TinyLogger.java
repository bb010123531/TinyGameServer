package org.slf4j.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TinyLogger {
	public String name;
	public TinyLogger(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public static void main(String[] args) {
		//slf4j
		Logger log = LoggerFactory.getLogger(TinyLogger.class);
		log.debug("这是一条debug测试log");
		log.error("这是一条error测试log");
	}
}
