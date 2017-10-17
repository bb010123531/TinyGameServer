package tiny.gs.util;

import java.util.Map;

public class AssertUtil {
	public static <K, V> V getValue(Map<K,V> map, K k) {
		if (!map.containsKey(k)) {
			ExceptionUtil.printErr("unknown key for the map, key: " + k );
		}
		
		return map.get(k);
	}
}
