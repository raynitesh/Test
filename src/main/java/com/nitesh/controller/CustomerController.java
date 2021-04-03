package com.nitesh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nitesh.form.CustomerForm;
import com.nitesh.response.CustomerResponseDto;
import com.nitesh.service.CustomerService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/customer/api")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping(value = "/save")
	public ResponseEntity<CustomerResponseDto> addCustomer(@RequestBody CustomerForm customerForm) {
		CustomerResponseDto result = customerService.saveCustomer(customerForm);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping(value = "/update")
	public ResponseEntity<String> updateCustomer(@RequestBody CustomerForm customerForm, @RequestParam(value = "id") int id) {
		customerService.updateCustomer(customerForm,id);
		return ResponseEntity.ok("Customer Update Successfully");
	}
	
	@GetMapping
	public ResponseEntity<List<CustomerResponseDto>> getCustomers() {
		List<CustomerResponseDto> result = customerService.custmersData();
		return ResponseEntity.ok(result);
	}
	
	@PostMapping(value = "/delete")
	public ResponseEntity<String> deleteCustomer(@RequestParam(value = "id") int id) {
		customerService.deleteCustomers(id);
		return ResponseEntity.ok().body("Customer deleted Successfully");
	}

}
