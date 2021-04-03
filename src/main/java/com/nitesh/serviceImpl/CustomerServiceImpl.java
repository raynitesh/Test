package com.nitesh.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.PatternMatchUtils;

import com.nitesh.Dao.CustomerDao;
import com.nitesh.entity.Customer;
import com.nitesh.form.CustomerForm;
import com.nitesh.response.CustomerResponseDto;
import com.nitesh.service.CustomerService;

import java.util.regex.*;  


@Transactional
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	public CustomerResponseDto saveCustomer(CustomerForm customerForm) {

		Pattern pattern = Pattern.compile("@", Pattern.CASE_INSENSITIVE);
		Matcher match = pattern.matcher(customerForm.getEmail());
		
		System.out.println(match);
		
		Customer customer = new Customer();
		customer.setName(customerForm.getName());
		if(match.find()) {
			customer.setEmail(customerForm.getEmail());
		} else {
			throw new IllegalArgumentException("Email Regex not matched");
		}
		customer.setMobile(customerForm.getMobile());
		customer.setAddress(customerForm.getAddress());
		Customer customers = customerDao.saveDetail(customer);
		return customerResponse(customers);
	}
	
	public CustomerResponseDto customerResponse(Customer customer) {
		CustomerResponseDto customerResponseDto = new CustomerResponseDto();
		customerResponseDto.setId(customer.getId());
		customerResponseDto.setName(customer.getName());
		customerResponseDto.setEmail(customer.getEmail());
		customerResponseDto.setMobile(customer.getMobile());
		customerResponseDto.setAddress(customer.getAddress());
		return customerResponseDto;
	}

	public void updateCustomer(CustomerForm customerForm, int id) {
		Customer customer = customerDao.get(id);
		String validation ="@";
		if(customerForm.getAddress() != null) {
			customer.setAddress(customerForm.getAddress());
		}
		if(customerForm.getMobile() != null) {
			customer.setMobile(customerForm.getMobile());
		}
		if(customerForm.getEmail().contains(validation)) {
			customer.setEmail(customerForm.getEmail());
		} else {
			throw new IllegalArgumentException("Email Regex not matched");
		}
		customerDao.update(customer);
	}

	public List<CustomerResponseDto> custmersData() {
		List<CustomerResponseDto> customerResponseDtoList = new ArrayList<CustomerResponseDto>();
		List<Customer> customerList = customerDao.getCustomerList();
		for(Customer customer : customerList) {
			CustomerResponseDto customerResponseDto = customerResponse(customer);
			customerResponseDtoList.add(customerResponseDto);
		}
		return customerResponseDtoList;
	}

	public void deleteCustomers(int id) {
		Customer customer = customerDao.get(id);
		customerDao.delete(customer);
	}

}
