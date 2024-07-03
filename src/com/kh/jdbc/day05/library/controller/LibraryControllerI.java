package com.kh.jdbc.day05.library.controller;

import java.util.ArrayList;

import com.kh.jdbc.day05.library.model.vo.Library;


public interface LibraryControllerI {
	public ArrayList<Library> selectAll();
	public void selectOne(String userId);
	public void selectOneByName(String bookName);
	public void insertLibrary(Library library);
}
