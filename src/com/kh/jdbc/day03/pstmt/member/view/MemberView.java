package com.kh.jdbc.day03.pstmt.member.view;

import java.util.Scanner;

import com.kh.jdbc.day03.pstmt.member.controller.MemberController;
import com.kh.jdbc.day03.pstmt.member.model.vo.Member;

public class MemberView {
	
	MemberController mController;
	
	public MemberView() {
		mController = new MemberController();
	}

	public void startProgram() {
		Member memberLogin = null;
		end:
		while(true){
			int choice = this.mainMenu(memberLogin);
			switch (choice) {
			case 1:
				Member member = this.inputInfo();
				int result = mController.registerMember(member);
				if(result > 0) {
					printMessage("회원가입 성공!");
				}else {
					printMessage("회원가입 실패..");					
				}
				break;
			case 2:
				// 아이디, 비밀번호 입력 
				memberLogin = this.inputLoginInfo();
				// 입력한 아이디, 비밀번호 DB에 존재하는지 확인
				memberLogin = mController.checkLogin(memberLogin);
				if(memberLogin != null) {
					printMessage("로그인 성공!!");
					this.printOneMember(memberLogin);
				}else {
					printMessage("로그인 실패..");
				}				
				
				break;
			case 3:
				String memberId = inputMemberId();
				member = mController.checkMember(memberId);
				if(member != null) {
					// 수정하기
					member = this.modifyMember();
					member.setMemberID(memberId);
					result = mController.updateMember(member);
				}else {
					printMessage("존재하지 않는 정보입니다.");
				}
				break;
			case 4:
				memberId = this.inputMemberId();
				member = mController.checkMember(memberId);
				if(member != null) {
					result = mController.deleteOneMember(memberId);
					if(result > 0) {
						printMessage(memberId+"님이 탈퇴되었습니다.");
					}
				}else {
					printMessage("일치하는 회원이 없습니다.");
				}
				
				// 내가 만든 로직 - (choice = 2)로그인 정보를 바탕으로 바로 탈퇴가능
//				if(memberLogin != null) {
//					int myResult = mController.deleteOneMember(memberLogin.getMemberID());
//					
//					if(myResult > 0) {
//						printMessage(memberLogin.getMemberID()+"님이 탈퇴되었습니다.");
//						memberLogin = null;
//					}else {
//						printMessage("일치하는 회원이 없습니다.");
//					}
//				}else {
//					printMessage("로그아웃 상태입니다..");
//				}
				
				break;
			case 5:
				
				break;
			case 6:
				
				break;
			case 0:
				this.printMessage("프로그램 종료");
				
				break end;
			default:
				break ;
			}
		}
	}
		
	private Member modifyMember() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("====== 회원 정보 수정 ======");
		
		System.out.print("비밀번호 : ");
		String memberPW = scanner.next();
		System.out.print("이메일 : ");
		String email = scanner.next();
		System.out.print("전화번호 : ");
		String phone = scanner.next();
		System.out.print("주소 : ");
		scanner.nextLine();
		String address = scanner.nextLine();
		System.out.print("취미 : ");
		String hobby = scanner.next();
		
		Member member = new Member(memberPW, email, phone, address, hobby);
		
		
		return member;
	}

	private String inputMemberId() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("검색할 ID : ");
		String memberId = scanner.next();
		return memberId;
	}

	private void printOneMember(Member member) {
		System.out.println("====== 회원 정보 출력 ======");
		System.out.println("아이디 : " + member.getMemberID());
		System.out.println("비밀번호 : " + member.getMemberPW());
		System.out.println("이름 : " + member.getMemberName());
		System.out.println("성별 : " + member.getMemberGender());
		System.out.println("나이 : " + member.getMemberAge());
		System.out.println("이메일 : " + member.getMemberEmail());
		System.out.println("전화번호 : " + member.getMemberPhone());
		System.out.println("주소 : " + member.getMemberAddress());
		System.out.println("취미 : " + member.getMemberHobby());
		System.out.println("등록일자 : " + member.getMemberRegDate());

		
	}

	
	
	private Member inputLoginInfo() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("====== 로그인 정보 입력 ======");
		System.out.println("아이디 : ");
		String memberID = scanner.next();
		System.out.println("비밀번호 : ");
		String memberPW = scanner.next();
		
		Member member = new Member();
		member.setMemberID(memberID);
		member.setMemberPW(memberPW);
		
		return member;
	}
	
	
	private Member inputInfo() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("====== 회원 정보 입력 ======");
		System.out.print("아이디 : ");
		String memberID = scanner.next();
		System.out.print("비밀번호 : ");
		String memberPW = scanner.next();
		System.out.print("이름 : ");
		String memberName = scanner.next();
		System.out.print("성별 : ");
		String gender = scanner.next();
		System.out.print("나이 : ");
		int age = scanner.nextInt();
		System.out.print("이메일 : ");
		String email = scanner.next();
		System.out.print("전화번호 : ");
		String phone = scanner.next();
		System.out.print("주소 : ");
		scanner.nextLine();
		String address = scanner.nextLine();
		System.out.print("취미 : ");
		String hobby = scanner.next();
		
		Member member = new Member(memberID, memberPW, memberName, gender, age, email, phone, address, hobby);
		
		
		return member;
	}

	private void printMessage(String message) {
		System.out.println("[결과] : " + message);
	}

	private int mainMenu(Member memberLogin) {
		if(memberLogin != null) {
			System.out.println("로그인 ID : " + memberLogin.getMemberID());
		}else {
			System.out.println("-----로그아웃 상태-----");
		}
		Scanner scanner = new Scanner(System.in);
		System.out.println("====== 회원 관리 프로그램 ======");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("3. 회원 정보수정");
		System.out.println("4. 회원 탈퇴");
		System.out.println("0. 프로그램 종료");
		System.out.print("메뉴 선택 : ");
		int input = scanner.nextInt();
		
		return input;
	}

}
