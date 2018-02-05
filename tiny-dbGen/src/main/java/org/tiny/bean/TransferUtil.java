package org.tiny.bean;

import org.tiny.dbGen.Gen;

/**
 * 
 * @author chunbo
 */
public class TransferUtil {
	private static final String NONE = "";
	public static String metaType2JavaBaseType(String type, String name, String key, String value) {
		switch(type.toLowerCase()) {
		case "int":
			return "int";
			
		case "long":
			return "long";
			
		case "string":
			return "String";
			
		case "bool":
		case "boolean":
			return "boolean";
		
		case "short":
			return "short";
			
		case "byte":
			return "byte";
			
		case "char":
			return "char";
			
		case "list":
			notNull(name + " list value", value);
			return "java.util.List<" + getRawTypeByBaseType(value) + ">";
		
		case "map":
			notNull(name + " map key", key);
			notNull(name + " map value", value);
			return "java.util.Map<" + getRawTypeByBaseType(key) + ", " + getRawTypeByBaseType(value) + ">";
			
		default:
			return Gen.packageExtraBean + "." + toFirstUpper(type);
		}
	}
	
	public static String genMemberVariable(String type, String key, String value, String name, String collectionType, String defaultValue) {
		return genMemberVariable(type, key, value, name, collectionType, defaultValue, NONE);
	}
	
	public static String genMemberVariable(String type, String key, String value, String name, String collectionType, String defaultValue, String collectionsSize) {
		return metaType2JavaBaseType(type, name, key, value) + " " + name + genDefaultValue(type, name, defaultValue, collectionType, collectionsSize) + ";";
	}
	
	private static String genDefaultValue(String type, String name, String defaultValue, String collectionType, String collectionsSize) {
		if (isBaseType(type) && (defaultValue == null || defaultValue.length() == 0)) {
			return NONE;
		}
		
		switch(type.toLowerCase()) {
		
		case "long":
			return " = " + defaultValue + "L";
			
		case "string":
			return " = \"" + ((defaultValue == null || defaultValue.isEmpty()) ? NONE:defaultValue) + "\"";
			
		case "char":
			return " = \'" + defaultValue + "\'";
			
		case "int":	
		case "short":	
		case "byte":	
		case "bool":
		case "boolean":
			return " = " + defaultValue;
			
		case "list":
		case "map":
			notNull(name + " collectionType", collectionType);
			return " = new java.util." + collectionType + "<>(" + collectionsSize + ")";
			
		default:
			return " = new " + Gen.packageExtraBean + "." + toFirstUpper(type) + "()";
		}
	}
	
	public static String getRawTypeByBaseType(String key) {
		switch (key.toLowerCase()) {
		case "int":
			return "Integer";
			
		case "short":
			return "Short";
			
		case "byte":
			return "Byte";
			
		case "long":
			return "Long";
			
		case "string":
			return "String";
			
		case "bool":
		case "boolean":
			return "Boolean";
			
		case "char":
			return "Character";
			
		default :
			return Gen.packageExtraBean + "." + toFirstUpper(key);
		}
	}
	
	public static void notNull(String name, String value) {
		if (value == null || value.trim().length() == 0) {
			throw new IllegalArgumentException(name + " is null");
		}
	}
	
	private static boolean isBaseType(String metaType) {
		switch (metaType.toLowerCase()) {
		case "int":
		case "long":
		case "char":
		case "bool":
		case "boolean":
		case "short":
		case "byte":
		//case "string":
			return true;
			
		default:
			return false;
		}
	}
	
	@SuppressWarnings("unused")
	private static boolean isCollection(String metaType) {
		if (metaType.equalsIgnoreCase("map") || metaType.equalsIgnoreCase("list")) {
			return true;
		}
		return false;
	}
	
	public static String toFirstUpper(String name) {
		char[] chars = name.toCharArray();
		
		if (chars[0] >= 'a' && chars[0] <= 'z') {
			chars[0] -= 32;
		}
		
		return new String(chars);
	}
	public static String toFirstLower(String name) {
		char[] chars = name.toCharArray();
		
		if (chars[0] >= 'A' && chars[0] <= 'Z') {
			chars[0] += 32;
		}
		
		return new String(chars);
	}
	
	public static void main(String[] args) {
		
		String a = "roleId";
		
		System.err.println(toFirstUpper(a));
	}
}
