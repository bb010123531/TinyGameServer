package auto.table;
//auto-gen file, do not edit anyway

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.tiny.base.DBBase;
import org.tiny.base.MongoBase;
import org.tiny.util.SerializationUtil;

import auto.bean.Role;
import auto.bean.Guild;
public class _Tables_ extends org.tiny.base.Tables {

	private DBBase db = new MongoBase();
	private _Tables_ () {
		super("mongo");
		init();
	};

	private static class SingletonFactory {
		private static final _Tables_ tables = new _Tables_();
	}

	public static _Tables_ getInstance() {
		return SingletonFactory.tables;
	}

	public DBBase getDB() {
		return db;
	}

	public void init() {
		add(guilds);
		add(roles);
	}

	org.tiny.base.Table<Long, Role> roles = new org.tiny.base.Table<Long, Role>() {
		@Override
		public String getTableName() {
			return "roles";
		};
	};

	org.tiny.base.Table<Long, Guild> guilds = new org.tiny.base.Table<Long, Guild>() {
		@Override
		public String getTableName() {
			return "guilds";
		};
	};

}
