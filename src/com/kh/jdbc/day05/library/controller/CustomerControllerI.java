package com.kh.jdbc.day05.library.controller;

import java.util.List;

import com.kh.jdbc.day05.library.model.vo.Customer;

public interface CustomerControllerI {
	public List<Customer> selectAllCustomer();
	public Customer selectNameSearch(String CName);
	public Customer selectIdSearch(String CId);
	public int insertCustomer(Customer customer);
	public void updateCustomer(Customer customer);
	public void deleteCustomer(String CId);
}
