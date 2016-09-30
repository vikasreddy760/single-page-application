package com.ecom.dao;

import java.util.List;

import com.ecom.model.Product;

public interface ProductDAO {
      

	      
		public boolean saveOrUpdate(Product product);
		public boolean delete(Product product);
		
		public Product get (int id);
		 
		public List<Product>  list(); 
	}
	

