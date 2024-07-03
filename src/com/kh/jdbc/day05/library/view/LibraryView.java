package com.kh.jdbc.day05.library.view;

import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.day05.library.controller.BookController;
import com.kh.jdbc.day05.library.controller.CustomerController;
import com.kh.jdbc.day05.library.model.vo.Book;
import com.kh.jdbc.day05.library.model.vo.Customer;

public class LibraryView {
	private Scanner scanner = new Scanner(System.in);
	BookController bookController;
	CustomerController customerController;

	public LibraryView() {
		bookController = new BookController();
		customerController = new CustomerController();
	}

	public void startProgram() {
		end:
		while (true) {
			int choice = this.printMainMenu();
			switch (choice) {
			case 0:
				this.printMessage("프로그램 종료");
				break end;
			case 1:
				this.bookMenu();
				break;
			case 2:
				this.customerMenu();
				break;
			case 3:
				this.libraryMenu();
				break;
			default:
				break;
			}
		}
	}

	public int printMainMenu() {
		System.out.println("=== 메인메뉴 ===");
		System.out.println("0. 프로그램 종료");
		System.out.println("1. 책 관리");
		System.out.println("2. 회원 관리");
		System.out.println("3. 대여 관리");
		System.out.print("메뉴 선택 : ");
		return scanner.nextInt();
	}

	public void bookMenu() {
		while (true) {
			int choice = this.printBookMenu();
			switch (choice) {
			case 1:
				// 전체 책 조회
				List<Book> bookList = bookController.selectAllBook();
				if (bookList != null) {
					printMessage("전체 책 조회");
					this.displayBookList(bookList);
				} else {
					printMessage("책이 존재하지 않습니다.");
				}
				break;
			case 2:
				// 책 코드로 조회
				int bookNo = this.inputBookNo();
				Book bookOne = bookController.selectBookOne(bookNo);

				if (bookOne != null) {
					printMessage("책 번호로 조회 성공");
					this.displayBook(bookOne);
				} else {
					printMessage("번호와 일치하는 책이 없습니다.");
				}
				break;
			case 3:
				// 책 정보 입력받기
				Book book = this.inputBookInfo();
				// 책 정보 전달
				int result = bookController.insertBook(book);
				if (result > 0) {
					this.printMessage(book.getBookName() + "이(가) 목록에 추가되었습니다.");
				}
				break;
			case 4:
				// 책 삭제하기
				bookNo = this.inputBookNo();
				bookOne = bookController.selectBookOne(bookNo);

				if (bookOne != null) {
					result = bookController.deleteBook(bookNo);
					if (result > 0) {
						printMessage(bookOne.getBookName() + "이(가) 목록에서 삭제되었습니다.");
					}
				} else {
					printMessage("조회된 책이 없습니다.");
				}
				break;
			case 5:
				return;
			}
		}

	}

	private void printMessage(String msg) {
		System.out.println("=============");
		System.out.println("[서비스 결과] : " + msg);
	}

	private Book inputBookInfo() {
		System.out.println("====== 책 정보 등록 ======");
		System.out.print("책 이름 : ");
		scanner.nextLine();
		String bookName = scanner.nextLine();
		System.out.print("책 저자 : ");
		String bookWriter = scanner.nextLine();
		System.out.print("책 가격 : ");
		int bookPrice = scanner.nextInt();
		System.out.print("출판사 : ");
		scanner.nextLine();
		String publisher = scanner.nextLine();
		System.out.print("장르 : ");
		String genre = scanner.next();

		Book book = new Book();
		book.setBookName(bookName);
		book.setBookWriter(bookWriter);
		book.setBookPrice(bookPrice);
		book.setPublisher(publisher);
		book.setGenre(genre);

		return book;
	}

	public void displayBookList(List<Book> list) {
		System.out.println("====== 전체 책 정보 조회 =======");
		for (Book li : list) {
			System.out.println("제목 : " + li.getBookName() + ",  글쓴이 : " + li.getBookWriter() + ",  가격 : "
					+ li.getBookPrice() + ",  출판사 : " + li.getPublisher() + ",  장르 : " + li.getGenre());

		}
	};

	public void displayMessage(String message) {
	};

	public void displayError(String message) {
	};

