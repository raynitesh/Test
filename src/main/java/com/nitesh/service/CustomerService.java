package com.nitesh.service;

import java.util.List;

import com.nitesh.form.CustomerForm;
import com.nitesh.response.CustomerResponseDto;

public interface CustomerService {
	
	public CustomerResponseDto saveCustomer(CustomerForm customer);
	
	public void updateCustomer(CustomerForm customerForm, int id);
	
	public List<CustomerResponseDto> custmersData();
	
	public void deleteCustomers(int id);

}
