package com.kh.jdbc.day05.library.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day05.library.common.JDBCTemplate;
import com.kh.jdbc.day05.library.model.dao.CustomerStore;
import com.kh.jdbc.day05.library.model.vo.Customer;

public class CustomerService implements CustomerServiceI{
	CustomerStore customerStore;
	
	public CustomerService() {
		customerStore = new CustomerStore();
	}

	@Override
	public List<Customer> selectAllCustomer() {
		Connection conn = null;
		List<Customer> cList = null;
		try {
			conn = JDBCTemplate.getConnection();
			cList = customerStore.selectAllCustomer(conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cList;
	}

	@Override
	public Customer selectNameSearch(String CName) {
		Connection conn = null;
		Customer customer = null;
		
		try {
			conn = JDBCTemplate.getConnection();
			customer = customerStore.selectNameSearch(CName, conn);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customer;
	}

	@Override
	public Customer selectIdSearch(String CId) {
		Connection conn = null;
		Customer customer = null;
		
		try {
			conn = JDBCTemplate.getConnection();
			customer = customerStore.selectIdSearch(CId, conn);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return customer;
	}

	@Override
	public int insertCustomer(Customer customer) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = JDBCTemplate.getConnection();
			result = customerStore.insertCustomer(customer, conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCustomer(String CId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
