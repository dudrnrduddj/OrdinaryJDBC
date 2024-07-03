package com.kh.jdbc.day05.library.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day05.library.model.vo.Customer;

public class CustomerStore implements CustomerStoreI{

	@Override
	public List<Customer> selectAllCustomer(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		String query = "SELECT * FROM CUSTOMER";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		
		List<Customer> cList = new ArrayList<Customer>();
		
		while(rset.next()) {
			Customer customer = this.rsetToCustomer(rset);
			cList.add(customer);
		}
		
		pstmt.close();
		conn.close();
		rset.close();
		
		return cList;
	}

	private Customer rsetToCustomer(ResultSet rset) throws SQLException {
		Customer customer = new Customer();
		
		customer.setUserNo(rset.getInt("USER_NO"));
		customer.setUserId(rset.getString("USER_ID"));
		customer.setUserName(rset.getString("USER_NAME"));
		customer.setUserAge(rset.getInt("USER_AGE"));
		customer.setAddr(rset.getString("ADDR"));
		customer.setGender(rset.getString("GENDER"));
		customer.setEnrollDate(rset.getDate("ENROLL_DATE"));
		
		return customer;
	}

	@Override
	public Customer selectNameSearch(String CName, Connection conn) throws SQLException {
		Customer customer = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String query = "SELECT * FROM CUSTOMER WHERE USER_ID = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, CName);
		rset = pstmt.executeQuery();
		
		
		if(rset.next()) {
			customer = rsetToCustomer(rset);
		}
		
		rset.close();
		pstmt.close();
		conn.close();
		
		
		return customer;
	}

	@Override
	public Customer selectIdSearch(String CId, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Customer customer = null;
		String query = "SELECT * FROM CUSTOMER WHERE USER_ID = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, CId);
		rset = pstmt.executeQuery();
		
		if(rset.next()) {
			customer = this.rsetToCustomer(rset);
		}
		
		rset.close();
		conn.close();
		pstmt.close();
		
		return customer;
		
	}

	@Override
	public int insertCustomer(Customer customer, Connection conn) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "INSERT INTO CUSTOMER VALUES(SEQ_CUSTOMER_NO.NEXTVAL, ?,?,?,?,?,SYSDATE)";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, customer.getUserId());
		pstmt.setString(2, customer.getUserName());
		pstmt.setInt(3, customer.getUserAge());
		pstmt.setString(4, customer.getAddr());
		pstmt.setString(5, customer.getGender());
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return result;
	}

	@Override
	public int updateCustomer(Customer customer, Connection conn) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCustomer(String CId, Connection conn) {
		// TODO Auto-generated method stub
		return 0;
	}

}
