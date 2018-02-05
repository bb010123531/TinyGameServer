package org.tiny.dbGen;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.tiny.bean.BeanMeta;
import org.tiny.bean.IMeta;
import org.tiny.bean.TableMeta;

/**
 * 
 * @author chunbo
 */
public class BeanMetaGenerator {
	
	private static final String Bean = "bean";
	private static final String Table = "table";
	
	private static TableMeta tableMeta;
	
	public static Document doc;
	
	public static void init(String xmlPath) throws Exception{
		InputStream in = new FileInputStream(new File(xmlPath));
		doc = new SAXReader().read(in);
		in.close();
	}
	
	@SuppressWarnings("unchecked")
	public static List<IMeta> genBeans() {
		List<IMeta> beans = new ArrayList<>();
		
		Iterator<Element> it = doc.getRootElement().elementIterator();
		while (it.hasNext()) {
			IMeta iMeta= genMeta(it.next());
			if (iMeta == null) {
				continue;
			}
			beans.add(iMeta);
		}
		
		return beans;
	}
	
	public static IMeta genMeta(Element e) {
		String tag = e.getQualifiedName();
		if (tag.equals(Bean)) {
			return new BeanMeta(e);
		} else if (tag.equals(Table)) {
			if (tableMeta == null) {
				tableMeta = new TableMeta(e);
				return tableMeta;
			} else {
				tableMeta.add(e);
			}
		}
		
		return null;
	}
}
