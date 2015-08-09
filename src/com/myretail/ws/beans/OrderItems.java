package com.myretail.ws.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="product")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderItems {
	
	@XmlElement(name="itemId")
	private String itemId;
	
	@XmlElement(name="qty")
	private Integer qty;
	
	public OrderItems(){}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(" Item : "+itemId)
		.append(", Quantity: "+qty);
		
		return sb.toString();
	}
	
	
}
