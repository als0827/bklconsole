package service;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import dao.BookDAO;
import dao.CartDAO;
import dao.OrdersDAO;
import dto.BookDTO;
import dto.MembersDTO;

public class BookService {
	Scanner scanner = new Scanner(System.in);
	BookDAO bookDao = new BookDAO();
	Detail detail = new Detail();

	public void displayTodayBook(List<MembersDTO> loginInfo) {
		BookDAO bookDao = new BookDAO();
		OrdersDAO ordersDao = new OrdersDAO();
		CartDAO cartDao = new CartDAO();
		BookDTO todayBook = null;
		List<BookDTO> bookList = bookDao.todayBook();
		MembersService membersService = new MembersService();
		Scanner sc = new Scanner(System.in);

		// 상위 판매량이 높은 책 중에서 선택
		Random random = new Random();
		todayBook = bookList.get(random.nextInt(bookList.size())); // 상위 5권 중 하나 선택

		// 오늘의 책 출력
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("■                         오늘의 책                ■");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("            제     목: " + todayBook.getTitle());
		System.out.println("            저     자: " + todayBook.getAuthor());
		System.out.println("            출  판  사: " + todayBook.getPub());
		System.out.println("            가     격: " + todayBook.getPrice() + "원");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

		// 구매 또는 카트에 담기 선택
		System.out.println("                    1. 상세페이지로");
		System.out.println("                    2. 카트에 담기");
		System.out.println("                    3. 종료");
		System.out.print("원하시는 메뉴를 선택하세요: ");

		int selNum = Integer.parseInt(sc.nextLine());

		if (selNum == 1) {

			detail.showBookDetail(todayBook, loginInfo);
		} else if (selNum == 2 && loginInfo != null) {
			
			System.out.println("몇권을 담으시겠습니까?");
			int quantity = Integer.parseInt(sc.nextLine());
			cartDao.addToCart(loginInfo.get(0).getMemId(), todayBook.getBookId(), quantity);
		} else if (selNum == 3) {
			System.out.println("메뉴로 돌아갑니다.");
		} else if (selNum != 3 && loginInfo == null) {
        	System.out.println("로그인이 필요합니다");
        	membersService.loginService();
		}
	}
	
	

	public void domestic(List<BookDTO> bookList, List<MembersDTO> loginInfo) {
		Detail detail = new Detail(); // Detail 객체 생성
		int booksPerPage = 5; // 한 페이지에 출력할 책 수
		int totalBooks = bookList.size(); // 전체 책 수
		int totalPages = (int) Math.ceil((double) totalBooks / booksPerPage); // 전체 페이지 수 계산
		int currentPage = 1; // 시작 페이지

		while (true) {
			// 상단에 국내도서 카테고리 출력
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("========================================================");
			System.out.println();
			System.out.println("                        국내도서                        ");
			System.out.println();
			System.out.println("========================================================");

			// 책 목록 출력 (제목과 저자 간격 맞춤)
			System.out.println();
			int start = (currentPage - 1) * booksPerPage;
			int end = Math.min(start + booksPerPage, totalBooks); // 마지막 책의 인덱스 (리스트 크기를 초과하지 않도록)

			for (int i = start; i < end; i++) {
				BookDTO book = bookList.get(i);
				System.out.printf("    %-5d %-28s %-20s%n", (i - start + 1), book.getTitle(), book.getAuthor());
				System.out.println();
			}

			// 페이지 정보 및 이전 페이지 메뉴 출력
			System.out.println("========================================================");
			System.out.printf("                      Page %d/%d%n", currentPage, totalPages);

			// 페이지 네비게이션 출력 (바로 아래에)
			if (currentPage > 1) {
				System.out.print("       P: 이전 페이지           ");
			}
			if (currentPage < totalPages) {
				System.out.println("     N: 다음 페이지  ");
			}
			System.out.println("                       Q: 종료");
			System.out.println("========================================================");
			System.out.print("책 번호를 선택하거나 페이지 이동을 선택하세요: ");
			System.out.println();
			System.out.println();
			System.out.println();

			String input = scanner.nextLine().trim(); // nextLine()으로 입력 처리

			// 입력에 따른 동작
			if (input.equalsIgnoreCase("P") && currentPage > 1) {
				currentPage--; // 이전 페이지로 이동
			} else if (input.equalsIgnoreCase("N") && currentPage < totalPages) {
				currentPage++; // 다음 페이지로 이동
			} else if (input.equalsIgnoreCase("Q")) {
				System.out.println("페이지 보기 종료.");
				break; // 종료
			} else {
				// 책 번호로 상세 페이지 이동 처리
				try {
					// 숫자 입력 처리
					int bookNumber = Integer.parseInt(input); // nextLine()으로 입력받은 문자열을 숫자로 변환

					if (bookNumber > 0 && bookNumber <= (end - start)) {
						BookDTO selectedBook = bookList.get(start + bookNumber - 1); // 현재 페이지의 번호에 맞는 책 선택
						detail.showBookDetail(selectedBook, loginInfo); // 책 상세 페이지 출력

						System.out.println("이전 화면으로 돌아가려면 Enter를 누르세요.");
						scanner.nextLine(); // Enter 입력 대기 (이전 화면으로 돌아가기 위한 처리)
					} else {
						System.out.println("잘못된 책 번호입니다. 다시 시도해주세요.");
					}
				} catch (NumberFormatException e) {
					// 숫자가 아닌 값 입력 시 처리
					System.out.println("잘못된 입력입니다. 숫자를 입력하거나 페이지 이동 명령을 입력하세요.");
				}
			}
		}
	}

