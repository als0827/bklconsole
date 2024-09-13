package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import common.DBManager;
import dto.BookDTO;

public class BookDAO {
	
	// 상품 검색
	public List<BookDTO> searchBook(String tempName) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<BookDTO> bookList = new ArrayList<BookDTO>();
		
		try {
			conn = DBManager.getConnection();

			String sql = "SELECT * "
					   + "FROM book "
					   + "WHERE title like ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  "%" + tempName + "%");
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int bookId = rs.getInt("bookId");
				String title = rs.getString("title");
				String author = rs.getString("author"); 
				String pub = rs.getString("pub"); 
				String bookDesc = rs.getString("bookDesc");
				String detail = rs.getString("detail");
				int price = rs.getInt("price");
				int inventory = rs.getInt("inventory");
				String genre = rs.getString("genre");
				String enroll = rs.getString("enroll");
				String pubDate = rs.getString("pubDate");
				String bookType = rs.getString("bookType");
				
				BookDTO bDto = new BookDTO(bookId, title, author, pub, bookDesc, detail, price, inventory, genre, enroll, pubDate, bookType);
				bookList.add(bDto);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(rs, pstmt, conn);
		}
		
		return bookList; 
	}
	
	//상세페이지 이동
	public BookDTO detailBook(int num) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BookDTO bookDto = null;

		try {
            conn = DBManager.getConnection();
            String sql = "SELECT * FROM book WHERE bookId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, num-15);
						
			rs = pstmt.executeQuery();
            
            if (rs.next()) {
				int bookId = rs.getInt("bookId");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String pub = rs.getString("pub");
				String bookDesc = rs.getString("bookDesc");
				String detail = rs.getString("detail");
				int price = rs.getInt("price");
				int inventory = rs.getInt("inventory");
				String genre = rs.getString("genre");
				String enroll = rs.getString("enroll");
				String pubDate = rs.getString("pubDate");
				String bookType = rs.getString("bookType");
				
				bookDto = new BookDTO(bookId, title, author, pub, bookDesc, detail, price, inventory, genre, enroll, pubDate, bookType);
				
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	DBManager.close(rs, pstmt, conn);
        }

        return bookDto;
	}
	
	 // 모든 도서 정보 조회
    public List<BookDTO> getAllBooks() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<BookDTO> bookList = new ArrayList<>();

        try {
            conn = DBManager.getConnection();
            String sql = "SELECT * FROM book";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
            	int bookId = rs.getInt("bookId");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String pub = rs.getString("pub");
				String bookDesc = rs.getString("bookDesc");
				String detail = rs.getString("detail");
				int price = rs.getInt("price");
				int inventory = rs.getInt("inventory");
				String genre = rs.getString("genre");
				String enroll = rs.getString("enroll");
				String pubDate = rs.getString("pubDate");
				String bookType = rs.getString("bookType");
				
				BookDTO bookDto = new BookDTO(bookId, title, author, pub, bookDesc, detail, price, inventory, genre, enroll, pubDate, bookType);
                bookList.add(bookDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	DBManager.close(rs, pstmt, conn);
        }
         
        return bookList;
    }
    
    // 조회수 순으로 도서 목록 가져오기
//    public List<BookDTO> getBookOrderByViewCount() {
//    	
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        List<BookDTO> bookList = new ArrayList<>();
//        
//        try {
//            conn = DBManager.getConnection();
//            String sql = "SELECT b.bookId, b.title, b.author, b.pub, b.bookDesc, "
//            		   + "b.price, b.genre, b.pubDate, b.bookType, COALESCE(SUM(v.viewCount), 0) AS totalViewCount "
//            		   + "FROM BOOK b "
//            		   + "LEFT JOIN VIEWS v ON b.bookId = v.bookId "
//            		   + "GROUP BY b.bookId, b.title, b.author, b.pub, b.bookDesc, "
//            		   + "b.price, b.genre, b.pubDate, b.bookType "
//            		   + "ORDER BY totalViewCount DESC ";
//            		   
//            pstmt = conn.prepareStatement(sql);
//            rs = pstmt.executeQuery();
//
//            while (rs.next()) {
//                String title = rs.getString("title");
//                String author = rs.getString("author");
//                String pub = rs.getString("pub");
//                String bookDesc = rs.getString("bookDesc");
//                int price = rs.getInt("price");
//                String genre = rs.getString("genre");
//                String pubDate = rs.getString("pubDate");
//                String bookType = rs.getString("bookType");
//                
//                BookDTO book = new BookDTO(0, title, author, pub, bookDesc, price, 0, genre, null, pubDate, bookType);
//                bookList.add(book);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//        	DBManager.close(rs, pstmt, conn);
//        }
//
//        return bookList;
//    }
    
    // 판매량 순으로 도서 목록 가져오기
