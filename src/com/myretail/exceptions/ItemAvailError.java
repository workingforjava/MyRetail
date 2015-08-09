package com.myretail.exceptions;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Error")
public class ItemAvailError {
	
	private Integer errorCode;
	
	private String name;
	
	private String description;
	
	public ItemAvailError(){}
	
	public ItemAvailError(Integer errorCode,String name, String description){
		this.errorCode=errorCode;
		this.name=name;
		this.description=description;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
