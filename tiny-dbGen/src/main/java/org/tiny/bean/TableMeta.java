package org.tiny.bean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Element;
import org.tiny.dbGen.Gen;

import static org.tiny.bean.TransferUtil.*;
/**
 * 
 * @author chunbo
 */
public class TableMeta implements IMeta{
	private List<Table> tables = new LinkedList<>();
	private List<String> tableNames = new LinkedList<>();
	private List<String> tableBeanNames = new LinkedList<>();
	
	private Map<String, String> tablesName2BeanName = new HashMap<>();
	
	public TableMeta(Element e) {
		add(e);
	}
	
	public void add(Element e) {
		Table t = new Table(e);
		tables.add(t);
		
		tableNames.add(t.getTablenName());
		tableBeanNames.add(t.getBeanName());
		
		tablesName2BeanName.put(t.getTablenName(), t.getBeanName());
	}

	@Override
	public void toJavaFile(String rootPath) {
		String path = rootPath + getName() + ".java";
		FileOutputStream out = null;
		PrintStream ps = null;
		
		try {
			File file = new File(path);
			if(file.getParentFile() != null && !file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			
			out = new FileOutputStream(file);
			ps = new PrintStream(out, false, "UTF-8");
			ps.println("package " +  Gen.packageExtraTable + ";");
			ps.println("//auto-gen file, do not edit anyway");
			ps.println();
			ps.println("import java.util.Map;");
			ps.println("import java.util.concurrent.ConcurrentHashMap;");
			ps.println();
			ps.println("import org.tiny.base.DBBase;");
			ps.println("import org.tiny.base.MongoBase;");
			ps.println("import org.tiny.util.SerializationUtil;");
			ps.println();
			
			// import bean
			for (String beanName : tableBeanNames) {
				ps.println("import " + Gen.packageExtraBean + "." + beanName + ";");
			}
			
			ps.println("public class " + getName() + " extends org.tiny.base.Tables {");
			ps.println();
			ps.println("	private DBBase db = new MongoBase();");
			ps.println("	private " + getName() + " () {");
			ps.println("		super(\"mongo\");");
			ps.println("		init();");
			ps.println("	};");
			ps.println();
			ps.println("	private static class SingletonFactory {");
			ps.println("		private static final " + getName() + " tables = new " + getName() + "();");
			ps.println("	}");
			ps.println();
			ps.println("	public static " + getName() + " getInstance() {");
			ps.println("		return SingletonFactory.tables;");
			ps.println("	}");
			ps.println();
			ps.println("	public DBBase getDB() {");
			ps.println("		return db;");
			ps.println("	}");
			ps.println();
			ps.println("	public void init() {");
			// tables init
			for (Entry<String, String> e : tablesName2BeanName.entrySet()) {
				String tableName = e.getKey();
				String beanName = e.getValue();
				
//				ps.println("		cache" + beanName + "FromDB(\""+ tableName + "\");");
				ps.println("		add(" + tableName + ");");
			}
			ps.println("	}");
			ps.println();
			
			for (Table table : tables) {
				table.toJavaFile(ps);
				table.toSingleJavaFile(rootPath);
			}
			
			//ps.println();
			ps.println("}");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return "_Tables_";
	}
	
	private class Table {
		private String tablenName;
		
		// case sensitive
		private String beanName;
		public Table(Element e) {
			this.tablenName = e.attributeValue("name");
			this.beanName = e.attributeValue("bean");
		}
		
		public String getBeanName() {
			return beanName;
		}

		public String getTablenName() {
			return tablenName;
		}
		
		private void toJavaFile(PrintStream ps) {
//			ps.println("	Map<Long, " + beanName + "> " + toFirstLower(beanName) + "Map = new ConcurrentHashMap<>();");
//			ps.println();
//			ps.println("	private void cache" + beanName + "FromDB(String tableName) {");
//			ps.println("		db.putInCache(tableName, (k, v)-> {");
//			ps.println("			" + toFirstLower(beanName) + "Map.put(SerializationUtil.deserialize(k, Long.class), SerializationUtil.deserialize(v, " + beanName + ".class));");
//			ps.println("		});");
//			ps.println("	}");
//			ps.println();
//			ps.println("	public " + beanName + " select" + beanName + "(long " + toFirstLower(beanName) + "Id) {");
//			ps.println("		return " + toFirstLower(beanName) + "Map.get(" +  toFirstLower(beanName) + "Id);");
//			ps.println("	}");
//			ps.println();
//			ps.println("	public " + beanName + " get" + beanName + "(long " + toFirstLower(beanName) + "Id) {");
//			ps.println("		return " + toFirstLower(beanName) + "Map.get(" +  toFirstLower(beanName) + "Id);");
//			ps.println("	}");
//			ps.println();
			/**
			 * @Override
		public String getTableName() {
			// TODO Auto-generated method stub
			return null;
		} 
			 */
			ps.println("	org.tiny.base.Table<Long, " + beanName + "> " + toFirstLower(beanName) + "s = new org.tiny.base.Table<Long, " + beanName + ">() {");
			ps.println("		@Override");
			ps.println("		public String getTableName() {");
			ps.println("			return \"" +  getTablenName() + "\";");
			ps.println("		};");
			ps.println("	};");
			ps.println();
		}
		/**
		 * org.tiny.base.Table<Long, auto.bean.Role> roles = new org.tiny.base.Table<Long, auto.bean.Role>() {};
		 */
		/**
		 * 
		 * public static auto.bean.Bag select(long key) {
		return _Tables_.getInstance().bags.select(key);
	}

	public static auto.bean.Bag get(long key) {
		return _Tables_.getInstance().bags.find(key);
	}

	public static boolean remove(long key) {
		return _Tables_.getInstance().bags.remove(key);
	}

	public static boolean insert(long key, auto.bean.Bag value) {
		return _Tables_.getInstance().bags.insert(key, value);
	}

	public static boolean update(long key, auto.bean.Bag value) {
		return _Tables_.getInstance().bags.update(key, value);
	}
	
		 * @param rootPath
		 */
		private void toSingleJavaFile (String rootPath) {
			String path = rootPath + toFirstUpper(tablenName) + ".java";
			FileOutputStream out = null;
			PrintStream ps = null;
			
			try {
				File file = new File(path);
				if(file.getParentFile() != null && !file.getParentFile().exists()){
					file.getParentFile().mkdirs();
				}
				
				out = new FileOutputStream(file);
				ps = new PrintStream(out, false, "UTF-8");
				ps.println("package " +  Gen.packageExtraTable + ";");
				ps.println("//auto-gen file, do not edit anyway");
				
				ps.println();
//				ps.println("public class " + toFirstUpper(tablenName) + " extends org.tiny.base.Table<Long, " + Gen.packageExtraBean + "." + beanName + "> {");
				ps.println("public class " + toFirstUpper(tablenName) + " {");
				ps.println();
				ps.println("	public static " + Gen.packageExtraBean + "." +  beanName + " select(long key) {");
				ps.println("		return " + getName() + ".getInstance()." + toFirstLower(beanName) + "s.select(key);");
				ps.println("	}");
				ps.println();
				
				ps.println("	public static " + Gen.packageExtraBean + "." +  beanName + " get(long key) {");
				ps.println("		return " + getName() + ".getInstance()." + toFirstLower(beanName) + "s.get(key);");
				ps.println("	}");
				ps.println();
				
				ps.println("}");
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
}
