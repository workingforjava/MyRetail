package com.myretail.ws.service;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.myretail.ws.beans.Item;
import com.myretail.ws.dao.ItemDAO;
import com.myretail.ws.dao.impl.ItemDAOImpl;

@Path("items")
public class ItemService {
	
	@POST
	@Path("createItem")
	@Consumes(MediaType.APPLICATION_XML)
	public Response createLineItem(Item lineItem){
		ItemDAO daoImpl = new ItemDAOImpl();
		
		try {
			daoImpl.insertItem(lineItem);
		} catch (IOException e) {
			return Response.status(500).entity("Failed to create item").build();
		}
		return Response.status(200).entity("Line Item Successfully Created").build();
	}
	
	@POST
	@Path("updateItem")
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateProduct(Item llma){
		ItemDAO daoImpl = new ItemDAOImpl();
		
		try {
			daoImpl.save(llma);
		} catch (IOException e) {
			return Response.status(500).entity("Failed to update item").build();
		}
		
		return Response.status(200).entity("Item Successfully Updated").build();
	}
	

}
