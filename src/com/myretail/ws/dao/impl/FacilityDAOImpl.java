package com.myretail.ws.dao.impl;

import java.io.IOException;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.myretail.common.CommonConstants;
import com.myretail.connection.MongoConnectionManager;
import com.myretail.ws.beans.Item;
import com.myretail.ws.beans.OrderItems;
import com.myretail.ws.dao.FacilityDAO;

public class FacilityDAOImpl implements FacilityDAO {

	static final FacilityDAOImpl daoImpl = new FacilityDAOImpl();

	@Override
	public synchronized boolean addItemToStore(OrderItems item, String storeId) throws IOException {
		MongoConnectionManager cManager = MongoConnectionManager.getInstance();
		DBCollection table = cManager.getTable(CommonConstants.INVENTORY_TABLE);
		boolean isItemAvailable = false;
		BasicDBObject searchQuery = new BasicDBObject();
		DBCursor cursor;
		int itemCount = 0;
		synchronized (daoImpl) {
			try {

				searchQuery.append(CommonConstants.STORE_ID, storeId);
				searchQuery.append(CommonConstants.ITEM_ID, item.getItemId());
				cursor = table.find(searchQuery);
				// Item Availability Check
				itemCount = cursor.count();
				if (itemCount == 0) {// New Product

					BasicDBObject newDocument = new BasicDBObject();
					newDocument.append(CommonConstants.STORE_ID, storeId);
					newDocument.append(CommonConstants.ITEM_ID, item.getItemId());
					newDocument.append(CommonConstants.QTY, item.getQty());
					table.createIndex(new BasicDBObject(CommonConstants.STORE_ID, 1).append(CommonConstants.ITEM_ID, 1).append("unique", true));
					table.insert(newDocument);
					isItemAvailable = true;
				} else {

					while (cursor.hasNext()) { // Updating Product
						BasicDBObject obj = (BasicDBObject) cursor.next();
						int availableQty = obj.getInt(CommonConstants.QTY);
						System.out.println("Avalibale QTY: " + availableQty);
						Integer newCount = availableQty + item.getQty();
						System.out.println("Updated QTY: " + newCount);
						BasicDBObject updateObj = new BasicDBObject();
						updateObj.append("$set", new BasicDBObject().append(CommonConstants.QTY, newCount));
						table.update(searchQuery, updateObj);
						isItemAvailable = true;
					}

				}

			} finally {

			}
		}
		return isItemAvailable;
	}

	@Override
	public void refreshInventory(List<OrderItems> products, String storeId) throws IOException {
		for (OrderItems p : products) {
			addItemToStore(p, storeId);
		}

	}

}
