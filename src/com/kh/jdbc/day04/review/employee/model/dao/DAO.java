package com.kh.jdbc.day04.review.employee.model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day04.review.common.JDBCTemplate;
import com.kh.jdbc.day04.review.employee.model.vo.Employee;

public class DAO {
	JDBCTemplate jdbc;
	
	public DAO() {
		jdbc = new JDBCTemplate();
	}

	public List<Employee> selectAll(Connection conn) throws SQLException {
		
		Statement stmt = null;
		List<Employee> eList = null;
		
		stmt = conn.createStatement();
		String query = jdbc.getProp2().getProperty("selectList");
		ResultSet rset = stmt.executeQuery(query);

		eList = new ArrayList<>();
		while(rset.next()) {
			Employee emp = this.rsetToEmp(rset);
			eList.add(emp);
		}
		stmt.close();
		conn.close();
		
		return eList;
	}

	private Employee rsetToEmp(ResultSet rset) throws SQLException {
		Employee emp = new Employee();
		
		emp.setEmpId(rset.getString("EMP_ID"));
		emp.setEmpName(rset.getString("EMP_NAME"));
		emp.setEmpNo(rset.getString("EMP_NO"));
		emp.setEmail(rset.getString("EMAIL"));
		emp.setPhone(rset.getString("PHONE"));
		emp.setDeptCode(rset.getString("DEPT_CODE"));
		emp.setJobCode(rset.getString("JOB_CODE"));
		emp.setSalLevel(rset.getString("SAL_LEVEL"));
		emp.setSalary(rset.getInt("SALARY"));
		emp.setBonus(rset.getDouble("BONUS"));
		emp.setManagerId(rset.getString("MANAGER_ID"));
		emp.setHireDate(rset.getDate("HIRE_DATE"));
		emp.setEntDate(rset.getDate("ENT_DATE"));
		emp.setEntYn(rset.getString("ENT_YN"));
		
		return emp;
	}

	
	public int insertEmp(Connection conn, Employee emp) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = jdbc.getProp2().getProperty("insertEmployee");
		pstmt = conn.prepareStatement(query);
		
		pstmt.setString(1, emp.getEmpId());
		pstmt.setString(2, emp.getEmpName());
		pstmt.setString(3, emp.getEmpNo());
		pstmt.setString(4, emp.getJobCode());
		pstmt.setString(5, emp.getSalLevel());
		
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		
		return result;
	}

	public Employee selectOne(String id, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		Employee emp = null;
		
		String query = jdbc.getProp2().getProperty("selectOne");
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, id);
		
		ResultSet rset = pstmt.executeQuery();
		
		if(rset.next()) {
			emp = this.rsetToEmp(rset);
		}
		pstmt.close();
		conn.close();
		rset.close();
		
		return emp;
	}

	public int deleteEmp(String id, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		
		String query = jdbc.getProp2().getProperty("deleteEmployee");
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, id);
		
		int result = pstmt.executeUpdate();
		pstmt.close();
		conn.close();
		
		return result;
	}

	public int update(Employee emp, Connection conn) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = jdbc.getProp2().getProperty("updateEmployee");
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, emp.getEmail());
		pstmt.setString(2, emp.getPhone());
		pstmt.setString(3, emp.getDeptCode());
		pstmt.setInt(4, emp.getSalary());
		pstmt.setDouble(5, emp.getBonus());
		pstmt.setString(6, emp.getManagerId());
		pstmt.setString(7, emp.getEmpId());
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		
		return result;
		
	}

	

}
