package tiny.gs.config;

import java.util.HashMap;
import java.util.Map;

import tiny.gs.util.AssertUtil;

public class Conf {
	private static Map<String, String> configMap = new HashMap<>();
	
	public static void init(String propName, String propValue) {
		configMap.put(propName.trim(), propValue.trim());
	}
	
	public static String getServerIp() {
		return AssertUtil.getValue(configMap, "server_ip");
	}
	
	public static int getServerPort() {
		return Integer.parseInt(AssertUtil.getValue(configMap, "server_port"));
//		return Integer.parseInt(configMap.get("server_port"));
	}
}
