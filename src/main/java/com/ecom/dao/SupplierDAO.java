package com.ecom.dao;

import java.util.List;

import com.ecom.model.Supplier;

public interface SupplierDAO {
       
	public boolean saveOrUpdate( Supplier supplier);

	public boolean  delete(Supplier supplier);

	public   Supplier get(int id);

	public List<Supplier> list();

	
	
}
