package tiny.gs.util;

import java.util.Map;

public class AssertUtil {
	public static <K, V> V getValue(Map<K,V> map, K k) {
		V v = map.get(k);
		if (v == null) {
			ExceptionUtil.printErr("unknown key for the map, key: " + k );
		}
		
		return v;
	}
}
