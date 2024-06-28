package com.kh.jdbc.day02.stmt.member.view;

import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.day02.stmt.member.controller.MemberController;
import com.kh.jdbc.day02.stmt.member.model.vo.Member;

public class MemberView {

	MemberController mController; // view 클래스에서 계속 쓸거니 필드로 생성

	public MemberView() {
		this.mController = new MemberController(); // 생성자에서 초기화
	}

	public void startProgram() {
		end: while (true) {
			int value = this.printMainMenu();
			switch (value) {
			case 0:
				this.printMessage("프로그램을 종료합니다!!");
				break end;
			case 1:
				// 회원가입
				Member newMember = this.inputMember();
				// 생성된 새회원 controller에 전달
				mController.insertMember(newMember);
				break;
			case 2:
				// 전체 회원 조회
				// db에서 데이터 가져오기
				// view의 메소드를 이용해서 출력하기
				List<Member> mList = mController.selectAllMember();
				this.printAllMember(mList);
				break;
			case 3:
				// 회원 아이디 검색
				// db에서 데이터 가져오기
				// view의 메소드 이용해서 출력하기
				String memberID = this.inputMemberID();
				Member member = mController.selectOneMember(memberID);
				if (member.getMemberID() != null) {
					this.printOneMember(member);
				} else {
					this.printMessage("일치하는 회원이 없습니다..");
				}
				break;
			case 4:
				// 회원 정보 수정
				// 수정할 회원 아이디 입력
				memberID = this.inputMemberID();
				// 회원 아이디와 일치하는 member 존재 유무 확인
				member = mController.selectOneMember(memberID);
				// 멤버가 있는 경우에 수정로직 실행되도록 설계
				if(member != null) {
					Member modifyInfo = this.inputModifyInfo();
					modifyInfo.setMemberID(memberID);
					int result = mController.modifyMember(modifyInfo);
					if (result > 0) {
						this.printMessage("수정 성공!!");
					}else {
						this.printMessage("수정 실패..");
					}
				}else {
					this.printMessage("일치하는 회원이 없습니다.");
				}
				
				
				break;
			case 5:
				// 회원 정보 삭제
				memberID = this.inputMemberID();

				// 존재하는 정보만 삭제로직을 타야함.
				member = mController.selectOneMember(memberID);

				if (member != null) {
					int result = mController.deleteOneMember(memberID);
					if (result > 0) {
						this.printMessage("삭제 성공!!");
					}else {
						this.printMessage("삭제 실패..");
					}
				} else {
					this.printMessage("일치하는 회원이 없습니다.");
				}
				break;
			case 6:
				// 회원 정보 전체 삭제

				System.out.println("정말 삭제하시겠습니까? (예, 아니오) : ");

				boolean deleteChoice = inputDeleteMsg();
				if (deleteChoice) {
					int deleteResult = mController.deleteAllMember();
					if (deleteResult > 0) {
						System.out.println("삭제되었습니다!!");
					} else {
						System.out.println("일치하는 회원이 없습니다..");
					}
					break;
				} else {
					break;
				}

			}
		}
	}

	private Member inputModifyInfo() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("====== 회원 정보 수정 ======");
		System.out.print("비밀번호 : ");
		String memberPW = scanner.next();
		System.out.print("이메일 : ");
		String memberEmail = scanner.next();
		System.out.print("전화번호 : ");
		String memberPhone = scanner.next();
		System.out.print("주소 : ");
		scanner.nextLine();
		String memberAddress = scanner.nextLine();
		System.out.print("취미 : ");
		String memberHobby = scanner.next();

		Member modifyInfo = new Member();
		modifyInfo.setMemberPW(memberPW);
		modifyInfo.setMemberEmail(memberEmail);
		modifyInfo.setMemberPhone(memberPhone);
		modifyInfo.setMemberAddress(memberAddress);
		modifyInfo.setMemberHobby(memberHobby);
		
		return modifyInfo;
	}

	private boolean inputDeleteMsg() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			String deleteChoice = scanner.next();
			if ("예".equals(deleteChoice)) {
				return true;
			} else if ("아니오".equals(deleteChoice)) {
				return false;
			} else {
				System.out.println("다시 입력해주세요! (예, 아니오)");
			}
		}

	}

	private void printMessage(String msg) {
		System.out.println(msg);
	}

	private String inputMemberID() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("검색할 ID : ");
		String memberID = scanner.next();
		return memberID;
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

	private void printAllMember(List<Member> mList) {
		System.out.println("====== 전체 회원 이름 출력 ======");
		for (Member member : mList) {
			System.out.printf("이름 : %s\n", member.getMemberName());
		}

	}

	private Member inputMember() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("====== 회원 정보 등록 ======");
		System.out.print("아이디 : ");
		String memberID = scanner.next();
		System.out.print("비밀번호 : ");
		String memberPW = scanner.next();
		System.out.print("이름 : ");
		String memberName = scanner.next();
		System.out.print("성별 : ");
		String memberGender = scanner.next();
		System.out.print("나이 : ");
		int memberAge = scanner.nextInt();
		System.out.print("이메일 : ");
		String memberEmail = scanner.next();
		System.out.print("전화번호 : ");
		String memberPhone = scanner.next();
		System.out.print("주소 : ");
		scanner.nextLine();
		String memberAddress = scanner.nextLine();
		System.out.print("취미 : ");
		String memberHobby = scanner.next();

		Member member = new Member();
		member.setMemberID(memberID);
		member.setMemberPW(memberPW);
		member.setMemberName(memberName);
		member.setMemberGender(memberGender);
		member.setMemberAge(memberAge);
		member.setMemberEmail(memberEmail);
		member.setMemberPhone(memberPhone);
		member.setMemberAddress(memberAddress);
		member.setMemberHobby(memberHobby);

		return member;

	}

	public int printMainMenu() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("====== 회원 관리 프로그램 ======");
		System.out.println("1. 회원가입");
		System.out.println("2. 전체 회원 조회");
		System.out.println("3. 회원 아이디 검색");
		System.out.println("4. 회원 정보 수정");
		System.out.println("5. 회원 정보 삭제");
		System.out.println("6. 회원 정보 전체 삭제");
		System.out.println("0. 종료");
		System.out.println("메뉴 선택 : ");
		int choice = scanner.nextInt();
		return choice;
	}

}
