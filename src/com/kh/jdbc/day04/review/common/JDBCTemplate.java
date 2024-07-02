package com.kh.jdbc.day04.review.common;

import java.io.FileNotFoundException;
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
	private Properties prop;
	private Properties prop2;
	private String FILE_PATH = "resources/dev.properties";
	private String QUERY_PATH = "resources/query.properties";
	private String driverName;
	private String url;
	private String userName;
	private String userPassword;
	


	public Properties getProp() {
		return prop;
	}
	public Properties getProp2() {
		return prop2;
	}


	public JDBCTemplate() {
		try {
			prop = new Properties();
			prop2 = new Properties();
			Reader reader;
			Reader reader2 = new FileReader(QUERY_PATH);
			reader = new FileReader(FILE_PATH);
			prop.load(reader);
			prop2.load(reader2);
			
			
			
			driverName = prop.getProperty("driverName");
			url = prop.getProperty("url");
			userName = prop.getProperty("user");
			userPassword = prop.getProperty("password");	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	private static Connection conn;
	

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		JDBCTemplate j = new JDBCTemplate();
		
		
		if (conn == null || conn.isClosed()) {
			Class.forName(j.driverName);
			conn = DriverManager.getConnection(j.url, j.userName, j.userPassword);
		}
		
		return conn;
	}

}
