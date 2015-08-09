package com.myretail.ws.service;

import java.io.IOException;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.myretail.common.CommonConstants;
import com.myretail.ws.beans.StoreItem;
import com.myretail.ws.dao.FacilityDAO;
import com.myretail.ws.dao.impl.FacilityDAOImpl;


@Path("supply")
public class SupplyService {
	
	@POST
	@Path("toStore")
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateStore(StoreItem supplyObject){
		FacilityDAO daoImpl = new FacilityDAOImpl();
		
		if(supplyObject.getProducts()!=null){
			if(supplyObject.getProducts().size()==1){
				try {
					daoImpl.addItemToStore(supplyObject.getProducts().get(0), supplyObject.getStoreId());
				} catch (IOException e) {
					e.printStackTrace();
					return Response.status(500).entity(CommonConstants.NOT_UPDATED_TO+supplyObject.getStoreId()).build();
				}
			}else{
				try {
					daoImpl.refreshInventory(supplyObject.getProducts(), supplyObject.getStoreId());
				} catch (IOException e) {
					e.printStackTrace();
					e.printStackTrace();
					return Response.status(500).entity(CommonConstants.NOT_UPDATED_TO+supplyObject.getStoreId()).build();
				}
			}
		}else{
			return Response.status(200).entity(CommonConstants.NO_PRODUCTS_IN_REQ).build();
		}
		
		return Response.status(200).entity(CommonConstants.PRODUCT_TO_STORE+supplyObject.getStoreId()).build();
	}
	
}
