package tiny.gs.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConfigManager {
	private static final String path = "../tiny-config/properties";
	
	public static void start(){
		File dir = new File(path);
		File[] files = dir.listFiles();
		if (files != null) {
			try {
				for (File file : files) {
					if (file.getName().endsWith(".properties")) {
						readFile(file);
					}
				}
			} catch (FileNotFoundException fe) {
				
			} catch (IOException ie) {
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				
			}
		} else {
			
		}
	}
	
	private static void readFile (File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		
		String line = "";
		String[] arrs = null;
		while ((line = br.readLine()) != null) {
			arrs = line.split("//")[0].split("=");
			if (arrs.length == 2) {
				Conf.init(arrs[0], arrs[1]);
			}
		}
		
		br.close();
	}
}
