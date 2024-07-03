package com.kh.jdbc.day05.library.controller;

import java.util.List;

import com.kh.jdbc.day05.library.model.service.CustomerService;
import com.kh.jdbc.day05.library.model.vo.Customer;

public class CustomerController implements CustomerControllerI{
	CustomerService customerService;
	
	public CustomerController() {
		customerService = new CustomerService();
	}

	@Override
	public List<Customer> selectAllCustomer() {
		List<Customer> cList = customerService.selectAllCustomer();
		return cList;
	}

	@Override
	public Customer selectNameSearch(String CName) {
		Customer customer = customerService.selectNameSearch(CName);
		return customer;
	}

	@Override
	public Customer selectIdSearch(String CId) {
		Customer customer = customerService.selectIdSearch(CId);
		return customer;
	}

	@Override
	public int insertCustomer(Customer customer) {
		int result = customerService.insertCustomer(customer);
		return result;
	}

	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCustomer(String CId) {
		// TODO Auto-generated method stub
		
	}

}
