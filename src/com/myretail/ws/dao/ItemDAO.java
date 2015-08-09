package com.myretail.ws.dao;

import java.io.IOException;

import com.myretail.exceptions.ItemAvailException;
import com.myretail.ws.beans.Item;

public interface ItemDAO {

	public boolean isItemExists(Item product) throws IOException;

	public void insertItem(Item product) throws IOException, ItemAvailException;

	public void save(Item product) throws IOException, ItemAvailException;

}
