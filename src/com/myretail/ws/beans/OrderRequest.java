package com.myretail.ws.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderRequest {
	
	@XmlElement(name="storeId")
	private String storeId;
	
	@XmlElement(name="products")
	private List<OrderItems> orderItems;
	
	public OrderRequest(){}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public List<OrderItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItems> orderItemsList) {
		this.orderItems = orderItemsList;
	}
	
	
}
