package com.kh.jdbc.day05.library.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.kh.jdbc.day05.library.model.vo.Customer;


public interface CustomerStoreI {
	public List<Customer> selectAllCustomer(Connection conn) throws SQLException;
	public Customer selectNameSearch(String CName, Connection conn) throws SQLException;
	public Customer selectIdSearch(String CId, Connection conn) throws SQLException;
	public int insertCustomer(Customer customer, Connection conn) throws SQLException;
	public int updateCustomer(Customer customer, Connection conn);
	public int deleteCustomer(String CId, Connection conn);
}
