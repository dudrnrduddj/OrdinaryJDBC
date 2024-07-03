package com.kh.jdbc.day05.library.model.dao;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.jdbc.day05.library.model.vo.Library;


public interface LibraryStoreI {
	public ArrayList<Library> selectList(Connection conn);
	public Library selectOne(String userId, Connection conn);
	public Library selectOneByName(String bookName, Connection conn);
	public int insertLibrary(Library library, Connection conn);
}