	public void overseas(List<BookDTO> bookList, List<MembersDTO> loginInfo) {
		Detail detail = new Detail(); // Detail 객체 생성
		int booksPerPage = 5; // 한 페이지에 출력할 책 수
		int totalBooks = bookList.size(); // 전체 책 수
		int totalPages = (int) Math.ceil((double) totalBooks / booksPerPage); // 전체 페이지 수 계산
		int currentPage = 1; // 시작 페이지

		while (true) {
			// 상단에 국내도서 카테고리 출력
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("========================================================");
			System.out.println();
			System.out.println("                        해외도서                        ");
			System.out.println();
			System.out.println("========================================================");

			// 책 목록 출력 (제목과 저자 간격 맞춤)
			System.out.println();
			int start = (currentPage - 1) * booksPerPage;
			int end = Math.min(start + booksPerPage, totalBooks); // 마지막 책의 인덱스 (리스트 크기를 초과하지 않도록)

			for (int i = start; i < end; i++) {
				BookDTO book = bookList.get(i);
				System.out.printf("    %-5d %-28s %-20s%n", (i - start + 1), book.getTitle(), book.getAuthor());
				System.out.println();
			}

			// 페이지 정보 및 이전 페이지 메뉴 출력
			System.out.println("========================================================");
			System.out.printf("                      Page %d/%d%n", currentPage, totalPages);

			// 페이지 네비게이션 출력 (바로 아래에)
			if (currentPage > 1) {
				System.out.print("       P: 이전 페이지           ");
			}
			if (currentPage < totalPages) {
				System.out.println("     N: 다음 페이지  ");
			}
			System.out.println("                       Q: 종료");
			System.out.println("========================================================");
			System.out.print("책 번호를 선택하거나 페이지 이동을 선택하세요: ");
			System.out.println();
			System.out.println();
			System.out.println();

			String input = scanner.nextLine().trim(); // nextLine()으로 입력 처리

			// 입력에 따른 동작
			if (input.equalsIgnoreCase("P") && currentPage > 1) {
				currentPage--; // 이전 페이지로 이동
			} else if (input.equalsIgnoreCase("N") && currentPage < totalPages) {
				currentPage++; // 다음 페이지로 이동
			} else if (input.equalsIgnoreCase("Q")) {
				System.out.println("페이지 보기 종료.");
				break; // 종료
			} else {
				// 책 번호로 상세 페이지 이동 처리
				try {
					// 숫자 입력 처리
					int bookNumber = Integer.parseInt(input); // nextLine()으로 입력받은 문자열을 숫자로 변환

					if (bookNumber > 0 && bookNumber <= (end - start)) {
						BookDTO selectedBook = bookList.get(start + bookNumber - 1); // 현재 페이지의 번호에 맞는 책 선택
						detail.showBookDetail(selectedBook, loginInfo); // 책 상세 페이지 출력

						System.out.println("이전 화면으로 돌아가려면 Enter를 누르세요.");
						scanner.nextLine(); // Enter 입력 대기 (이전 화면으로 돌아가기 위한 처리)
					} else {
						System.out.println("잘못된 책 번호입니다. 다시 시도해주세요.");
					}
				} catch (NumberFormatException e) {
					// 숫자가 아닌 값 입력 시 처리
					System.out.println("잘못된 입력입니다. 숫자를 입력하거나 페이지 이동 명령을 입력하세요.");
				}
			}
		}
	}

