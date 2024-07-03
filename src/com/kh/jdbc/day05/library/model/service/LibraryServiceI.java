package com.kh.jdbc.day05.library.model.service;

import java.util.ArrayList;

import com.kh.jdbc.day05.library.model.vo.Library;


public interface LibraryServiceI {
	public ArrayList<Library> selectAll();
	public Library selectOne(String userId);
	public Library selectOneByName(String bookName);
	public int insertLibrary(Library library);
}
