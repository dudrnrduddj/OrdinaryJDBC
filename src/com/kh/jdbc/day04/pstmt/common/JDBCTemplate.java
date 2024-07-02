package com.kh.jdbc.day04.pstmt.common;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemplate {
//	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
//	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
//	private static final String USER_NAME = "ORDINARYJDBC";
//	private static final String USER_PASSWORD = "ORDINARYJDBC";
	
	// resource 활용하는 방법!! 
	private static Properties prop;
	
	// ---- 싱글톤 패턴 static 인스턴스 변수 ----
	private static Connection conn;
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
		// resource 활용하는 방법!!
		// Properties 객체 생성
		prop = new Properties();
		// FileReader로 해당 파일 읽어오기
		Reader reader = new FileReader("resources/dev.properties"); // 필요한 resource가 있는 경로 적어주면 됨.
		// load시키기
		prop.load(reader);

		// 각 key의 value를 찾아서 변수에 초기화시켜서 사용!
		String driverName = prop.getProperty("driverName");
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
		
		
		// ---- singleton 패턴 로직 ----
		if (conn == null || conn.isClosed()) { // conn이 null이면 연결 생성, dao에서 close시킨다면 ||conn.isclosed() 해주기
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, user, password);
		}
		return conn;

	}

}
// **!!
//conn.close() 를 시키면 conn 은 객체를 참조하곤 있지만 더이상 사용하지 않는 객체이다 ( 나중에 삭제될 메모리 )
//따라서 conn == null 의 의미는 제일 처음 conn이 생성되는 시기에만 해당하는 조건이므로 반복해서 connection을 활용하는 경우 조건문을 추가해주자(|| conn.isclosed() )
//conn.close()를 시킨 conn은 다시 열 수 없고, 사용하고 싶다면 새롭게 객체를 생성해 줘야 한다.


