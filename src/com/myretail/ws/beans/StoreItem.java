package com.myretail.ws.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="storeItem")
@XmlAccessorType(XmlAccessType.FIELD)
public class StoreItem {
	
	@XmlElement(name="fslId")
	String fslID;
	
	@XmlElement(name="products")
	List<OrderItems> lineLevelMaterials;
	
	public StoreItem(){}

	public String getStoreId() {
		return fslID;
	}

	public void setStoreId(String storeId) {
		this.fslID = storeId;
	}

	public List<OrderItems> getProducts() {
		return lineLevelMaterials;
	}

	public void setProducts(List<OrderItems> llmas) {
		this.lineLevelMaterials = llmas;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("Store Id: "+fslID);
		
		if(lineLevelMaterials != null){
			for(OrderItems p:lineLevelMaterials){
				sb.append(p.toString());
			}
		}
		
		return sb.toString();
	}
	
}
