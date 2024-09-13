package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.DBManager;
import dto.CartJoinDTO;

public class CartDAO {

	// 카트 조회
		public List<CartJoinDTO> getCartItems(String memId) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<CartJoinDTO> cartItems = new ArrayList<>();

			try {
				conn = DBManager.getConnection();
				String sql = "SELECT ci.bookId, ci.memId, b.title, b.author, b.price, ci.quantity, (ci.quantity * b.price) AS totalPrice "
						   + "FROM CART ci JOIN BOOK b ON ci.bookId = b.bookId WHERE ci.memId = ?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memId);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					int bookId = rs.getInt("bookId");
					String memIdFromDB = rs.getString("memId");
					String title = rs.getString("title");
					String author = rs.getString("author");
					int price = rs.getInt("price");
					int quantity = rs.getInt("quantity");
					int totalPrice = rs.getInt("totalPrice");
					
					// CartJoinDTO 객체 생성 및 리스트에 추가
					CartJoinDTO cartItem = new CartJoinDTO(bookId, memIdFromDB, title, author, price, quantity, totalPrice);
					cartItems.add(cartItem);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(rs, pstmt, conn);
			}

			return cartItems;
		}
	
		
	// 카트에 도서 추가
	public int addToCart(String memId, int bookId, int quantity) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			conn = DBManager.getConnection();
			String sql = "INSERT INTO CART (cartId, memId, bookId, quantity) VALUES (CART_SEQ.NEXTVAL, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId); // memId를 cart_id 대신 사용
			pstmt.setInt(2, bookId);
			pstmt.setInt(3, quantity);

			result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("장바구니에 추가되었습니다.");
			} else {
				System.out.println("장바구니 추가 실패.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(pstmt, conn);
		}

		return result;
	}
	
	// 카트 비우기
	public void clearCart(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			conn = DBManager.getConnection();

			// CARTITEM 테이블에서 해당 memId의 항목 삭제
			String sql = "DELETE FROM CART WHERE memId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId); // memId로 장바구니 항목 삭제

			result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("장바구니가 비워졌습니다.");
			} else {
				System.out.println("장바구니 비우기에 실패했습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(pstmt, conn);
		}

	}

	// 카트에 담긴 도서 총액 계산
	private int calcCartTotal(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalPrice = 0;

		try {
			conn = DBManager.getConnection();
			// CARTITEM과 BOOK 테이블을 조인하여 memId로 총 가격 계산
			String sql = "SELECT SUM(b.price * c.quantity) AS total_price "
					+ "FROM CARTITEM c JOIN BOOK b ON c.bookId = b.bookId " + "WHERE c.memId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId); // memId로 장바구니 항목 조회
			rs = pstmt.executeQuery();

			if (rs.next()) {
				totalPrice = rs.getInt("total_price");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(rs, pstmt, conn);
		}

		return totalPrice;
	}
}
