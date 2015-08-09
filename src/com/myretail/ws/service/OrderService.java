package com.myretail.ws.service;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.myretail.ws.beans.OrderRequest;
import com.myretail.ws.beans.OrderResponse;
import com.myretail.ws.dao.OrderDAO;
import com.myretail.ws.dao.impl.OrderDAOImpl;

@Path("order")
public class OrderService {
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response placeOrder(OrderRequest req){
		OrderResponse resp=null;
		OrderDAO daoImpl = new OrderDAOImpl();
		
		try {
			resp = daoImpl.createOrder(req);
		} catch (IOException e) {
			return Response.status(500).entity("Some problem while placing your order.").build();
		}
		
		if(resp!=null){
			return Response.status(200).entity(resp).build();
		}
		return Response.status(200).entity("Response creation faild.").build();
	}

}
