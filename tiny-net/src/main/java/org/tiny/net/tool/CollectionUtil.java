package org.tiny.net.tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionUtil {
	
	public static <K, KK, VV> void add2MapMap(Map<K, Map<KK, VV>> mapMap, K k, KK kk, VV vv) {
		Map<KK, VV> map = mapMap.get(k);
		if (null == map) {
			map = new HashMap<>();
			mapMap.put(k, map);
		}
		map.put(kk, vv);
	}
	
	public static <K, V> void add2MapSet(Map<K, Set<V>> mapSet, K k, V v) {
		Set<V> set = mapSet.get(k);
		if (null == set) {
			set = new HashSet<>();
			mapSet.put(k, set);
		}
		set.add(v);
	}
	
    public static <K, V> void add2MapList(Map<K, List<V>> mapList, K k, V v) {
    	List<V> list = mapList.get(k);
    	if (null == list) {
    		list = new ArrayList<>();
    		mapList.put(k, list);
    	}
    	list.add(v);
    }
    
    public static <K> void incrMapIntValue(Map<K, Integer> map, K k, int v) {
    	Integer value = map.get(k);
    	if (null == value) {
    		map.put(k, v);
    	} else {
    		map.put(k, value + v);
    	}
    }
}


