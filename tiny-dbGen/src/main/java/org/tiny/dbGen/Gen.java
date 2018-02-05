package org.tiny.dbGen;

import java.util.List;

import org.tiny.bean.BeanMeta;
import org.tiny.bean.IMeta;
import org.tiny.bean.TableMeta;

/**
 * 
 * @author chunbo
 */
public class Gen 
{	
	public static String packageExtraBean = "auto.bean";
	public static String packageExtraTable = "auto.table";
    public static void main( String[] args ) {
    	String input = "db.xml";
    	String output = "./src/main/java/auto/";
    	
    	if (args.length == 3) {
    		input = args[0];
    		output = args[1] + "/auto/";
    		packageExtraBean = args[2] + ".auto.bean";
    		packageExtraTable = args[2] + ".auto.table";
    	}
    	
    	try {
//    		String dataPath = "./src/main/java/auto";
    		String basePath = output;
    		
			BeanMetaGenerator.init(input);
			
			List<IMeta> list = BeanMetaGenerator.genBeans();
			for (IMeta bean : list) {
				if (bean instanceof BeanMeta) {
					bean.toJavaFile(basePath + "bean/" + bean.getName() + ".java");
				} else if (bean instanceof TableMeta) {
					bean.toJavaFile(basePath + "table/");
				}
			}
			
			System.err.println("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
