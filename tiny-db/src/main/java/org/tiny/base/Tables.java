package org.tiny.base;

import java.util.HashMap;
import java.util.Map;

public class Tables {
	public static DBBase db = null;
	
	private Map<String, Table<?, ?>> tables = new HashMap<>();
	
	public Tables (String dbType) {
		db = new MongoBase();
	}
	
	protected void add(Table<?, ?> t) {
		tables.put(t.getTableName(), t);
	}
	
	public void loadToCache() {
		for (Table<?, ?> t : tables.values()) {
			t.loadDB2Cache();
		}
	}
}	
