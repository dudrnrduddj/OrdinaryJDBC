package com.kh.jdbc.day04.pstmt.employee.model.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.kh.jdbc.day04.pstmt.common.JDBCTemplate;
import com.kh.jdbc.day04.pstmt.employee.model.dao.EmployeeDAO;
import com.kh.jdbc.day04.pstmt.employee.model.vo.Employee;

public class EmployeeService {
	private EmployeeDAO employeeDao;

	public EmployeeService() {
		this.employeeDao = new EmployeeDAO();
	}

	public List<Employee> selectList(){
		Connection conn = null;
		List<Employee> eList = null;
		try {
			conn = JDBCTemplate.getConnection();
			eList = employeeDao.selectList(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return eList;
	}

	public int insertEmployee(Employee emp) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = JDBCTemplate.getConnection();
			result = employeeDao.insertEmployee(emp, conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int deleteEmployee(String empId) {
		int result = 0;
		Connection conn = null;
		
		try {
			conn = JDBCTemplate.getConnection();
			result = employeeDao.deleteEmployee(conn, empId);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	public Employee selectOne(String empId) {
		Connection conn = null;
		Employee emp = null;
		try {
			conn = JDBCTemplate.getConnection();
			emp = employeeDao.selectOne(empId, conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return emp;
	}
	

	public int updateEmployee(Employee emp) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = JDBCTemplate.getConnection();
			result = employeeDao.updateEmployee(emp, conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
}
