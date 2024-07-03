package com.kh.jdbc.day05.library.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.kh.jdbc.day05.library.model.vo.Book;


public interface BookStoreI {

	public List<Book> selectAllBook(Connection conn) throws SQLException;
	public Book selectBookOne(int bookNo, Connection conn) throws SQLException;
	public int insertBook(Book book, Connection conn) throws SQLException;
	public int deleteBook(int bookNo, Connection conn) throws SQLException;
	
}
