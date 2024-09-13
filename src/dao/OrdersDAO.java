package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.DBManager;
import dto.BookDTO;
import dto.CartJoinDTO;
import dto.MembersDTO;
import dto.OrderItemDTO;
import dto.OrdersDTO;

public class OrdersDAO {
	
	public int placeOrderWithItems(OrdersDTO order, BookDTO book) {
	    Connection conn = null;
	    PreparedStatement pstmtOrder = null;
	    PreparedStatement pstmtOrderItem = null;
	    int result = 0;

	    try {
	        conn = DBManager.getConnection();
	        conn.setAutoCommit(false); // 트랜잭션 시작
	        
	        int totalOrderPrice = order.getTotalPrice() * order.getQuantity();
	        // 1. ORDERS 테이블에 주문 정보 삽입
	        String sqlOrder = "INSERT INTO ORDERS (orderId, memId, totalPrice, quantity, orderComment, orderDate, recipient, address, phone) "
	                        + "VALUES (ORDERS_SEQ.NEXTVAL, ?, ?, ?, ?, SYSDATE, ?, ?, ?)";
	        pstmtOrder = conn.prepareStatement(sqlOrder, new String[] { "orderId" });
	        pstmtOrder.setString(1, order.getMemId());
	        pstmtOrder.setInt(2, totalOrderPrice);
	        pstmtOrder.setInt(3, order.getQuantity());
	        pstmtOrder.setString(4, order.getOrderComment());
	        pstmtOrder.setString(5, order.getRecipient());
	        pstmtOrder.setString(6, order.getAddress());
	        pstmtOrder.setString(7, order.getPhone());
	        
	        result = pstmtOrder.executeUpdate();

	        // 주문 ID를 가져옴 (자동 생성된 키)
	        ResultSet generatedKeys = pstmtOrder.getGeneratedKeys();
	        int orderId = 0;
	        if (generatedKeys.next()) {
	            orderId = generatedKeys.getInt(1);
	        }

	        // 2. ORDERITEM 테이블에 각 도서 항목 삽입
	        String sqlOrderItem = "INSERT INTO ORDERITEM (orderItemId, orderId, bookId, title, quantity, Price) "
	                            + "VALUES (ORDERITEM_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
	        pstmtOrderItem = conn.prepareStatement(sqlOrderItem);
	            pstmtOrderItem.setInt(1, orderId);  // 방금 삽입된 주문 ID 사용
	            pstmtOrderItem.setInt(2, book.getBookId());
	            pstmtOrderItem.setString(3, book.getTitle());
	            pstmtOrderItem.setInt(4, order.getQuantity());  // 각 도서의 수량
	            pstmtOrderItem.setInt(5, book.getPrice());  // 각 도서의 가격

	            pstmtOrderItem.executeUpdate();

	        // 3. 트랜잭션 커밋
	        conn.commit();
	        System.out.println();
	        System.out.println("주문이 성공적으로 처리되었습니다.");
	        
	    } catch (Exception e) {
	        try {
	            if (conn != null) {
	                conn.rollback();  // 오류 발생 시 롤백
	            }
	        } catch (Exception rollbackEx) {
	            rollbackEx.printStackTrace();
	        }
	        e.printStackTrace();
	        result = 0;
	    } finally {
	        DBManager.close(pstmtOrderItem, pstmtOrder, conn);  // 자원 해제
	    }

	    return result;
	}
	
