package tiny.gs;

import org.tiny.net.log.TinyLogger;

import tiny.gs.base.AppContext;
import tiny.gs.config.ConfigManager;
import tiny.gs.net.GameServer;

public class ServerMain {
	public static void start() {
		AppContext.start();
		ConfigManager.start();
		GameServer.start();
	}
	
	public static void main(String[] args) {
		try {
			start();
			TinyLogger.LOG.debug("Debug Message");
			TinyLogger.LOG.info("Info Message");
			TinyLogger.LOG.warn("Warn Message");
			TinyLogger.LOG.error("Error Message");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			TinyLogger.LOG.error("GameServer Start ERROR !");
			e.printStackTrace();
		}
		
		TinyLogger.LOG.info("GameServer Start SUCCESS!");
	}
}
