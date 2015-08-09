package com.myretail.ws.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.myretail.common.CommonConstants;
import com.myretail.connection.MongoConnectionManager;
import com.myretail.exceptions.OutOfStockException;
import com.myretail.ws.beans.OrderRequest;
import com.myretail.ws.beans.OrderResponse;
import com.myretail.ws.beans.OrderItems;
import com.myretail.ws.dao.OrderDAO;

public class OrderDAOImpl implements OrderDAO {

	static final FacilityDAOImpl daoImpl = new FacilityDAOImpl();

	@Override
	public OrderResponse createOrder(OrderRequest req) throws IOException {
		MongoConnectionManager connectionManager = MongoConnectionManager
				.getInstance();
		DBCollection table = connectionManager
				.getTable(CommonConstants.INVENTORY_TABLE);
		OrderResponse resp = new OrderResponse();
		try {
			String storeId = req.getStoreId();
			if (req.getOrderItems() != null) {
				for (OrderItems item : req.getOrderItems()) {
					if (updateInventory(storeId, item.getItemId(), item.getQty(),
							table)) {
						resp.setResponseDesc("You have order" + item.getItemId());
					} else {
						resp.setResponseDesc("Order Failed for "
								+ item.getItemId());
					}
				}
			}
		} finally {
		}
		return resp;
	}

	private boolean updateInventory(String storeId, String skuId, int qty,
			DBCollection table) throws IOException {
		BasicDBObject searchQuery = new BasicDBObject();
		DBCursor cursor;
		int itemCount = 0;
		searchQuery.put(CommonConstants.STORE_ID, storeId);
		searchQuery.put(CommonConstants.ITEM_ID, skuId);

		synchronized (daoImpl) {
			cursor = table.find(searchQuery);
			// Item Availability Check
			try {
				itemCount = cursor.count();
				if (itemCount == 0) {
					return false;
				}

				while (cursor.hasNext()) {
					BasicDBObject obj = (BasicDBObject) cursor.next();
					int stockSize = obj.getInt(CommonConstants.QTY);
					if (stockSize < qty) {
						throw new OutOfStockException(
								"Ordered quantity is not available. Please order with less quantity.");
					}
					Integer newCount = stockSize - qty;

					BasicDBObject updateObj = new BasicDBObject();
					updateObj.append("$set", new BasicDBObject().append(CommonConstants.QTY, newCount));

					table.update(searchQuery, updateObj);
				}
			} finally {
				cursor.close();
			}

		}
		return true;
	}

}
