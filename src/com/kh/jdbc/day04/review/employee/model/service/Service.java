package com.kh.jdbc.day04.review.employee.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.kh.jdbc.day04.review.common.JDBCTemplate;
import com.kh.jdbc.day04.review.employee.model.dao.DAO;
import com.kh.jdbc.day04.review.employee.model.vo.Employee;

public class Service {
	DAO dao;
	
	public Service() {
		this.dao = new DAO();
	}
	

	public List<Employee> selectAll() {
		Connection conn = null;
		List<Employee> eList = null;
		
		try {
			conn = JDBCTemplate.getConnection();
			eList = dao.selectAll(conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return eList;
	}


	public int insertEmp(Employee emp) {
		int result = 0;
		Connection conn = null;
		
		try {
			conn = JDBCTemplate.getConnection();
			result = dao.insertEmp(conn, emp);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}


	public Employee selectOne(String id) {
		Connection conn = null;
		Employee emp = null;
		
		try {
			conn = JDBCTemplate.getConnection();
			emp = dao.selectOne(id, conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emp;
	}


	public int deleteEmp(String id) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = JDBCTemplate.getConnection();
			result = dao.deleteEmp(id, conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}


	public int update(Employee emp) {
		int result = 0;
		Connection conn = null;
		
		try {
			conn = JDBCTemplate.getConnection();
			result = dao.update(emp, conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
