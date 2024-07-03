package com.kh.jdbc.day05.library.model.dao;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.jdbc.day05.library.model.vo.Library;

public class LibraryStore implements LibraryStoreI{

	@Override
	public ArrayList<Library> selectList(Connection conn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Library selectOne(String userId, Connection conn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Library selectOneByName(String bookName, Connection conn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertLibrary(Library library, Connection conn) {
		// TODO Auto-generated method stub
		return 0;
	}

}
