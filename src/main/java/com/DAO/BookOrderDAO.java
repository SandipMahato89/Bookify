package com.DAO;

import java.util.List;

import com.entity.Book_Order;

public interface BookOrderDAO {
	
	//public int getOrderNo();
	
	public boolean saveOrder(List<Book_Order> b);
	
	public List<Book_Order> getBook(String email); //for user
	
	public List<Book_Order> getAllOrder(); //for admin
}
