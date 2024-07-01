package com.kh.jdbc.day03.pstmt.member.controller;

import com.kh.jdbc.day03.pstmt.member.model.dao.MemberDAO;
import com.kh.jdbc.day03.pstmt.member.model.vo.Member;

public class MemberController {
	MemberDAO mDAO = new MemberDAO();

	public int registerMember(Member member) {
		int result = mDAO.insertMember(member);
		return result;
	}

	public Member checkLogin(Member memberLogin) {
		Member result = mDAO.selectOne(memberLogin);
		
		return result;
	}

	public int deleteOneMember(String memberId) {
		int result = mDAO.deleteOneMember(memberId);
		return result;
	}

	public Member checkMember(String memberId) {
		Member member = mDAO.selectOne(memberId);
		return member;
	}

	public int updateMember(Member member) {
		int result = mDAO.updateMember(member);
		return result;
	}

}