	public int cartOrderWithItems(OrdersDTO order, List<CartJoinDTO> cartlist) {
	    Connection conn = null;
	    PreparedStatement pstmtOrder = null;
	    PreparedStatement pstmtOrderItem = null;
	    int result = 0;

	    try {
	        conn = DBManager.getConnection();
	        conn.setAutoCommit(false); // 트랜잭션 시작

	        // 1. ORDERS 테이블에 주문 정보 삽입
	        String sqlOrder = "INSERT INTO ORDERS (orderId, memId, totalPrice, quantity, orderComment, orderDate, recipient, address, phone) "
	                        + "VALUES (ORDERS_SEQ.NEXTVAL, ?, ?, ?, ?, SYSDATE, ?, ?, ?)";
	        pstmtOrder = conn.prepareStatement(sqlOrder, new String[] { "orderId" });
	        pstmtOrder.setString(1, order.getMemId());
	        pstmtOrder.setInt(2, order.getTotalPrice());
	        pstmtOrder.setInt(3, order.getQuantity());
	        pstmtOrder.setString(4, order.getOrderComment());
	        pstmtOrder.setString(5, order.getRecipient());
	        pstmtOrder.setString(6, order.getAddress());
	        pstmtOrder.setString(7, order.getPhone());
	        
	        result = pstmtOrder.executeUpdate();

	        // 주문 ID를 가져옴 (자동 생성된 키)
	        ResultSet generatedKeys = pstmtOrder.getGeneratedKeys();
	        int orderId = 0;
	        if (generatedKeys.next()) {
	            orderId = generatedKeys.getInt(1);
	        }

	        // 2. ORDERITEM 테이블에 각 도서 항목 삽입
	        String sqlOrderItem = "INSERT INTO ORDERITEM (orderItemId, orderId, bookId, title, quantity, price) "
	                            + "VALUES (ORDERITEM_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
	        pstmtOrderItem = conn.prepareStatement(sqlOrderItem);
	        
	        // cartlist에 있는 각 책을 ORDERITEM 테이블에 삽입
	        for (CartJoinDTO cartItem : cartlist) {
	            pstmtOrderItem.setInt(1, orderId); // 주문 ID 사용
	            pstmtOrderItem.setInt(2, cartItem.getBookId());
	            pstmtOrderItem.setString(3, cartItem.getTitle());
	            pstmtOrderItem.setInt(4, cartItem.getQuantity()); // 장바구니에 있는 수량
	            pstmtOrderItem.setInt(5, cartItem.getTotalPrice() / cartItem.getQuantity()); // 가격 (총 금액을 수량으로 나눠서 단가 계산)
	            
	            pstmtOrderItem.executeUpdate();
	        }

	        // 3. 트랜잭션 커밋
	        conn.commit();
	        System.out.println("주문이 성공적으로 처리되었습니다.");

	    } catch (Exception e) {
	        try {
	            if (conn != null) {
	                conn.rollback();  // 오류 발생 시 롤백
	            }
	        } catch (Exception rollbackEx) {
	            rollbackEx.printStackTrace();
	        }
	        e.printStackTrace();
	        result = 0;
	    } finally {
	        DBManager.close(pstmtOrderItem, pstmtOrder, conn);  // 자원 해제
	    }

	    return result;
	}
	
	public List<OrdersDTO> getOrderHistory(String memId) {
        List<OrdersDTO> orderList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBManager.getConnection();
            String sql = "SELECT o.orderId, o.totalPrice, o.orderDate, o.recipient, o.address, o.phone, o.orderComment " +
                         "FROM ORDERS o " +
                         "WHERE o.memId = ? " +
                         "ORDER BY o.orderDate DESC";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                OrdersDTO order = new OrdersDTO();
                order.setOrderId(rs.getInt("orderId"));
                order.setTotalPrice(rs.getInt("totalPrice"));
                order.setOrderDate(rs.getString("orderDate"));
                order.setRecipient(rs.getString("recipient"));
                order.setAddress(rs.getString("address"));
                order.setPhone(rs.getString("phone"));
                order.setOrderComment(rs.getString("orderComment"));
                orderList.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs, pstmt, conn);
        }

        return orderList;
    }

    // 주문 항목 조회 메서드 (각 주문의 상세 항목)
    public List<OrderItemDTO> getOrderItems(int orderId) {
        List<OrderItemDTO> orderItems = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBManager.getConnection();
            String sql = "SELECT oi.bookId, b.title, oi.quantity, oi.price " +
                         "FROM ORDERITEM oi JOIN BOOK b ON oi.bookId = b.bookId " +
                         "WHERE oi.orderId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                OrderItemDTO orderItem = new OrderItemDTO();
                orderItem.setBookId(rs.getInt("bookId"));
                orderItem.setTitle(rs.getString("title"));
                orderItem.setQuantity(rs.getInt("quantity"));
                orderItem.setPrice(rs.getInt("price"));
                orderItems.add(orderItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs, pstmt, conn);
        }

        return orderItems;
    }	
}