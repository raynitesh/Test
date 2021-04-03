package com.nitesh.Dao;

import java.util.List;

import com.nitesh.entity.Customer;

public interface CustomerDao {
	
	public Customer saveDetail(Customer customer);
	
	public void update(Customer customer);
	
	public Customer get(int id);
	
	public List<Customer> getCustomerList();
	
	public void delete(Customer customer);

}
