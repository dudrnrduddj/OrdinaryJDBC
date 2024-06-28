package com.kh.jdbc.day02.stmt.member.controller;

import java.util.List;

import com.kh.jdbc.day02.stmt.member.model.dao.MemberDao;
import com.kh.jdbc.day02.stmt.member.model.vo.Member;

public class MemberController {

	MemberDao mDao;
	
	public MemberController() {
		mDao = new MemberDao();
	}
	
	public void insertMember(Member newMember) {
		mDao.insertMember(newMember);
	}

	public List<Member> selectAllMember() {
		List<Member> mList = mDao.selectAllList();
		return mList;
	}

	public Member selectOneMember(String memberID) {
		Member member = mDao.selectOneMember(memberID);
		return member;
	}

	public int deleteAllMember() {
		int result = mDao.deleteAllMember();
		return result;
	}

	public int deleteOneMember(String memberID) {
		int result = mDao.deleteOneMember(memberID);
		return result;
		
	}

	public int modifyMember(Member modifyInfo) {
		int result = mDao.modifyMember(modifyInfo);
		return result;
	}
	
	
}
