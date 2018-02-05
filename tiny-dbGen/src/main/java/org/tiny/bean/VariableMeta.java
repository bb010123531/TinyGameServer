package org.tiny.bean;

import static org.tiny.bean.TransferUtil.*;
import java.io.PrintStream;

import org.dom4j.Element;

/**
 * 
 * @author chunbo
 */
public class VariableMeta {
	
	private String name;
	private String metaType;
	
	private String collectionType;
	
	private String key;
	private String value;
	
	private String defaultValue;
	
	public VariableMeta (Element e) {
		this.name = e.attributeValue("name");
		this.metaType = e.attributeValue("type");
		this.collectionType = e.attributeValue("collectionType");
		this.key = e.attributeValue("key");
		this.value = e.attributeValue("value");
		this.defaultValue = e.attributeValue("default");
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return metaType;
	}
	
	/**
	 * @param ps
	 */
	public void genSetterAndGetter(PrintStream ps) {
		ps.println("	private " + genMemberVariable(metaType, key, value, name, collectionType, defaultValue));
		ps.println();
		ps.println("	public " + metaType2JavaBaseType(metaType, name, key, value) + " get" + toFirstUpper(name) + "() {");
		ps.println("		return " + name + ";");
		ps.println("	}");
		ps.println();
		ps.println("	public void set" + toFirstUpper(name) + "(" + metaType2JavaBaseType(metaType, name, key, value) + " "
				+ name + ") {");
		ps.println("		this." + name + " = " + name + ";");
		ps.println("	}");
		ps.println();
	}
	
	/**
	 * java.util.Map<Character, String> bagClone = new java.util.HashMap<>(bag.size());
		for (java.util.Map.Entry<Character, String> e : bag.entrySet()) {
			bagClone.put(e.getKey(), e.getValue());
		}
		clone.setBag(bagClone);
		
		java.util.List<auto.bean.Friend> friendsClone = new java.util.ArrayList<>(friends.size());
		for (auto.bean.Friend friend : friends) {
			friendsClone.add(friend.clone());
		}
		clone.setFriends(friendsClone);
		
	 * @param ps
	 */
	public void genDeepClone(PrintStream ps) {
		
		switch (metaType.toLowerCase()) {
		case "int":
		case "long":
		case "string":
		case "bool":
		case "boolean":
		case "short":
		case "byte":
		case "char":
			// base type, donot need clone
			return;
			
		case "map":
			String mapSize = name + ".size()";
			ps.println("		" + genMemberVariable(metaType, key, value, name + "Clone", collectionType, defaultValue, mapSize));
			ps.println("		for(java.util.Map.Entry<" + getRawTypeByBaseType(key) + ", " + getRawTypeByBaseType(value) + "> e : " + name + ".entrySet()) {");
			ps.println("			" + name + "Clone.put(e.getKey(), e.getValue()" + valueIsBean(value) + ");");
			ps.println("		}");
			ps.println("		clone.set" + toFirstUpper(name) + "(" + name + "Clone);");
			ps.println();
			return;
		case "list":
			String listSize = name + ".size()";
			ps.println("		" + genMemberVariable(metaType, key, value, name + "Clone", collectionType, defaultValue, listSize));
			ps.println("		for(" + getRawTypeByBaseType(value) + " bean : " + name + ") {");
			ps.println("			" + name + "Clone.add(bean" + valueIsBean(value) + ");");
			ps.println("		}");
			ps.println("		clone.set" + toFirstUpper(name) + "(" + name + "Clone);");
			ps.println();
			return;
		default:
//			throw new RuntimeException("unhandled type " + metaType.toLowerCase());
			//clone.setGirlFriend(girlFriend.toString());
			ps.println("		clone.set" + toFirstUpper(name) + "(" + toFirstLower(name) + ".clone());");
			return;
		}
	}
	
	public boolean isBean(String value) {
		switch (value.toLowerCase()) {
		case "int":
		case "long":
		case "string":
		case "bool":
		case "boolean":
		case "short":
		case "byte":
		case "char":
			return false;
		
		default:
			return true;
		}
	}
	
	private String valueIsBean(String value) {
		if (isBean(value)) {
			return ".clone()";
		} else {
			return "";
		}
	}
}
