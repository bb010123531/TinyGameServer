package auto.table;
//auto-gen file, do not edit anyway

public class Guilds {

	public static auto.bean.Guild select(long key) {
		return _Tables_.getInstance().guilds.select(key);
	}

	public static auto.bean.Guild get(long key) {
		return _Tables_.getInstance().guilds.get(key);
	}

}
