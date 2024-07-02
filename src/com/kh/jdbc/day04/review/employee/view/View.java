package com.kh.jdbc.day04.review.employee.view;

import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.day04.review.employee.controller.Controller;
import com.kh.jdbc.day04.review.employee.model.vo.Employee;

public class View {
	Controller controller;
	
	public View() {
		this.controller = new Controller();
	}
	

	public void startProgram() {
		end:
		while(true) {
			int choice = this.mainMenu();
			switch(choice) {
			case 0 : 
				this.printMessage("프로그램을 종료합니다");
				break end;
			case 1 : 
				List<Employee> eList = controller.selectAll();
				this.printAll(eList);
				break;
			case 2 : 
				Employee emp = this.inputEmp();
				int result = controller.insertEmp(emp);
				if(result > 0) {
					printMessage("성공!");
				}else {
					printMessage("실패");
				}
				break;
			case 3 : 
				String id = this.inputId();
				emp = controller.checkid(id);
				if(emp != null) {
					emp = this.inputModifyInfo();
					emp.setEmpId(id);
					result = controller.modifyEmp(emp);
					if(result > 0) {
						printMessage("수정 성공!");
					}
				}else {
					printMessage("해당 id 없음");
				}
				
				break;
			case 4 : 
				id = this.inputId();
				emp = controller.checkid(id);
				if(emp != null) {
					result = controller.deleteEmp(id);
					if(result > 0) {
						printMessage("삭제 성공!");
					}
				}else {
					printMessage("해당 id 없음");
				}
				break;
			default :
				break;
			}
		}
	}

	private Employee inputModifyInfo() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("====== 사원 정보 수정 ======");
		System.out.print("이메일 :");
		String email = scanner.next();
		System.out.print("전화번호 :");
		String phone = scanner.next();
		System.out.print("부서코드 :");
		String deptCode = scanner.next();
		System.out.print("급여 :");
		int salary = scanner.nextInt();
		System.out.print("보너스율 :");
		double bonus = scanner.nextDouble();
		System.out.print("관리자사번 :");
		String managerId = scanner.next();
		
		Employee emp = new Employee();
		emp.setEmail(email);
		emp.setPhone(phone);
		emp.setDeptCode(deptCode);
		emp.setSalary(salary);
		emp.setBonus(bonus);
		emp.setManagerId(managerId);
		
		return emp;
	}


	private String inputId() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("검색 아이디 : ");
		
		return scanner.next();
	}


	private Employee inputEmp() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("====== 사원 정보 등록 ======");
		System.out.print("사원번호 : ");
		String empId = scanner.next();
		System.out.print("직원명 : ");
		String empName = scanner.next();
		System.out.print("주민등록번호 : ");
		String empNo = scanner.next();
		System.out.print("직급코드 : ");
		String jobCode = scanner.next();
		System.out.print("급여등급 : ");
		String salLevel = scanner.next();

		Employee employee = new Employee();
		employee.setEmpId(empId);
		employee.setEmpName(empName);
		employee.setEmpNo(empNo);
		employee.setJobCode(jobCode);
		employee.setSalLevel(salLevel);

		return employee;
	}


	private void printAll(List<Employee> eList) {
		for (Employee list : eList) {
			System.out.println("사원번호 : " + list.getEmpId());
			System.out.println("직원명 : " + list.getEmpName());
			System.out.println("주민등록번호 : " + list.getEmpNo());
			System.out.println("이메일 : " + list.getEmail());
			System.out.println("전화번호 : " + list.getPhone());
			System.out.println("부서코드 : " + list.getDeptCode());
			System.out.println("직급코드 : " + list.getJobCode());
			System.out.println("급여등급 : " + list.getSalLevel());
			System.out.println("급여 : " + list.getSalary());
			System.out.println("보너스율 : " + list.getBonus());
			System.out.println("관리자사번 : " + list.getManagerId());
			System.out.println("입사일 : " + list.getHireDate());
			System.out.println("퇴사일 : " + list.getEntDate());
			System.out.println("퇴직여부 : " + list.getEntYn());
			System.out.println("===============");
		}
		
	}


	private void printMessage(String string) {
		System.out.println("[서비스 결과] : "+string);
	}

	private int mainMenu() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("====== 사원 관리 프로그램 ======");
		System.out.println("1. 사원 전체 출력");
		System.out.println("2. 사원 등록");
		System.out.println("3. 사원 정보수정");
		System.out.println("4. 사원 삭제");
		System.out.println("0. 프로그램 종료");
		System.out.print("메뉴 선택 : ");
		int choice = scanner.nextInt();
		return choice;
	}

}
