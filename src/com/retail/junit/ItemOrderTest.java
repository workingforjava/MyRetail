package com.retail.junit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

import com.myretail.exceptions.ItemAvailError;
import com.myretail.ws.beans.OrderItems;
import com.myretail.ws.beans.OrderRequest;
import com.myretail.ws.beans.OrderResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

public class ItemOrderTest extends JerseyTest {

	public static final String USER_URI = "http://localhost:8080/MyRetail/myretail/order";

	@Test
	public void OrderItem() {
		OrderRequest req = new OrderRequest();
		req.setStoreId("FSL1");
		
		List<OrderItems> itemList = new ArrayList<OrderItems>();
		OrderItems lineItem = new OrderItems();
		lineItem.setQty(1);
		lineItem.setItemId("Iphone4S");
		itemList.add(lineItem);
		req.setOrderItems(itemList);

		OrderResponse res = resource().accept(MediaType.APPLICATION_XML).post(
				OrderResponse.class, req);
		System.out.println(res.getResponseDesc());
		assertEquals("You have order"+lineItem.getItemId(), res.getResponseDesc());

	}

	@Test
	public void orderItemToError() {
		OrderRequest req = new OrderRequest();
		req.setStoreId("FSL1");
		List<OrderItems> itemList = new ArrayList<OrderItems>();
		OrderItems lineItem = new OrderItems();
		lineItem.setQty(25);
		lineItem.setItemId("Iphone4S");
		itemList.add(lineItem);
		req.setOrderItems(itemList);
		
		ItemAvailError res = resource().accept(MediaType.APPLICATION_XML).post(
				ItemAvailError.class, req);
		assertEquals("Ordered quantity is not available. Please order with less quantity.", res.getDescription());
	}

	@Override
	protected AppDescriptor configure() {
		return new WebAppDescriptor.Builder("com.myretail.ws.service")
				.contextPath("/").build();

	}

	public WebResource resource() {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource resource = client.resource(USER_URI);
		return resource;
	}

}