	public void eBook(List<BookDTO> bookList, List<MembersDTO> loginInfo) {
		Detail detail = new Detail(); // Detail 객체 생성
		int booksPerPage = 5; // 한 페이지에 출력할 책 수
		int totalBooks = bookList.size(); // 전체 책 수
		int totalPages = (int) Math.ceil((double) totalBooks / booksPerPage); // 전체 페이지 수 계산
		int currentPage = 1; // 시작 페이지

		while (true) {
			// 상단에 국내도서 카테고리 출력
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("========================================================");
			System.out.println();
			System.out.println("                        eBook                        ");
			System.out.println();
			System.out.println("========================================================");

			// 책 목록 출력 (제목과 저자 간격 맞춤)
			System.out.println();
			int start = (currentPage - 1) * booksPerPage;
			int end = Math.min(start + booksPerPage, totalBooks); // 마지막 책의 인덱스 (리스트 크기를 초과하지 않도록)

			for (int i = start; i < end; i++) {
				BookDTO book = bookList.get(i);
				System.out.printf("    %-5d %-28s %-20s%n", (i - start + 1), book.getTitle(), book.getAuthor());
				System.out.println();
			}

			// 페이지 정보 및 이전 페이지 메뉴 출력
			System.out.println("========================================================");
			System.out.printf("                      Page %d/%d%n", currentPage, totalPages);

			// 페이지 네비게이션 출력 (바로 아래에)
			if (currentPage > 1) {
				System.out.print("       P: 이전 페이지           ");
			}
			if (currentPage < totalPages) {
				System.out.println("     N: 다음 페이지  ");
			}
			System.out.println("                       Q: 종료");
			System.out.println("========================================================");
			System.out.print("책 번호를 선택하거나 페이지 이동을 선택하세요: ");
			System.out.println();
			System.out.println();
			System.out.println();

			String input = scanner.nextLine().trim(); // nextLine()으로 입력 처리

			// 입력에 따른 동작
			if (input.equalsIgnoreCase("P") && currentPage > 1) {
				currentPage--; // 이전 페이지로 이동
			} else if (input.equalsIgnoreCase("N") && currentPage < totalPages) {
				currentPage++; // 다음 페이지로 이동
			} else if (input.equalsIgnoreCase("Q")) {
				System.out.println("페이지 보기 종료.");
				break; // 종료
			} else {
				// 책 번호로 상세 페이지 이동 처리
				try {
					// 숫자 입력 처리
					int bookNumber = Integer.parseInt(input); // nextLine()으로 입력받은 문자열을 숫자로 변환

					if (bookNumber > 0 && bookNumber <= (end - start)) {
						BookDTO selectedBook = bookList.get(start + bookNumber - 1); // 현재 페이지의 번호에 맞는 책 선택
						detail.showBookDetail(selectedBook, loginInfo); // 책 상세 페이지 출력

						System.out.println("이전 화면으로 돌아가려면 Enter를 누르세요.");
						scanner.nextLine(); // Enter 입력 대기 (이전 화면으로 돌아가기 위한 처리)
					} else {
						System.out.println("잘못된 책 번호입니다. 다시 시도해주세요.");
					}
				} catch (NumberFormatException e) {
					// 숫자가 아닌 값 입력 시 처리
					System.out.println("잘못된 입력입니다. 숫자를 입력하거나 페이지 이동 명령을 입력하세요.");
				}
			}
		}

	}

