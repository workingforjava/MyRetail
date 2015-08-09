package com.myretail.ws.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="item")
@XmlAccessorType(XmlAccessType.FIELD)
public class Item {
	
	@XmlElement(name="itemID")
	String itemID;
	
	@XmlElement(name="description")
	String description;
	
	@XmlElement(name="qty")
	int quantity;
	
	@XmlElement(name="type")
	String type;
	
	@XmlElement(name="price")
	Double price;
	
	public Item(){}

	public String getItemId() {
		return itemID;
	}

	public void setItemId(String id) {
		this.itemID = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String name) {
		this.description = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String categories) {
		this.type = categories;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("Item ID: " + itemID).append(", Name: " + description)
				.append(", Price: " + price).append(", categories: " + type);
		
		return builder.toString();
	}

}
