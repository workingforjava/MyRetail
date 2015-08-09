package com.myretail.ws.dao.impl;

import java.io.IOException;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.myretail.common.CommonConstants;
import com.myretail.connection.MongoConnectionManager;
import com.myretail.exceptions.ItemAvailException;
import com.myretail.ws.beans.Item;
import com.myretail.ws.dao.ItemDAO;

public class ItemDAOImpl implements ItemDAO {

	@Override
	public boolean isItemExists(Item item) throws IOException {

		MongoConnectionManager connectionManager = MongoConnectionManager
				.getInstance();
		DBCollection table = connectionManager
				.getTable(CommonConstants.ITEM_TABLE);
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.append(CommonConstants.ITEM_ID, item.getItemId());
		DBCursor cursor = table.find(searchQuery);
		try {
			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				Double price = (Double) obj.get(CommonConstants.ITEM_PRICE);
				System.out.println("Price :" + price);
				if (item.getPrice().doubleValue() == price.doubleValue()) { // May price get update
					System.out.println("Error:Same item and price to update");
					return true;
				} else { // May price get update
					BasicDBObject newDocument = new BasicDBObject();
					newDocument.append("$set", new BasicDBObject().append(
							CommonConstants.ITEM_PRICE, item.getPrice()));
					table.update(searchQuery, newDocument);
					System.out.println("Item got update");
					return true;
				}
			}
			return false;
		} finally {
			cursor.close();
		}
	}

	@Override
	public void insertItem(Item item) throws IOException {
		MongoConnectionManager connectionManager = MongoConnectionManager
				.getInstance();
		DBCollection table = connectionManager
				.getTable(CommonConstants.ITEM_TABLE);
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.append(CommonConstants.ITEM_ID, item.getItemId());
		DBCursor cursor = table.find(searchQuery);
		try {
			if (cursor.count()==0) {
				BasicDBObject document = new BasicDBObject();
				document.append(CommonConstants.ITEM_ID, item.getItemId());
				document.append(CommonConstants.ITEM_NAME, item.getDescription());
				document.append(CommonConstants.ITEM_TYPE, item.getType());
				document.append(CommonConstants.ITEM_PRICE, item.getPrice());
				table.createIndex(new BasicDBObject().append(CommonConstants.ITEM_ID, 1).append(
						"unique", true));
				table.insert(document);
				System.out.println("Item insert  : " + item.getItemId());
			}else{
				throw new ItemAvailException("Item Already exist with same price");
			}
		} finally {
			cursor.close();
		}

		
	}

	@Override
	public void save(Item item) throws IOException, ItemAvailException {

		if (isItemExists(item)) {
			throw new ItemAvailException("Item Already exist with same price");
		}
		insertItem(item);
	}

}
