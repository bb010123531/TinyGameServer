package org.tiny.util;

import java.util.HashMap;
import java.util.Map;

import org.tiny.auto.bean.Role;

public class TestClone {
	
	public static void main(String[] args) {
		
		Map<Integer, Role> map1 = new HashMap<>();
		Map<Integer, Role> map2 = new HashMap<>();
		
		Role role = new Role();
		role.setId("1");
		Map<String, Integer> bag = new HashMap<>();
		bag.put("1001", 20);
		role.setBag(bag);
		
		try {
			Role role2 = role.clone();
			
			map1.put(1, role);
			map2.put(1, role2);
			
			map1.get(1).getBag().clear();
			
			System.err.println(map1);
			System.err.println(map2);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
