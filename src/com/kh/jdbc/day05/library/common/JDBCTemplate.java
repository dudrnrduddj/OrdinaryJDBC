package com.kh.jdbc.day05.library.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTemplate {
	private static final String DRVIER_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER_NAME = "ORDINARYJDBC";
	private static final String USER_PASSWORD = "ORDINARYJDBC";

	private static Connection conn;

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if (conn == null || conn.isClosed()) {
			Class.forName(DRVIER_NAME);
			conn = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD);
		}

		return conn;
	}

}
