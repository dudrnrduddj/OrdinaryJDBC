package com.kh.jdbc.day04.review.employee.controller;


import java.util.List;

import com.kh.jdbc.day04.review.employee.model.service.Service;
import com.kh.jdbc.day04.review.employee.model.vo.Employee;

public class Controller {
	Service service;
	
	public Controller(){
		this.service = new Service();
	}
	
	public List<Employee> selectAll() {
		List<Employee> eList = service.selectAll();
		
		return eList;
	}

	public int insertEmp(Employee emp) {
		int result = service.insertEmp(emp);
		return result;
	}

	public Employee checkid(String id) {
		Employee emp = service.selectOne(id);
		return emp;
	}

	public int deleteEmp(String id) {
		int result = service.deleteEmp(id);
		return result;
	}

	public int modifyEmp(String id) {
		
		return 0;
	}

	public int modifyEmp(Employee emp) {
		int result = service.update(emp);
		return result;
	}

}
