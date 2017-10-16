package tiny.gs;

import tiny.gs.config.ConfigManager;
import tiny.gs.net.GameServer;

public class ServerMain {
	public static void start() {
		ConfigManager.start();
		GameServer.start();
	}
	
	public static void main(String[] args) {
		try {
			start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("GameServer Start ERROR ! ");
			e.printStackTrace();
		}
		
		System.err.println("GameServer Start SUCCESS! ");
	}
}
