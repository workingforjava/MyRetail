package com.myretail.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class OutOfStockException extends WebApplicationException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OutOfStockException(String message){
		super(Response.status(200).entity(new ItemAvailError(Response.Status.OK.getStatusCode(),"outof_stock",message)).build());
	}
}
