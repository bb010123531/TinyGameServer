package tiny.gs.config;

import java.util.HashMap;
import java.util.Map;

public class Conf {
	private static Map<String, String> configMap = new HashMap<>();
	
	public static void init(String propName, String propValue) {
		configMap.put(propName.trim(), propValue.trim());
	}
	
	public static String getServerIp() {
		return configMap.get("server_ip");
	}
	
	public static int getServerPort() {
		return Integer.parseInt(configMap.get("server_port"));
	}
}
