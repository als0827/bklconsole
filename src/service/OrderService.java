package service;

import java.util.List;
import java.util.Scanner;

import dao.MembersDAO;
import dao.OrdersDAO;
import dto.BookDTO;
import dto.MembersDTO;
import dto.OrderItemDTO;
import dto.OrdersDTO;
public class OrderService {
	
	public void purchaseBook(String memId, int bookId, BookDTO book) {
	    OrdersDAO ordersDao = new OrdersDAO();
	    MembersDAO membersDao = new MembersDAO();
	    
	    Scanner sc = new Scanner(System.in);
	    
	    // 회원 정보를 MembersDAO를 통해 가져옴
	    MembersDTO member = membersDao.getMemberInfo(memId);
	    
	    if (member == null) {
	        System.out.println("회원 정보를 찾을 수 없습니다.");
	        return;
	    }

	    // 주문 요청 사항을 입력받음
	    System.out.print("요청 사항을 입력해 주세요 (없으면 Enter): ");
	    String orderComment = sc.nextLine();
	    
	    // 수량 입력받기
	    System.out.print("구매할 수량을 입력해 주세요: ");
	    int quantity = sc.nextInt();
	    sc.nextLine();  // 개행 문자 제거
	    
	    // 구매 진행
	    System.out.println("1. 회원 기본 정보 사용");
	    System.out.println("2. 새로운 정보 입력");
	    System.out.print("배송 정보를 선택해 주세요: ");
	    String choice = sc.nextLine();
	    
	    
	    if (choice.equals("1")) {
	        // 회원 기본 정보를 사용하여 구매
	        OrdersDTO order = new OrdersDTO(0, memId, book.getPrice(), quantity, orderComment, null, member.getMemName(), member.getAddress(), member.getPhone()); 
	        ordersDao.placeOrderWithItems(order, book);
	    } else if (choice.equals("2")) {
	        // 새로운 정보를 입력받아 구매
	        System.out.print("수령인 이름: ");
	        String recipient = sc.nextLine();
	        System.out.print("배송지 주소: ");
	        String address = sc.nextLine();
	        System.out.print("연락처: ");
	        String phone = sc.nextLine();

	        OrdersDTO order = new OrdersDTO(0, memId, book.getPrice(), quantity, orderComment, null, recipient, address, phone);
	        ordersDao.placeOrderWithItems(order, book);
	    } else {
	        System.out.println("잘못된 선택입니다.");
	    }
	}
	
	public void showOrderHistory(String memId) {
        Scanner sc = new Scanner(System.in);
        OrdersDAO ordersDao = new OrdersDAO();
        List<OrdersDTO> orderList = ordersDao.getOrderHistory(memId);

        if (orderList.isEmpty()) {
            System.out.println("주문 내역이 없습니다.");
            return;
        }

        System.out.println();
        System.out.println("=================================================");
        System.out.println();
        System.out.println("                   주문 내역                    ");
        System.out.println();
        System.out.println("=================================================");
        for (OrdersDTO order : orderList) {
        	System.out.println();
            System.out.println("        주문 번호: " + order.getOrderId());
            System.out.println("        총 금액: " + order.getTotalPrice());
            System.out.println("        주문일: " + order.getOrderDate());
            System.out.println("        수령인: " + order.getRecipient());
            System.out.println("        주소: " + order.getAddress());
            System.out.println("        전화번호: " + order.getPhone());
            if (order.getOrderComment() != null) {
            	System.out.println("        요청사항: " + order.getOrderComment());
            }
            System.out.println();
            System.out.println("-------------------------------------------------");

            // 주문 항목 조회
            List<OrderItemDTO> orderItems = ordersDao.getOrderItems(order.getOrderId());
            for (OrderItemDTO item : orderItems) {
                System.out.println("            책 제목: " + item.getTitle());
                System.out.println("            가격: " + item.getPrice());
                System.out.println("            수량: " + item.getQuantity());
                System.out.println("-------------------------------------------------");
            }
        }
        System.out.println("=================================================");
        System.out.println();
        System.out.println("       이전 화면으로 돌아가려면 Enter를 누르세요.");
        System.out.println();
		sc.nextLine();
    }

}
