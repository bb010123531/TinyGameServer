package org.tiny.db;

import org.tiny.net.base.IMessage;

public class DBMessage implements IMessage{

	private final String NAME = "DBMessage";
	
	private DBOption option;
	
	private String tableName;
	private byte[] key;
	private byte[] value;
	
	public DBMessage(DBOption option, String tableName, byte[] key) {
		this(option, tableName, key, null);
	}
	
	public DBMessage(DBOption option, String tableName, byte[] key, byte[] value) {
		this.option = option;
		this.tableName = tableName;
		this.key = key;
		this.value = value;
	}
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void process() {
		switch(option) {
		case Update:
			DBBootstrap.getInstance().getTables().getDB().update(tableName, key, value);
			break;
		
		}
	}
}
