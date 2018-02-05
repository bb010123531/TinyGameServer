package org.tiny.bean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Element;
import org.tiny.dbGen.Gen;

/**
 * 
 * @author chunbo
 */
public class BeanMeta implements IMeta{
	private String name;
	private List<VariableMeta> variables;
	
	@SuppressWarnings("unchecked")
	public BeanMeta(Element e) {
		this.name = e.attributeValue("name");
		this.variables = new ArrayList<>();
		
		Iterator<Element> it = e.elementIterator();
		
		while(it.hasNext()) {
			variables.add(new VariableMeta(it.next()));
		}
	}
	
	public String getName() {
		return name;
	}
	// TODO to java file
	@Override
	public void toJavaFile(String path) {
		FileOutputStream out = null;
		PrintStream ps = null;
		
		try {
			File file = new File(path);
			if(file.getParentFile() != null && !file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			
			List<String> vaStrings = new ArrayList<>(variables.size());
			
			out = new FileOutputStream(file);
			ps = new PrintStream(out, false, "UTF-8");
			ps.println("package " +  Gen.packageExtraBean + ";");
			ps.println("//auto-gen file, do not edit anyway");
			ps.println();
			ps.println("public class "+ name + " implements org.tiny.base.Bean {");
			ps.println();
			// TODO setter/getter
			for (VariableMeta var : variables) {
				vaStrings.add(var.getName());
				var.genSetterAndGetter(ps);
			}
			genClone(ps, vaStrings);
			// TODO toString()
			genToString(ps, vaStrings);
			ps.print("}");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 	@Override
	public Role clone() throws CloneNotSupportedException {
		Role clone = (Role)super.clone();
		
		
		return clone;
	}
	 */
	
	private void genClone(PrintStream ps, List<String> names) {
		ps.println("	@Override");
		ps.println("	public " + getName() + " clone() throws CloneNotSupportedException {");
		ps.println("		" + getName() + " clone = (" + getName() + ")super.clone();");
		ps.println();
		for (VariableMeta var : variables) {
			var.genDeepClone(ps);
		}
		ps.println("		return clone;");
		ps.println("	}");
	}
	
	/**
	 * @param ps
	 * @param names
	 */
	private void genToString(PrintStream ps, List<String> names) {
		ps.println();
		ps.println("	@Override");
		ps.println("	public String toString() {");
		ps.println("		StringBuilder sb = new StringBuilder(\"" + name + " [\");");
		
		for (int i = 0 ; i < names.size() ; i++) { 
			String varName = names.get(i);
			ps.println("		sb.append(\"" + varName + "=\" + " + varName + ");");
			if (i != names.size() - 1) {
				ps.println("		sb.append(\", \");");
			}
		}
		
		ps.println("		sb.append(\"]\");");
		ps.println();
		ps.println("		return sb.toString();");
		ps.println("	}");
	}
}
