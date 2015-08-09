package com.retail.junit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import com.myretail.ws.beans.OrderItems;
import com.myretail.ws.beans.StoreItem;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

public class DataSyncTest extends JerseyTest {

	public static final String USER_URI = "http://localhost:8080/MyRetail/myretail/supply/toStore";
	

	@Test
	public void singleItemStoreItemTest() {
		StoreItem storeItem = new StoreItem();
		storeItem.setStoreId("FSL50");

		List<OrderItems> lineItems = new ArrayList<OrderItems>();
		OrderItems item = new OrderItems();
		item.setQty(11);
		item.setItemId("Iphone4");
		lineItems.add(item);
		storeItem.setProducts(lineItems);
		ClientResponse res = resource().accept(MediaType.APPLICATION_XML).post(
				ClientResponse.class,storeItem);
		System.out.println(res.getStatus());
		assertEquals(200,res.getStatus());;
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