//    public List<BookDTO> getBestseller() {
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        List<BookDTO> bestsellerList = new ArrayList<>();
//        
//        try {
//            conn = DBManager.getConnection();
//            String sql = "SELECT b.bookId, b.title, b.author, b.pub, b.bookDesc, b.price, b.genre, b.pubDate, b.bookType, COALESCE(SUM(o.quantity), 0) AS totalSoldCount "
//                       + "FROM BOOK b "
//                       + "LEFT JOIN ORDERITEM o ON b.bookId = o.bookId "
//                       + "GROUP BY b.bookId, b.title, b.author, b.pub, b.bookDesc, b.price, b.genre, b.pubDate b.bookType"
//                       + "ORDER BY totalSoldCount DESC";
//                       
//            pstmt = conn.prepareStatement(sql);
//            rs = pstmt.executeQuery();
//
//            while (rs.next()) {
//                int bookId = rs.getInt("bookId");
//                String title = rs.getString("title");
//                String author = rs.getString("author");
//                String pub = rs.getString("pub");
//                String bookDesc = rs.getString("bookDesc");
//                int price = rs.getInt("price");
//                String genre = rs.getString("genre");
//                String pubDate = rs.getString("pubDate");
//                String bookType = rs.getString("bookType");
//                BookDTO book = new BookDTO(bookId, title, author, pub, bookDesc, price, 0, genre, null, pubDate, bookType);
//                bestsellerList.add(book);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            DBManager.close(rs, pstmt, conn);
//        }
//
//        return bestsellerList;
//    }
	
    // 카테고리에 따른 도서 검색
    public List<BookDTO> searchBooksByCategory(BookDAO bookDao, String category) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<BookDTO> books = new ArrayList<>();

        try {
            conn = DBManager.getConnection();
            // 카테고리를 기준으로 도서 검색
            String sql = "SELECT * FROM BOOK WHERE genre = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, category); // 카테고리(국내도서, 해외도서, eBook 등)를 전달
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BookDTO book = new BookDTO();
                book.setBookId(rs.getInt("bookId"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPub(rs.getString("pub"));
                book.setBookDesc(rs.getString("bookDesc"));
                book.setPrice(rs.getInt("price"));
                book.setGenre(rs.getString("genre"));
                book.setEnroll(rs.getString("enroll"));
                book.setPubDate(rs.getString("pubDate"));
                book.setBookType(rs.getString("bookType"));
                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs, pstmt, conn);
        }

        return books;
    }
    
    //오늘의 책
    public List<BookDTO> todayBook() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<BookDTO> bookList = new ArrayList<>();

        try {
            conn = DBManager.getConnection();
            String sql = "SELECT b.bookId, b.title, b.author, b.pub, b.bookDesc, b.detail, b.price, "
            		   + "(SELECT COALESCE(SUM(v.viewCount), 0) FROM VIEWS v WHERE v.bookId = b.bookId) AS views, "
                       + "(SELECT COALESCE(SUM(s.quantitySold), 0) FROM SALES s WHERE s.bookId = b.bookId) AS sales "
                       + "FROM BOOK b";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BookDTO book = new BookDTO();
                book.setBookId(rs.getInt("bookId"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPub(rs.getString("pub"));
                book.setBookDesc(rs.getString("bookDesc"));
                book.setDetail(rs.getString("detail"));
                book.setPrice(rs.getInt("price"));

                int views = rs.getInt("views");
                int sales = rs.getInt("sales");

                // 리스트에 책 추가
                bookList.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs, pstmt, conn);
        }

        return bookList;
    }
    
    // 국내도서, 해외도서, eBook 구분
    public List<BookDTO> getBooksByType(String bookType) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<BookDTO> bookList = new ArrayList<>();

        try {
            conn = DBManager.getConnection();
            String sql = "SELECT * FROM BOOK WHERE bookType = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bookType);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BookDTO book = new BookDTO();
                book.setBookId(rs.getInt("bookId"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPub(rs.getString("pub"));
                book.setBookDesc(rs.getString("bookDesc"));
                book.setDetail(rs.getString("detail"));
                book.setPrice(rs.getInt("price"));
                book.setInventory(rs.getInt("inventory"));
                book.setGenre(rs.getString("genre"));
                book.setEnroll(rs.getString("enroll"));
                book.setPubDate(rs.getString("pubDate"));
                book.setBookType(rs.getString("bookType"));
                bookList.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs, pstmt, conn);
        }

        return bookList;
    }
    
    
}

