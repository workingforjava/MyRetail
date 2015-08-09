package com.myretail.ws.dao;

import java.io.IOException;
import java.util.List;

import com.myretail.ws.beans.OrderItems;

public interface FacilityDAO {
	public boolean addItemToStore(OrderItems item,String fslId) throws IOException;
	public void refreshInventory(List<OrderItems> items,String fslId) throws IOException;
	
}
