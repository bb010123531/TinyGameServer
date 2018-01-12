package org.tiny.base;

import org.bson.Document;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class MongoBase implements DBBase{

	private MongoClient mongoClient;
	private MongoDatabase mongoDataBase;
	private MongoTemplate mongoTemplate;
	
	public MongoBase() {
		init();
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		mongoClient = new MongoClient("127.0.0.1", 27017);
		mongoDataBase = mongoClient.getDatabase("database");
	}
	
	
	private Document genDocument(byte[] key, byte[] value) {
		return new Document().append("_key", key).append("value", value);
	}
	
	/**
	 * mongo 的默认键是 _id
	 */
	@Override
	public boolean insert(String collectionName, byte[] key, byte[] value) {
		// TODO Auto-generated method stub
		getCollection(collectionName).insertOne(genDocument(key, value));
		return true;
	}

	@Override
	public boolean delete(String collectionName, byte[] key) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public boolean update(String collectionName, byte[] key, byte[] value) {
		// TODO Auto-generated method stub
//		getCollection(collectionName).updateOne(Filters.eq("_key", key), new Document("$set", new Document("value", value)));
		getCollection(collectionName).replaceOne(Filters.eq("_key", key), genDocument(key, value));
		return true;
	}

	@Override
	public byte[] find(String collectionName, byte[] key) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private MongoCollection<Document> getCollection(String collectionName) {
		return mongoDataBase.getCollection(collectionName);
	}

	@Override
	public void putInCache(String collectionName, ICacheTrigger cache) {
		// TODO Auto-generated method stub
		long have = getCollection(collectionName).count();
		int count = 0;
		MongoCursor<Document> itr = getCollection(collectionName).find().iterator();
		
		while (itr.hasNext()) {
			Document d = itr.next();
			Binary k = (Binary)d.get("_key");
			Binary v = (Binary)d.get("value");
			
			cache.trigger(k.getData(), v.getData());
			
			count++;
		}
		
		System.err.println("成功导入 " + collectionName + " : " + count + " have ：" + have);
	}
}