	public void displayBooks(List<BookDTO> bookList, List<MembersDTO> loginInfo, String category) {
		Detail detail = new Detail(); // Detail 객체 생성
		int booksPerPage = 5; // 한 페이지에 출력할 책 수
		int totalBooks = bookList.size(); // 전체 책 수
		int totalPages = (int) Math.ceil((double) totalBooks / booksPerPage); // 전체 페이지 수 계산
		int currentPage = 1; // 시작 페이지

		while (true) {
			// 상단에 카테고리 출력
			System.out.println();
			System.out.println();
			System.out.println("========================================================");
			System.out.println();
			System.out.printf("                        %s                        %n", category);
			System.out.println();
			System.out.println("========================================================");

			// 책 목록 출력 (제목과 저자 간격 맞춤)
			System.out.println();
			int start = (currentPage - 1) * booksPerPage;
			int end = Math.min(start + booksPerPage, totalBooks); // 마지막 책의 인덱스 (리스트 크기를 초과하지 않도록)

			for (int i = start; i < end; i++) {
				BookDTO book = bookList.get(i);
				System.out.printf("    %-5d %-28s %-20s%n", (i - start + 1), book.getTitle(), book.getAuthor());
				System.out.println();
			}

			// 페이지 정보 및 이전 페이지 메뉴 출력
			System.out.println("========================================================");
			System.out.printf("                      Page %d/%d%n", currentPage, totalPages);

			// 페이지 네비게이션 출력 (바로 아래에)
			if (currentPage > 1) {
				System.out.print("       P: 이전 페이지           ");
			}
			if (currentPage < totalPages) {
				System.out.println("     N: 다음 페이지  ");
			}
			System.out.println("                       Q: 종료");
			System.out.println("========================================================");
			System.out.print("책 번호를 선택하거나 페이지 이동을 선택하세요: ");
			System.out.println();

			String input = scanner.nextLine().trim(); // nextLine()으로 입력 처리

			// 입력에 따른 동작
			if (input.equalsIgnoreCase("P") && currentPage > 1) {
				currentPage--; // 이전 페이지로 이동
			} else if (input.equalsIgnoreCase("N") && currentPage < totalPages) {
				currentPage++; // 다음 페이지로 이동
			} else if (input.equalsIgnoreCase("Q")) {
				System.out.println("페이지 보기 종료.");
				break; // 종료
			} else {
				// 책 번호로 상세 페이지 이동 처리
				try {
					int bookNumber = Integer.parseInt(input); // nextLine()으로 입력받은 문자열을 숫자로 변환

					if (bookNumber > 0 && bookNumber <= (end - start)) {
						BookDTO selectedBook = bookList.get(start + bookNumber - 1); // 현재 페이지의 번호에 맞는 책 선택
						detail.showBookDetail(selectedBook, loginInfo); // 책 상세 페이지 출력

						System.out.println("이전 화면으로 돌아가려면 Enter를 누르세요.");
						scanner.nextLine(); // Enter 입력 대기 (이전 화면으로 돌아가기 위한 처리)
					} else {
						System.out.println("잘못된 책 번호입니다. 다시 시도해주세요.");
					}
				} catch (NumberFormatException e) {
					System.out.println("잘못된 입력입니다. 숫자를 입력하거나 페이지 이동 명령을 입력하세요.");
				}
			}
		}
	}

	
}
