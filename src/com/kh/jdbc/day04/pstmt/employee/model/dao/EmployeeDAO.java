package com.kh.jdbc.day04.pstmt.employee.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.jdbc.day04.pstmt.common.JDBCTemplate;
import com.kh.jdbc.day04.pstmt.employee.model.vo.Employee;

public class EmployeeDAO {
	private String FILE_PATH = "resources/query.properties";
	private Properties prop;
	
	public EmployeeDAO() {
		try {
			prop = new Properties();
			Reader reader = new FileReader(FILE_PATH);
			prop.load(reader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public List<Employee> selectList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		List<Employee> eList = null;

		String query = prop.getProperty("selectList");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			eList = new ArrayList<Employee>();

			while (rset.next()) {
				Employee emp = this.rsetToEmployee(rset);
				eList.add(emp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
//				conn.close();
				stmt.close();
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return eList;
	}

	private Employee rsetToEmployee(ResultSet rset) throws SQLException {
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

	public int insertEmployee(Employee emp, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("insertEmployee");
		pstmt = conn.prepareStatement(query);

		pstmt.setString(1, emp.getEmpId());
		pstmt.setString(2, emp.getEmpName());
		pstmt.setString(3, emp.getEmpNo());
		pstmt.setString(4, emp.getJobCode());
		pstmt.setString(5, emp.getSalLevel());

		result = pstmt.executeUpdate();

		pstmt.close();
//		conn.close();

		return result;
	}

	public int deleteEmployee(Connection conn, String empId) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = prop.getProperty("deleteEmployee");
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, empId);

		result = pstmt.executeUpdate();

//		conn.close();
		pstmt.close();

		return result;
	}

	public Employee selectOne(String empId, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Employee emp = null;
		
		String query = prop.getProperty("selectOne");
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, empId);
		rset = pstmt.executeQuery();

		if(rset.next()) {
			emp = this.rsetToEmp(rset);
		}
		
		rset.close();
		pstmt.close();
//		conn.close();
		
		return emp;
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

	public int updateEmployee(Employee emp, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateEmployee");
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
//		conn.close();
		
		return result;
	}

}
