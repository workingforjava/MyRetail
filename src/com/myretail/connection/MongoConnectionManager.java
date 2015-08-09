package com.myretail.connection;

import java.io.IOException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.myretail.common.CommonConstants;

public class MongoConnectionManager {

	private static MongoConnectionManager instance = new MongoConnectionManager();
	private static DB db;
	DBCollection table;

	private MongoConnectionManager() {
		try {
			MongoClient mongo = new MongoClient("localhost", 27017);
			db = mongo.getDB(CommonConstants.SCHEMA_NAME);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static MongoConnectionManager getInstance() {
		return instance;
	}

	public synchronized DBCollection getTable(String tableName) throws IOException {
		return db.getCollection(tableName);
	}

}
