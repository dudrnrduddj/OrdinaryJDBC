package com.kh.jdbc.day04.pstmt.employee.view;

import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.day04.pstmt.employee.controller.EmployeeController;
import com.kh.jdbc.day04.pstmt.employee.model.vo.Employee;

import oracle.net.aso.l;

public class EmployeeView {
	EmployeeController empController = null;

	public EmployeeView() {
		this.empController = new EmployeeController();
	}

	public void startProgram() {
		end: while (true) {
			int choice = this.mainMenu();
			switch (choice) {
			case 1:
				List<Employee> eList = empController.printAllEmp();
				this.showAllEmp(eList);
				break;
			case 2:
				Employee emp = this.inputEmpInfo();
				int result = empController.insertEmployee(emp);
				if (result > 0) {
					printMessage("사원이 등록되었습니다.");
				} else {
					printMessage("사원 등록에 실패하였습니다.");
				}
				break;
			case 3:
				String empId = this.inputEmpId("수정");
				emp = empController.checkEmployee(empId);
				if(emp != null) {
					emp = this.modifyEmpInfo();
					emp.setEmpId(empId);
					result = empController.updateEmployee(emp);
					if (result > 0) {
						printMessage("[사번 : " + empId + "] 사원이 수정되었습니다.");
					}
				}else {
					printMessage("일치하는 사원이 존재하지 않습니다.");
				}
				
				break;
			case 4:
				empId = this.inputEmpId("삭제");
				emp = empController.checkEmployee(empId);
				if(emp != null) {
					result = empController.deleteEmployee(empId);					
					if (result > 0) {
						printMessage("[사번 : " + empId + "] 사원이 삭제되었습니다.");
					}
				}else {
					printMessage("일치하는 사원이 존재하지 않습니다.");
				}
				
				break;
			case 0:
				this.printMessage("프로그램이 종료되었습니다.");
				break end;
			default:
				break;
			}
		}
	}

	private Employee modifyEmpInfo() {
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

	private String inputEmpId(String msg) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("["+msg+"] 사번 입력 : ");
		return scanner.next();
	}

	private Employee inputEmpInfo() {
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

	private void showAllEmp(List<Employee> eList) {
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

	private void printMessage(String message) {
		System.out.println("[서비스 결과] : " + message);
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
