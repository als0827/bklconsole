package main;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.BookDAO;
import dao.CartDAO;
import dao.MembersDAO;
import dao.OrdersDAO;
import dto.BookDTO;
import dto.CartDTO;
import dto.CartJoinDTO;
import dto.MembersDTO;
import dto.OrdersDTO;
import service.BookService;
import service.MembersService;
import service.OrderService;

public class Main {

	public static void main(String[] args) {
		List<MembersDTO> loginInfo = null;
		List<MembersDTO> adminInfo = null;

		BookDAO bookDao = new BookDAO();
		CartDAO cartDao = new CartDAO();
		OrdersDAO ordersDao = new OrdersDAO();
		MembersDAO membersDao = new MembersDAO();
		MembersService membersService = new MembersService();
		BookService bookService = new BookService();
		CartDTO cartDto = new CartDTO();

		Scanner sc = new Scanner(System.in);

		while (true) {

			if (loginInfo == null && adminInfo == null) {
				System.out.println("========================================================");
				System.out.println("                                                     ");
				System.out.println("                                                     ");
				System.out.println("                 ■ ■ ■    ■    ■   ■                 ");
				System.out.println("                 ■    ■   ■  ■     ■                 ");
				System.out.println("                 ■ ■ ■    ■ ■      ■                ");
				System.out.println("                 ■    ■   ■  ■     ■                 ");
				System.out.println("                 ■ ■ ■    ■    ■   ■ ■ ■           ");
				System.out.println("                                                   ");
				System.out.println("                                                    ");
				System.out.println("========================================================");
				System.out.println("                                                    ");
				System.out.println("                                                   ");
				System.out.println("                     2. 오늘의 책                      ");
				System.out.println("                     3. 국내도서                       ");
				System.out.println("                     4. 해외도서                       ");
				System.out.println("                     5. eBook                        ");
				System.out.println("                     6. 도서검색                       ");
				System.out.println("                     9. 장바구니                       ");
				System.out.println("                    10. 주문내역                       ");
				System.out.println("                    11. 로그인                        ");
				System.out.println("                    12. 회원가입                       ");
				System.out.println("                    				                   ");
				System.out.println("                                 		            ");
				System.out.println("                    15. 종료 (Q)                     ");
				System.out.println("                                                    ");
				System.out.println("                                                    ");
				System.out.println("                                                    ");
				System.out.println("========================================================");
				System.out.print("원하시는 서비스를 선택해 주세요: ");
			} else if (loginInfo != null) {
				System.out.println("========================================================");
				System.out.println("                                              ");
				System.out.println("                                              ");
				System.out.println("                ■ ■ ■    ■    ■   ■            ");
				System.out.println("                ■    ■   ■  ■     ■            ");
				System.out.println("                ■ ■ ■    ■ ■      ■            ");
				System.out.println("                ■    ■   ■  ■     ■            ");
				System.out.println("                ■ ■ ■    ■    ■   ■ ■ ■        ");
				System.out.println("                                              ");
				System.out.println("                                              ");
				System.out.println("========================================================");
				System.out.println("                                              ");
				System.out.println("                        " + loginInfo.get(0).getMemName() + " 님 반갑습니다!        ");
				System.out.println("                                                     ");
				System.out.println("                      2. 오늘의 책                       ");
				System.out.println("                      3. 국내도서                       ");
				System.out.println("                      4. 해외도서                       ");
				System.out.println("                      5. eBook                        ");
				System.out.println("                      6. 도서검색                       ");
				System.out.println("                      9. 카트                        ");
				System.out.println("                     10. 주문내역                      ");
				System.out.println("                     11. 로그아웃                      ");
				System.out.println("                     12. 마이페이지                    ");
				System.out.println("                     13. 회원정보수정                   ");
				System.out.println("                     14.                           ");
				System.out.println("                     15. 종료 (Q)                     ");
				System.out.println("                                              ");
				System.out.println("                                            ");
				System.out.println("========================================================");
				System.out.print("원하시는 서비스를 선택해 주세요: ");
			} else if (adminInfo != null) {
				System.out.println("========================================================");
				System.out.println("                                              ");
				System.out.println("                                              ");
				System.out.println("                ■ ■ ■    ■    ■   ■            ");
				System.out.println("                ■    ■   ■  ■     ■            ");
				System.out.println("                ■ ■ ■    ■ ■      ■            ");
				System.out.println("                ■    ■   ■  ■     ■            ");
				System.out.println("                ■ ■ ■    ■    ■   ■ ■ ■        ");
				System.out.println("                                              ");
				System.out.println("                                              ");
				System.out.println("========================================================");
				System.out.println("                                               ");
				System.out.println("                                               ");
				System.out.println("                                               ");
				System.out.println("                    반갑습니다, 관리자님!              ");
				System.out.println("                                               ");
				System.out.println("                      1. 도서검색                    ");
				System.out.println("                      2. 도서등록                    ");
				System.out.println("                      3. 도서목록                    ");
				System.out.println("                      4. 도서재고                    ");
				System.out.println("                      12. 로그아웃                   ");
				System.out.println("                      13. 회원목록                   ");
				System.out.println("                      15. 종료 (Q)                   ");
				System.out.println("                                               ");
				System.out.println("                                               ");
				System.out.println("                                               ");
				System.out.println("========================================================");
				System.out.print("        원하시는 서비스를 선택해 주세요: ");
			}

			System.out.println();
			int selNum = Integer.parseInt(sc.nextLine());

			// 조회수 높은순으로 도서 목록 보이기■
//			if(selNum == 1) {■
//				■
//				List<BookDTO> list = bookDao.getBookOrderByViewCount();■
//		        for (BookDTO bookDto : list) {ㅋ
//		            System.out.println("도서명: " + bookDto.getTitle() + "저자명: " + bookDto.getAuthor() + "출판사: " + bookDto.getPub() + "설명: " + bookDto.getBookDesc()
//		                             + "가격: " + bookDto.getGenre() + "장르: " + bookDto.getPrice() + "출판일: " + bookDto.getGenre());
//				
//		        }
//				
//			}	

//			System.out.println(index + ". 도서명: " + bookDto.getTitle() + "저자명: " + bookDto.getAuthor() + "출판사: " + bookDto.getPub() + "설명: " + bookDto.getBookDesc()
//            + "가격: " + bookDto.getGenre() + "장르: " + bookDto.getPrice() + "출판일: " + bookDto.getGenre() + "\t" + (index+1) + ". 구매하기" + (index+2) + ". 장바구니");
//System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
//index = index + 3;

			if (selNum == 1) {
				
			} else if (selNum == 2) {
				bookService.displayTodayBook(loginInfo);
			} else if (selNum == 3) {
				List<BookDTO> domesticList = bookDao.getBooksByType("국내도서");
				BookService bookSerivce = new BookService();
				
				if (loginInfo != null) {
					bookSerivce.domestic(domesticList, loginInfo);
				} else {
					bookSerivce.domestic(domesticList, loginInfo);
				}
				

			} 
			else if (selNum == 4) {
				List<BookDTO> overseasList = bookDao.getBooksByType("해외도서");
				BookService bookSerivce = new BookService();
				
					bookSerivce.overseas(overseasList, loginInfo);
				
			} else if (selNum == 5) {
				List<BookDTO> eBookList = bookDao.getBooksByType("eBook");
				BookService bookSerivce = new BookService();
				
					bookSerivce.eBook(eBookList, loginInfo);
				
//			} else if (selNum == 6) {
//			    System.out.print("검색하실 도서를 입력해 주세요: ");
//			    String searchKeyword = sc.nextLine();
//			    bookService.displayBooks(searchKeyword, loginInfo != null ? loginInfo.get(0).getMemId() : "guest");
				
				
				
			


			    // 판매량 순으로 도서 목록 보이기
//				System.out.println();
//				System.out.println();
//				System.out.println();
//				System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
//				List<BookDTO> list = bookDao.getBookOrderByViewCount();
//		        
//				for (BookDTO bookDto : list) {
//		        	System.out.printf(bookDto.getTitle());
//		        	System.out.printf("%20s", bookDto.getAuthor());
//		        	System.out.printf("%20s\n", bookDto.getPub());
//		        }
//				
//				
//		        System.out.println();
//		        System.out.println("상세페이지로 가기 - 번호 선택 : ");
//		        System.out.println();		        
//		        System.out.println("1. 메인으로 ");
//		        System.out.println();		        
//		       
//		        int tempSelNum = Integer.parseInt(sc.nextLine());
//		        BookDTO bDto = null;
//		        
//		        if (16 <= tempSelNum && tempSelNum < 36) { // 상세페이지
//		        	System.out.println();
//		        	bDto = bookDao.detailBook(tempSelNum);
//		        	
//		        	System.out.println();
//		        	System.out.println(bDto.getTitle());
//		        	System.out.println();
//		        	System.out.println(bDto.getAuthor());
//		        	System.out.println();
//		        	System.out.println(bDto.getPub());
//		        	System.out.println();
//		        	System.out.println(bDto.getBookDesc());
//		        	System.out.println();
//		        	System.out.println(bDto.getPrice());
//		        	System.out.println();
//		        	System.out.println(bDto.getGenre());
//		        	System.out.println();
//		        	System.out.println(bDto.getPubDate());
//		        	
//		        	System.out.println();		        
//			        System.out.println("1. 메인으로 2. 장바구니 담기 ");
//			        System.out.println();		        
//			        tempSelNum = Integer.parseInt(sc.nextLine());
//		        }
//		        int result = 0;
//		        if (tempSelNum == 1) {  
//		        	continue;
//		        } else if (tempSelNum == 2 && loginInfo != null) {
//		        	System.out.println("1.메인으로" + "\t" + "2.담기" + "\t" + "3.수량 변경(기본 1권)");
//		        	tempSelNum = Integer.parseInt(sc.nextLine());
//		        	if (tempSelNum == 1) {
//		        		continue;
//		        	} else if (tempSelNum == 2 && loginInfo != null) {
//		        		result =cartDao.addToCart(loginInfo.get(0).getMemId(), bDto.getBookId(), 1);
//		        	} else if (tempSelNum == 3) {
//		        		System.out.println("몇 권을 담으시겠습니까?");
//		        		tempSelNum = Integer.parseInt(sc.nextLine());
//		        		result =cartDao.addToCart(loginInfo.get(0).getMemId(), bDto.getBookId(), tempSelNum);
//		        	}
//		        } else if (tempSelNum == 2 && loginInfo == null) {
//		        	System.out.println("로그인 후 이용 해주세요");
//		        	System.out.println("1.메인으로");
//		        	
//		        }
//		        
//		        if (result > 0) {
//					
//					System.out.println("정상 처리되었습니다.");
//					
//					
//				} else {
//					System.out.println("실패 되었습니다.");
//				}
//		        switch (tempSelNum) {
//		        	case 11: 
//		        	
//		        }

			} else if (selNum == 9) {
			    boolean loginChk = membersDao.loginChk(loginInfo);

			    if (loginChk) {
			        System.out.println("로그인이 필요한 서비스 입니다.");
			        continue;
			    } else {
			        List<CartJoinDTO> cartlist = cartDao.getCartItems(loginInfo.get(0).getMemId());
			        DecimalFormat df = new DecimalFormat("#,###");

			        // 중복된 책을 합치기 위해 Map으로 처리
			        Map<String, CartJoinDTO> consolidatedCart = new HashMap<>();
			        
			        for (CartJoinDTO cartJoinDTO : cartlist) {
			            String key = cartJoinDTO.getTitle() + cartJoinDTO.getAuthor(); // 책 제목과 저자로 구분

			            if (consolidatedCart.containsKey(key)) {
			                // 중복된 책이 있을 경우 수량과 가격을 합산
			                CartJoinDTO existingItem = consolidatedCart.get(key);
			                existingItem.setQuantity(existingItem.getQuantity() + cartJoinDTO.getQuantity());
			                existingItem.setTotalPrice(existingItem.getTotalPrice() + cartJoinDTO.getTotalPrice());
			            } else {
			                consolidatedCart.put(key, cartJoinDTO);
			            }
			        }

			        int totalSum = 0;
			        System.out.println("=========================================");
			        System.out.println("              장바구니 내역               ");
			        System.out.println("=========================================");
			        System.out.printf("%-20s %-15s %-10s %-15s%n", "상품정보", "저자", "수량", "상품금액");
			        System.out.println("-----------------------------------------");

			        for (CartJoinDTO cartJoinDTO : consolidatedCart.values()) {
			            String formattedTotalPrice = df.format(cartJoinDTO.getTotalPrice());

			            // 각 항목을 정렬하여 출력 (저자 포함)
			            System.out.printf("%-20s %-15s %-10d %-15s%n", cartJoinDTO.getTitle(), cartJoinDTO.getAuthor(), cartJoinDTO.getQuantity(), formattedTotalPrice);

			            totalSum += cartJoinDTO.getTotalPrice();
			        }

			        // 하단 구분선과 합계 출력
			        System.out.println("-----------------------------------------");
			        String formattedTotalSum = df.format(totalSum);
			        System.out.printf("%-20s %-10s %-15s%n", "총 상품금액", "", formattedTotalSum);
			        System.out.println("=========================================");

			        // 메뉴 출력
			        System.out.println("1. 주문하기");
			        System.out.println("2. 카트 비우기");
			        System.out.println("3. 메뉴로 돌아가기");
			        System.out.println("=========================================");
			        System.out.print("원하시는 작업을 선택해 주세요: ");

			        selNum = Integer.parseInt(sc.nextLine());

			        if (selNum == 1) {
			            System.out.println("1. 배송지, 수령인 회원정보 일치");
			            System.out.println("2. 새로운 정보 입력");
			            int infoNum = Integer.parseInt(sc.nextLine());

			            if (infoNum == 1) {
			                System.out.println("요청사항을 입력해 주세요");
			                String orderComment = sc.nextLine();

			                String memId = loginInfo.get(0).getMemId();
			                String memName = loginInfo.get(0).getMemName();  
			                String address = loginInfo.get(0).getAddress();  
			                String phone = loginInfo.get(0).getPhone();  

			                // 주문 정보 생성
			                OrdersDTO ordersDto = new OrdersDTO(0, memId, totalSum, cartDto.getQuantity(), orderComment, null, memName, address, phone);

			                // 주문 처리 및 장바구니 항목 전달
			                int result = ordersDao.cartOrderWithItems(ordersDto, cartlist);
			                if (result > 0) {
			                    cartDao.clearCart(loginInfo.get(0).getMemId());
			                }

			            } else if (infoNum == 2) {
			                // 새로운 배송 정보 입력
			                System.out.print("수령인을 입력해 주세요: ");
			                String recipient = sc.nextLine();
			                System.out.print("배송지 주소를 입력해 주세요: ");
			                String address = sc.nextLine();
			                System.out.print("연락 가능한 전화번호를 입력해 주세요: ");
			                String phone = sc.nextLine();
			                System.out.println("요청사항을 입력해 주세요");
			                String orderComment = sc.nextLine();

			                // 주문 정보 생성
			                OrdersDTO ordersDto = new OrdersDTO(0, loginInfo.get(0).getMemId(), totalSum, cartDto.getQuantity(), orderComment, null, recipient, address, phone);

			                // 주문 처리 및 장바구니 항목 전달
			                int result = ordersDao.cartOrderWithItems(ordersDto, cartlist);
			                if (result > 0) {
			                    cartDao.clearCart(loginInfo.get(0).getMemId());
			                }
			            }

			        } else if (selNum == 2) {
			            cartDao.clearCart(loginInfo.get(0).getMemId());
			            continue;
			        }
			    }
			    
			//주문 내역    
			} else if (selNum == 10) {
				OrderService orderService = new OrderService();
				orderService.showOrderHistory(loginInfo.get(0).getMemId());
			
			
			
//			else if(loginInfo == null && adminInfo != null && selNum ==1) { //상품등록
//				System.out.println("등록하실 도서를 입력해 주세요 : ");
//				int bookId = sc.nextInt();
				
//				String title = sc.nextLine();
//				String author = sc.nextLine();
//				String pub = sc.nextLine();
//				String bookDesc = sc.nextLine();
//				int price = sc.nextInt();
//				int stock = sc.nextInt();
//				String genre = sc.nextLine();
//				String enroll = sc.nextLine();
//				String pubDate = sc.nextLine();
//				
			
			} 
			else if (loginInfo == null && adminInfo == null && selNum == 12) { // 회원가입
				
				loginInfo = membersService.signUpService();

			// 마이페이지
			} else if (loginInfo != null && adminInfo == null && selNum == 12) { //마이페이지
				for (MembersDTO membersDTO : loginInfo) {
					System.out.println("        아이디  :  " + membersDTO.getMemId());
					System.out.println("        비밀번호  :  " + membersDTO.getPwd());
					System.out.println("        email  :  " + membersDTO.getEmail());
					System.out.println("        생년월일  :  " + membersDTO.getBirth());
					System.out.println("        휴대전화  :  " + membersDTO.getPhone());
					System.out.println("        성   별  :  " + membersDTO.getGender());
					System.out.println("        주   소  :  " + membersDTO.getAddress());
					System.out.println("        가입일  :  " + membersDTO.getJoinDate());
					System.out.println();
					System.out.println();
					System.out.println("        1. 회원정보수정");
					System.out.println("        2. 회원탈퇴");
					System.out.println("        3. 이전으로");
					
					selNum = Integer.parseInt(sc.nextLine());
					if (selNum == 1) {
						membersService.editInfoService(loginInfo);
						break;
					} else if (selNum == 2) {
						while (true) {
							
							System.out.print("탈퇴하시려면 비밀번호를 다시 한번 입력해주세요 : ");
							String pwd = sc.nextLine();
									
							if (pwd.equals(loginInfo.get(0).getPwd())) {
								
								int result = membersDao.deleteMem(loginInfo.get(0).getMemId());
								
								if (result > 0) {
								System.out.println("정상 처리되었습니다.");
									loginInfo = null;
									break;
								} else {
									System.out.println("실패 되었습니다.");
								}
								
							} else {
								System.out.println("비밀번호가 틀렸습니다. 다시 한번 확인해주세요.");
								System.out.println("1.다시 시도 2.메인으로");
								int tempSel = Integer.parseInt(sc.nextLine());
								if (tempSel == 1) {
									continue;
								} else if (tempSel == 2) {
									break;
								} else {
									break;
								}
							}
						}
					} else if (selNum == 3) {
						break;
					}
				}
			} else if (selNum == 15) { //종료
				sc.nextLine();
				System.out.println("종료하실려면 Q문자를 입력해 주세요 : ");
				String quiteTemp = sc.nextLine();
				String quite = quiteTemp.toUpperCase();
				
				if (quite.equals("Q")) {
					System.out.println("시스템을 종료 합니다.");
					System.exit(0);
				}
				
			} else if (loginInfo == null && adminInfo == null && selNum == 11) { // 로그인
				
				boolean a = true;
						
				while (a) {
				
					loginInfo = membersService.loginService();
					System.out.println();
					String memId = loginInfo.get(0).getMemId();
					
					if (loginInfo != null && loginInfo.size() > 0 && memId.equals("admin")) {
						adminInfo = loginInfo;
						loginInfo = null;
						break;
					} else if (loginInfo != null && loginInfo.size() > 0) {
						break;
					} else {
						System.out.println("로그인 실패. 다시 시도해 주세요.");
					}
				}
			} else if (loginInfo != null && adminInfo == null && selNum == 11) { // 로그아웃
				if (loginInfo != null && loginInfo.size() > 0) {
					System.out.println("로그아웃 되었습니다.");
					loginInfo = null;
					adminInfo = null;
				}
			} else if (loginInfo != null && adminInfo == null && selNum == 13) {
				
				membersService.editInfoService(loginInfo);
				System.out.print("바꿀 비밀번호를 입력해 주세요 : ");
				String pwd = sc.nextLine();
				System.out.print("바꿀 이름을 입력해 주세요 : ");
				String memName = sc.nextLine();
				System.out.print("바꿀 생일을 입력해 주세요 : ");
				String email = sc.nextLine();
				System.out.print("생일을 입력해 주세요 : ");
				String birth = sc.nextLine();
				System.out.print("전화번호를 입력해 주세요 : ");
				String phone = sc.nextLine();
				System.out.print("성별를 입력해 주세요 : ");
				String gender = sc.nextLine();
				System.out.print("주소를 입력해 주세요 : ");
				String address = sc.nextLine();
				
				MembersDTO mDto = new MembersDTO(null, pwd, memName, email, birth, phone, gender, address, null, null, null);
				
				int result = membersDao.updateMem(mDto);
				
				if (result > 0) {
					
					System.out.println("정상 처리되었습니다.");
					
				} else {
					System.out.println("실패 되었습니다.");
				}
			} else if (selNum == 15) {
				System.out.println("시스템을 종료 합니다.");
				System.exit(0);
			}

		}
	}
}
