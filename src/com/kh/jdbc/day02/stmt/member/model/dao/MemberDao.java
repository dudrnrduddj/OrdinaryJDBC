package com.kh.jdbc.day02.stmt.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.day02.stmt.member.model.vo.Member;

public class MemberDao {
	// JDBC 코딩절차
	// JDBC를 통해 DB의 데이터를 가져옴
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USER_NAME = "ORDINARYJDBC";
	private final String USER_PASSWORD = "ORDINARYJDBC";

	public void insertMember(Member newMember) {
		/*
		 * 1. 드라이버 등록 2. 연결 생성 3. Statement 생성 4. SQL문 전송 5. 결과 받기 6. 자원 해제
		 */

		// try문 밖에서 선언해주는 이유는 exception에 대한 catch가 일어났을때 자원해제 코드가 실행되어야
		// 하기 때문에 try문 안이 아닌 finally에서 자원해제를 해주려면 외부에서 변수를 선언해주어야 한다.
		Connection conn = null;
		Statement stmt = null;

		try {
			// 드라이버 등록
			Class.forName(DRIVER_NAME);

			// 연결 생성
			conn = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD);

			// Statement 생성
			stmt = conn.createStatement();

			// 쿼리문 작성
			String query = "INSERT INTO MEMBER_TBL VALUES('" + newMember.getMemberID() + "','" + newMember.getMemberPW()
					+ "', '" + newMember.getMemberName() + "', '" + newMember.getMemberGender() + "', "
					+ newMember.getMemberAge() + ", '" + newMember.getMemberEmail() + "', '"
					+ newMember.getMemberPhone() + "', '" + newMember.getMemberAddress() + "', '"
					+ newMember.getMemberHobby() + "', DEFAULT)";

			// SQL문 전송 및 반환값 저장
			int resultUpdate = stmt.executeUpdate(query);

			// 후처리
			if (resultUpdate > 0) {
				System.out.println("성공!!");
			} else {
				System.out.println("실패..");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원해제 - finally에서 호출해줌으로써 예외의 발생 유무와 상관없이 실행되도록 할 수 있음.
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public List<Member> selectAllList() {
		List<Member> mList = null;

		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD);
			stmt = conn.createStatement();
			String query = "SELECT * FROM MEMBER_TBL";
			rset = stmt.executeQuery(query);

			mList = new ArrayList<Member>();

			while (rset.next()) {
				Member member = this.rsetToMember(rset);

				mList.add(member);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return mList;

	}

	public Member selectOneMember(String memberID) {
		Member member = new Member();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD);
			stmt = conn.createStatement();
			String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = '" + memberID + "'";
			rset = stmt.executeQuery(query);

			if (rset.next()) {
				System.out.println("성공!!");
				member = this.rsetToMember(rset);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return member;
	}

	private Member rsetToMember(ResultSet rset) throws SQLException {
		Member member = new Member();

		member.setMemberID(rset.getString("MEMBER_ID"));
		member.setMemberPW(rset.getString("MEMBER_PW"));
		member.setMemberName(rset.getString("MEMBER_NAME"));
		member.setMemberGender(rset.getString("GENDER"));
		member.setMemberAge(rset.getInt("AGE"));
		member.setMemberEmail(rset.getString("EMAIL"));
		member.setMemberPhone(rset.getString("PHONE"));
		member.setMemberAddress(rset.getString("ADDRESS"));
		member.setMemberHobby(rset.getString("HOBBY"));
		member.setMemberRegDate(rset.getString("REG_DATE"));

		return member;
	}

	public int deleteAllMember() {
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL,USER_NAME, USER_PASSWORD);
			stmt = conn.createStatement();
			String query = "DELETE FROM MEMBER_TBL";
			result = stmt.executeUpdate(query);
			
			conn.setAutoCommit(false);
			
			if(result > 0) {
				System.out.println("성공!!");
//				conn.commit(); //자동 커밋 해제하고 사용하기 conn.setAutoCommit(false);
			}else {
				System.out.println("실패..");
//				conn.rollback(); // 자동 커밋 해제하고 사용하기 conn.setAutoCommit(false);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return result;
		
	}
	

	public int deleteOneMember(String memberID) {
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL,USER_NAME, USER_PASSWORD);
			stmt = conn.createStatement();
			String query = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID = '" + memberID + "'";
			result = stmt.executeUpdate(query);
			
			conn.setAutoCommit(false);
			
			if(result > 0) {
				System.out.println("성공!!");
//				conn.commit(); //자동 커밋 해제하고 사용하기 conn.setAutoCommit(false);
			}else {
				System.out.println("실패..");
//				conn.rollback(); // 자동 커밋 해제하고 사용하기 conn.setAutoCommit(false);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
		
	}

	public int modifyMember(Member modifyInfo) {
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD);
			
			stmt = conn.createStatement();
			String query = "UPDATE MEMBER_TBL SET MEMBER_PW = '"+modifyInfo.getMemberPW()
				+"', EMAIL = '"+modifyInfo.getMemberEmail()
				+"', PHONE = '"+modifyInfo.getMemberPhone()
				+"', ADDRESS = '"+modifyInfo.getMemberAddress()
				+"', HOBBY = '"+modifyInfo.getMemberHobby()
				+"' WHERE MEMBER_ID = '" + modifyInfo.getMemberID() + "'";
			
			result = stmt.executeUpdate(query);
			
			conn.setAutoCommit(false);
			
			if(result > 0) {
				System.out.println("성공!!");
				conn.commit();
			}else {
				System.out.println("실패..");
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return result;
	}

}