	public void customerMenu() {
		while (true) {
			int choice = this.printCustomerMenu();
			switch (choice) {
			case 1:
//				전체 회원 조회
				List<Customer> customerList = customerController.selectAllCustomer();
				if (customerList != null) {
					printMessage("전체 회원 조회");
					this.displayCustomerList(customerList);
				} else {
					printMessage("고객이 존재하지 않습니다.");
				}
				break;
			case 2:
//				회원 이름으로 조회
				String customerName = this.inputCName();
				Customer customerOne = customerController.selectNameSearch(customerName);
				if(customerOne != null) {
					this.printMessage("조회되었습니다.");
					this.displayCustomerOne(customerOne);
				}else {
					printMessage("고객이 존재하지 않습니다.");
				}
				
				break;
			case 3:
//				회원 아이디로 조회
				String customerId = this.inputCId();
				customerOne = customerController.selectIdSearch(customerId);
				if(customerOne != null) {
					this.printMessage("조회되었습니다.");
					this.displayCustomerOne(customerOne);
				}else {
					printMessage("고객이 존재하지 않습니다.");
				}
				break;
			case 4:
//				회원 가입
				Customer customer = this.inputCustomer();
				int result = customerController.insertCustomer(customer);
				break;
			case 5:
//				회원 정보수정
				break;
			case 6:
//				회원 탈퇴
				break;
			case 7:
//				메인 메뉴로 이동
				return;
			}
		}
	}

	public void libraryMenu() {
		while (true) {
			int choice = this.printLibraryMenu();
			switch (choice) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				return;
			}
		}
	}

	private int printBookMenu() {
		System.out.println("=== 책관리 서브메뉴 ===");
		System.out.println("1. 전체 책 조회");
		System.out.println("2. 책 코드로 조회");
		System.out.println("3. 책 추가하기");
		System.out.println("4. 책 삭제하기");
		System.out.println("5. 메인 메뉴로 이동");
		System.out.print("메뉴 선택 : ");
		int choice = scanner.nextInt();
		return choice;
	}

	private int printCustomerMenu() {
		System.out.println("=== 회원관리 서브메뉴 ===");
		System.out.println("1. 전체 회원 조회");
		System.out.println("2. 회원 이름으로 조회");
		System.out.println("3. 회원 아이디로 조회");
		System.out.println("4. 회원 가입");
		System.out.println("5. 회원 정보수정");
		System.out.println("6. 회원 탈퇴");
		System.out.println("7. 메인 메뉴로 이동");
		System.out.print("메뉴 선택 : ");
		int choice = scanner.nextInt();
		return choice;
	}

	private int printLibraryMenu() {
		System.out.println("=== 대여관리 서브메뉴 ===");
		System.out.println("1. 전체 대여 조회");
		System.out.println("2. 대여 아이디로 조회");
		System.out.println("3. 대여 책이름으로 조회");
		System.out.println("4. 대여정보 추가");
		System.out.println("5. 메인 메뉴로 이동");
		System.out.print("메뉴 선택 : ");
		int choice = scanner.nextInt();
		return choice;
	}

	public void displayCustomerList(List<Customer> list) {
		for(Customer li : list) {
			System.out.println(li.toString());
		}
	}

	public void displayCustomerOne(Customer customer) {
		System.out.println("======= 조회된 고객 정보 ========");
		System.out.println(customer.toString());
	}

	public void displayBook(Book book) {
		System.out.println("책 번호 : " + book.getBookNo() + ",  제목 : " + book.getBookName() + ",  글쓴이 : "
				+ book.getBookWriter() + ",  가격 : " + book.getBookPrice() + ",  출판사 : " + book.getPublisher()
				+ ",  장르 : " + book.getGenre());
	}

	public String inputCName() {
		System.out.println("==============");
		System.out.println("회원 이름 조회 : ");
		return scanner.next();
	}

	public String inputCId() {
		System.out.println("==============");
		System.out.println("회원 아이디 조회 : ");
		return scanner.next();
	}

	public Customer inputCustomer() {
		System.out.println("===== 회원 가입 =====");
		System.out.print("아이디 : ");
		String userId = scanner.next();
		System.out.print("이름 : ");
		String userName = scanner.next();
		System.out.print("나이 : ");
		int userAge = scanner.nextInt();
		System.out.print("주소 : ");
		scanner.nextLine();
		String userAddr = scanner.nextLine();
		System.out.print("성별 : ");
		String userGender = scanner.next();
		
		Customer customer = new Customer();
		customer.setUserId(userId);
		customer.setUserName(userName);
		customer.setUserAge(userAge);
		customer.setAddr(userAddr);
		customer.setGender(userGender);
		
		
		return customer;
	}

	public Customer modifyCustomer() {
		return null;
	}

	public int inputBookNo() {
		System.out.println("책 번호 입력 : ");
		return scanner.nextInt();
	}

	public Book inputBook() {
		return null;
	}

}