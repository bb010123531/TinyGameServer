package tiny.gs.db;

import tiny.auto.table._Tables_;

public class DBBootstrap {
	
	private static DBBootstrap boot = new DBBootstrap();
	
	public static DBBootstrap getInstance() {
		return boot;
	}
	
	private volatile boolean isOpen = false;
	
	private _Tables_ tables = null;
	
	public void start (String path) {
		tables = _Tables_.getInstance();
		tables.init();
		tables.loadToCache();
	}
}
