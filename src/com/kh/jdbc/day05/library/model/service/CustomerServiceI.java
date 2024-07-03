package com.kh.jdbc.day05.library.model.service;

import java.util.List;

import com.kh.jdbc.day05.library.model.vo.Customer;


public interface CustomerServiceI {
	public List<Customer> selectAllCustomer();
	public Customer selectNameSearch(String CName);
	public Customer selectIdSearch(String CId);
	public int insertCustomer(Customer customer);
	public int updateCustomer(Customer customer);
	public int deleteCustomer(String CId);
}
