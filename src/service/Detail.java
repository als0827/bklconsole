package service;

import java.util.List;
import java.util.Scanner;

import dao.CartDAO;
import dao.ViewsDAO;
import dto.BookDTO;
import dto.MembersDTO;

public class Detail {
	Scanner sc = new Scanner(System.in);
	OrderService orderService = new OrderService();
	MembersService membersService = new MembersService();
	ViewsDAO viewsDao = new ViewsDAO();
	
	public void showBookDetail(BookDTO book, List<MembersDTO> loginInfo) {
		String memId = null;
		if (loginInfo != null && loginInfo.size() > 0) {
			memId = loginInfo.get(0).getMemId();
		} else if (loginInfo == null) {
			memId = "guest";
		}

        // 책의 조회수 증가
        viewsDao.incrementViewCount(book.getBookId(), memId);
        
		System.out.println("========================================================");
        System.out.println("                        책 상세 정보                       ");
        System.out.println("========================================================");
        
        // 제목, 저자, 가격, 설명을 50자씩 출력
        System.out.println("     제목     :     " + book.getTitle());
        System.out.println("     저자     :     " + book.getAuthor());
        System.out.println(String.format("     가격     :     %,d원", book.getPrice()));
        System.out.println("     설명     :     " + book.getBookDesc());
        System.out.println("========================================================");
        System.out.println();
        printWithLineBreaks(book.getDetail());
        System.out.println("========================================================");
        System.out.println();
        System.out.println();
        System.out.println("			1. 카트에 담기");
        System.out.println("			2. 구매하기");
        System.out.println("			3. 돌아가기");
        System.out.println();
        System.out.print("   원하시는 작업을 선택하세요: ");
        
        int choice = sc.nextInt();
        sc.nextLine();
        
        if (loginInfo != null) {
        switch (choice) {
            case 1: // 장바구니에 담기
            	System.out.println("몇 권을 담으시겠습니까?");
            	int quantity = sc.nextInt();
                CartDAO cartDao = new CartDAO();
            	cartDao.addToCart(loginInfo.get(0).getMemId(), book.getBookId(), quantity);
                break;
            case 2: // 구매하기
                orderService.purchaseBook(loginInfo.get(0).getMemId(), book.getBookId(), book);
                break;
            case 3: // 돌아가기
                System.out.println("이전 화면으로 돌아갑니다.");
                break;
            default:
                System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
        }
        } else if (loginInfo == null) {
        	System.out.println("로그인이 필요합니다");
        	membersService.loginService();
        }
        
	}
	
	// 지정한 길이만큼 자름
	public void printWithLineBreaks(String text) {
	    if (text == null || text.trim().isEmpty()) { 
	        System.out.println("내용이 없습니다.");  
	        return;
	    }

	    int length = text.length();
	    int start = 0;

	    while (start < length) {
	        int end = Math.min(start + 40, length);  
	        System.out.println(text.substring(start, end));
	        start = end;
	    }
	}
}
