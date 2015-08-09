package com.myretail.ws.dao;

import java.io.IOException;

import com.myretail.ws.beans.OrderRequest;
import com.myretail.ws.beans.OrderResponse;

public interface OrderDAO {
	
	public OrderResponse createOrder(OrderRequest req) throws IOException;
	
}
