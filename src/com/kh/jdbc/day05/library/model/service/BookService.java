package com.kh.jdbc.day05.library.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day05.library.common.JDBCTemplate;
import com.kh.jdbc.day05.library.model.dao.BookStore;
import com.kh.jdbc.day05.library.model.vo.Book;

public class BookService implements BookServiceI {

	BookStore bookStore;

	public BookService() {
		bookStore = new BookStore();

	}

	@Override
	public List<Book> selectAllBook() {
		Connection conn = null;
		List<Book> bookList = null;
		try {
			conn = JDBCTemplate.getConnection();
			bookList = bookStore.selectAllBook(conn);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return bookList;
	}

	@Override
	public Book selectBookOne(int bookNo) {
		Connection conn = null;
		Book book = null;

		try {
			conn = JDBCTemplate.getConnection();
			book = bookStore.selectBookOne(bookNo, conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return book;
	}

	@Override
	public int insertBook(Book book) {
		Connection conn = null;
		int result = 0;

		try {
			conn = JDBCTemplate.getConnection();
			result = bookStore.insertBook(book, conn);

			// 서비스에서 커밋,롤백 제어
			if (result > 0) {
//				conn.commit();
			} else {
//				conn.rollback();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	@Override
	public int deleteBook(int bookNo) {
		Connection conn = null;
		int result = 0;

		try {
			conn = JDBCTemplate.getConnection();
			result = bookStore.deleteBook(bookNo, conn);
			if (result > 0) {
//				conn.commit();
			} else {
//				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
}
