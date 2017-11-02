package org.tiny.net.log;

public class Logger {
	private Logger(){}
	
	public static final org.apache.logging.log4j.Logger LOG =
			org.apache.logging.log4j.LogManager.getLogger(Logger.class);
	
}
