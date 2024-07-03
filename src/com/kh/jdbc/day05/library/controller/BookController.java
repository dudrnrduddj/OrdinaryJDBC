package com.kh.jdbc.day05.library.controller;

import java.util.List;

import com.kh.jdbc.day05.library.model.service.BookService;
import com.kh.jdbc.day05.library.model.vo.Book;

public class BookController implements BookControllerI {
	
	BookService bookService;
	
	public BookController() {
		bookService = new BookService();
	}

	@Override
	public List<Book> selectAllBook() {
		List<Book> bookList = bookService.selectAllBook();
		return bookList;
	}

	@Override
	public Book selectBookOne(int bookNo) {
		Book book = bookService.selectBookOne(bookNo);
				
		return book;
	}

	@Override
	public int insertBook(Book book) {
		int result = bookService.insertBook(book);
		return result;
		
	}

	@Override
	public int deleteBook(int bookNo) {
		int result = bookService.deleteBook(bookNo);
		return result;
	}

}
