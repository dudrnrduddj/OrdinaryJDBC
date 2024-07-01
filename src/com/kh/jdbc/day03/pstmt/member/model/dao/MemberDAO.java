package com.kh.jdbc.day03.pstmt.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.kh.jdbc.day03.pstmt.member.model.vo.Member;

public class MemberDAO {
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USER_NAME = "ORDINARYJDBC";
	private final String USER_PASSWORD = "ORDINARYJDBC";

	public int insertMember(Member member) {
		// JDBC
		Connection conn = null;
		PreparedStatement pstmt = null;
//		Statement stmt = null;
		int result = 0;

		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD);
//			stmt = conn.createStatement();
//			String query = "INSERT INTO MEMBER_TBL VALUES('"+member.getMemberID()+"','"
//			+member.getMemberPW()+"','"+member.getMemberName()+"','"+member.getMemberGender()
//			+"','"+member.getMemberAge()+"','"+member.getMemberEmail()+"','"+member.getMemberPhone()
//			+"','"+member.getMemberAddress()+"','"+member.getMemberHobby()+"',DEFAULT)";
//		
//			result = stmt.executeUpdate(query);

			String query = "INSERT INTO MEMBER_TBL VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, DEFAULT)";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, member.getMemberID());
			pstmt.setString(2, member.getMemberPW());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberGender());
			pstmt.setInt(5, member.getMemberAge());
			pstmt.setString(6, member.getMemberEmail());
			pstmt.setString(7, member.getMemberPhone());
			pstmt.setString(8, member.getMemberAddress());
			pstmt.setString(9, member.getMemberHobby());

			result = pstmt.executeUpdate();

			if (result > 0) {
				// 성공!
			} else {
				// 실패..
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
//				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public Member selectOne(Member member) {
		Connection conn = null;
//		Statement stmt = null;
//		--> Statement를 사용하게 되면 injection을 통해 정보 유출될 수 있음
//		ex) select * from table where member_id = '' or 'a'='a'--; <- injection코드
//		Statement를 사용하여 전달받은 쿼리에 대한 입력값 검증없이 그대로 실현하기 때문임.
//		PreparedStatement는 Statement와 다르게 query문을 매개변수로 넣으며 생성해준다.
		PreparedStatement pstmt = null;

		ResultSet rset = null;
		Member result = null;

		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD);
			// 쿼리문 먼저 컴파일 (플레이스 홀더)
			// 1. 쿼리 준비
			String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ? AND MEMBER_PW = ?";
			// 2. 객체 생성 및, 쿼리 컴파일
			pstmt = conn.prepareStatement(query);

			// 3. 파라미터 설정
			pstmt.setString(1, member.getMemberID());
			pstmt.setString(2, member.getMemberPW());
			// 4. 쿼리 실행
			rset = pstmt.executeQuery(); // PreparedStatement는 메소드 매개변수로 쿼리문 전달 x

//			쿼리문을 그대로 실행하는 Statement와는 다르게
//			쿼리문을 이용하여 컴파일을 미리하여 객체를 생성함.
//			쿼리문에는 값이 들어가는 자리를 위치홀더('?')로 표시해줘야함.

//			String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = '"+member.getMemberID()
//			+"' AND MEMBER_PW = '"+member.getMemberPW()+"'";
//			stmt = conn.createStatement();
//			rset = stmt.executeQuery(query); // statement는 여기서 query를 전달

			if (rset.next()) {
				result = this.rsetToMember(rset);

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
//			stmt.close();
		}

		return result;
	}

	private Member rsetToMember(ResultSet rset) {
		Member member = new Member();

		try {
			member.setMemberID(rset.getString("MEMBER_ID"));
			member.setMemberPW(rset.getString("MEMBER_PW"));
			member.setMemberName(rset.getString("MEMBER_NAME"));
			member.setMemberAge(rset.getInt("AGE"));
			member.setMemberGender(rset.getString("GENDER"));
			member.setMemberAddress(rset.getString("ADDRESS"));
			member.setMemberPhone(rset.getString("PHONE"));
			member.setMemberHobby(rset.getString("HOBBY"));
			member.setMemberEmail(rset.getString("EMAIL"));
			member.setMemberRegDate(rset.getString("REG_DATE"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return member;
	}

	public int deleteOneMember(String memberId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD);
			String query = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);

			result = pstmt.executeUpdate();

			if (result > 0) {
				// 성공!!
			} else {
				// 실패..
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public Member selectOne(String memberId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member result = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD);
			String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery(); // PreparedStatement는 메소드 매개변수로 쿼리문 전달 x

			if (rset.next()) {
				result = this.rsetToMember(rset);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public int updateMember(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD);
			String query = "UPDATE MEMBER_TBL SET MEMBER_PW = ?, AGE = ?, EMAIL = ?, PHONE = ?, ADDRESS = ?,HOBBY = ?"
					+" WHERE MEMBER_ID = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberPW());
			pstmt.setInt(2, member.getMemberAge());
			pstmt.setString(3, member.getMemberEmail());
			pstmt.setString(4, member.getMemberPhone());
			pstmt.setString(5, member.getMemberAddress());
			pstmt.setString(6, member.getMemberHobby());
			pstmt.setString(7, member.getMemberID());
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("수정되었습니다.");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

}
