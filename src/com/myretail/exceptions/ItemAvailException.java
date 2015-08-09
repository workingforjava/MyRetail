package com.myretail.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class ItemAvailException extends WebApplicationException {

	private static final long serialVersionUID = 1L;

	public ItemAvailException(String message){
		super(Response.status(200).entity(new ItemAvailError(Response.Status.OK.getStatusCode(),"item_exist",message)).build());
	}

}
