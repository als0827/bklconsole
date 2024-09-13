package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import common.DBManager;

public class ViewsDAO {

	public void incrementViewCount(int bookId, String memId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            // 1. 데이터베이스 연결
            conn = DBManager.getConnection();
            if (conn == null) {
                throw new SQLException("데이터베이스 연결에 실패했습니다.");
            }

            // 2. SQL 문 작성
            String sql = "INSERT INTO VIEWS (viewId, bookId, viewCount, memId, viewDate) "
                       + "VALUES (VIEWS_SEQ.NEXTVAL, ?, 1, ?, SYSDATE)";
            
            // 3. PreparedStatement 생성
            pstmt = conn.prepareStatement(sql);

            // 4. bookId와 memId 값 설정
            pstmt.setInt(1, bookId);

            // memId가 null이면 guest로 처리
            if (memId == null) {
                pstmt.setString(2, "guest");
            } else {
                pstmt.setString(2, memId);
            }

            // 5. SQL 실행
            result = pstmt.executeUpdate();
            System.out.println("조회수 증가 완료, 결과: " + result);

        } catch (SQLException e) {
            e.printStackTrace(); // SQL 예외 발생 시 출력
        } finally {
            // 6. 자원 해제
            DBManager.close(pstmt, conn);
        }
    }

}

