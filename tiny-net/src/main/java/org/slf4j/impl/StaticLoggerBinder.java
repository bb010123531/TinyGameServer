package org.slf4j.impl;

import org.slf4j.ILoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

public class StaticLoggerBinder implements LoggerFactoryBinder{
	
	private static final StaticLoggerBinder SINGLETON = new StaticLoggerBinder();
	private final ILoggerFactory loggerFactory;
	private static final String LOGGER_FACTORY_CLASS_STR = "TinyLogger";
	private StaticLoggerBinder() {
		loggerFactory = new TinyLoggerFactory();
	}
	
	public static StaticLoggerBinder getSingleton() {
		return SINGLETON;
	}
	
	@Override
	public ILoggerFactory getLoggerFactory() {
		return loggerFactory;
	}

	@Override
	public String getLoggerFactoryClassStr() {
		return LOGGER_FACTORY_CLASS_STR;
	}

}
