package com.myretail.ws.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="orderResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderResponse {
	
	@XmlElement(name="message")
	private String responseDesc;
	
	public OrderResponse(){}

	public String getResponseDesc() {
		return responseDesc;
	}
	public void setResponseDesc(String prodMessages) {
		this.responseDesc = prodMessages;
	}
	
	
}
