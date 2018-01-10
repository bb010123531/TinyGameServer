package org.tiny.db;

import java.util.Set;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

public class TestMongoDB {
	
	public static void main (String[] args) {
		
		String a = "\\\\";
		
		String b = "\\\\\\\\";
		
		Mongo mongo = null;
		try {
			mongo = new MongoClient("127.0.0.1", 27017);
			
			System.err.println(" 连接成功~ "); 
			DB db = mongo.getDB("tiny");
			Set<String> collectionNames = db.getCollectionNames();
			
			for (String name : collectionNames) {
				System.err.println("  :  " + name);
			}
			
		} finally {
			if (mongo != null) {
				mongo.close();
			}
		}
	}
}
