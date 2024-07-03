package com.kh.jdbc.day05.library.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day05.library.model.vo.Book;

public class BookStore implements BookStoreI{

	@Override
	public List<Book> selectAllBook(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM BOOK";
		pstmt = conn.prepareStatement(query);
	
		rset = pstmt.executeQuery();
		
		List<Book> bookList = new ArrayList<Book>();
		
		while(rset.next()) {
			Book book = this.rsetToBookList(rset);
			bookList.add(book);
		}
		pstmt.close();
		conn.close();
		rset.close();
		
		return bookList;
	}

	private Book rsetToBookList(ResultSet rset) throws SQLException {
		Book book = new Book();
		
		book.setBookNo(rset.getInt("BOOK_NO"));
		book.setBookName(rset.getString("BOOK_NAME"));
		book.setBookWriter(rset.getString("BOOK_WRITER"));
		book.setBookPrice(rset.getInt("BOOK_PRICE"));
		book.setPublisher(rset.getString("PUBLISHER"));
		book.setGenre(rset.getString("GENRE"));
		
		return book;
	}

	@Override
	public Book selectBookOne(int bookNo, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Book book = null;
		
		String query = "SELECT * FROM BOOK WHERE BOOK_NO = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, bookNo);
		
		rset = pstmt.executeQuery();
		
		if(rset.next()) {
			book = rsetToBookList(rset);
		}
		
		pstmt.close();
		conn.close();
		rset.close();
		
		return book;
	}

	@Override
	public int insertBook(Book book, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		String query = "INSERT INTO BOOK VALUES(SEQ_BOOK_NO.NEXTVAL, ?, ?, ?, ?, ?)";
		pstmt = conn.prepareStatement(query);
		
		pstmt.setString(1, book.getBookName());
		pstmt.setString(2, book.getBookWriter());
		pstmt.setInt(3, book.getBookPrice());
		pstmt.setString(4, book.getPublisher());
		pstmt.setString(5, book.getGenre());
		
		int result = pstmt.executeUpdate();
		
		return result;
	}

	@Override
	public int deleteBook(int bookNo, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0; 
		String query = "DELETE FROM BOOK WHERE BOOK_NO = ?";
		pstmt = conn.prepareStatement(query);
		
		pstmt.setInt(1, bookNo);
		result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		
		return result;
	}

}
