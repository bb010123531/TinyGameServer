package org.tiny.db;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.tiny.util.SerializationUtil;

import tiny.auto.bean.Friend;
import tiny.auto.bean.Role;

public class TestSerialization {

	public static void main(String[] args) {
		Role role = new Role();
		role.setId("iY7Q9PBS");
		role.setAge(20);
		role.setName("NewBee");
		role.setPassword("gnOwbCdh");
		role.setRoleId(4096L);
		
		List<Friend> friends = new ArrayList<>(5);
		for (int i = 0 ; i < 5 ; i++) {
			Friend f = new Friend();
			
			List<Long> fList = Arrays.asList(1L, 2L, 3L, 4L, 5L);
			List<Long> aList = Arrays.asList(5L, 6L, 7L, 8L, 9L);
			f.setFriends(fList);
			f.setApplyList(aList);
			
			friends.add(f);
		}
		
		role.setFriends(friends);
		
		byte[] roleS = SerializationUtil.serialize(role);
		
		System.err.println(roleS);
		
		Role dRole = SerializationUtil.deserialize(roleS, Role.class);
		
		System.err.println(dRole);
	}
}
