package com.kh.jdbc.day01.basic;

import java.sql.*;


public class JDBCRun {
	public static void main(String[] args) {
		/*
		 * JDBC 코딩 절차
		 * 1. 드라이버 등록         (jar) <- 자료파일
		 * 2. DBMS 연결 생성        (KH/KH 확인)
		 * 3. Statement 객체 생성   (워크시트, 쿼리문 실행준비)
		 * 4. SQL 전송              (ctrl + Enter)
		 * 5. 결과받기              (ResultSet)
		 * 6. 자원해제
		 * 
		 */
		try {
			// 1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. DBMS 연결 생성
			Connection conn = 
						DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KH", "KH");
			// 3. Statement 객체 생성
			Statement stmt = conn.createStatement();
//			String query = "SELECT * FROM EMPLOYEE";
			String query = "SELECT * FROM DEPARTMENT";
			// 4. SQL전송, 5. 결과받기
			ResultSet rset = stmt.executeQuery(query);
			// 후처리
			while(rset.next()) {   // 다음값이 있는지 체크
//				System.out.println("직원명 : " + rset.getString("EMP_NAME")); // 컬럼명 오타없이 적기
				System.out.println("부서명 : " + rset.getString("DEPT_TITLE"));
			}
			// 6. 자원해제
			rset.close();
			stmt.close();
			conn.close();
// 이 1 ~ 6 번 과정들을 순서대로 코딩할 수 있어야 함.
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
