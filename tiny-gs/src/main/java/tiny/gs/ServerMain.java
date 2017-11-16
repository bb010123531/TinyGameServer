package tiny.gs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tiny.gs.base.AppContext;
import tiny.gs.config.ConfigManager;
import tiny.gs.net.GameServer;

public class ServerMain {
	static Logger logger = LoggerFactory.getLogger(ServerMain.class);
	
	public static void start() {
		AppContext.start();
		ConfigManager.start();
		GameServer.start();
	}
	
	public static void main(String[] args) {
		try {
			start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
				logger.error("GameServer Start ERROR ! ");
			e.printStackTrace();
		}
		
		logger.info("GameServer Start SUCCESS! ");
	}
}
