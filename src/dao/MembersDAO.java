package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import common.DBManager;
import dto.MembersDTO;

public class MembersDAO {

	// 회원 가입
	public int signUp(MembersDTO memDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			conn = DBManager.getConnection();

			String sql = "INSERT ALL "
					   + "INTO members (memId, pwd, memName, email, birth, phone, gender, address) VALUES (?, ?, ?, ?, ?, ?, ?, ?) "
					   + "INTO cart (cartId, memId) VALUES (CART_SEQ.NEXTVAL, ?) "
					   + "SELECT * "
					   + "FROM DUAL";
					
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memDto.getMemId());
			pstmt.setString(2, memDto.getPwd());
			pstmt.setString(3, memDto.getMemName());
			pstmt.setString(4, memDto.getEmail());
			pstmt.setString(5, memDto.getBirth());
			pstmt.setString(6, memDto.getPhone());
			pstmt.setString(7, memDto.getGender());
			pstmt.setString(8, memDto.getAddress());
			pstmt.setString(9, memDto.getMemId());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(pstmt, conn);
		}
		
		return result;
	}
	
	// 회원 정보 수정
	public int updateMem(MembersDTO mDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = DBManager.getConnection();

			String sql = "UPDATE member "
					   + "SET pass, pwd, memName, email, birth, phone,	gender, address, join_date, with_date, status "
					   + "WHERE userId=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mDto.getPwd());
			pstmt.setString(1, mDto.getEmail());
			pstmt.setString(3, mDto.getMemName());
			pstmt.setString(4, mDto.getBirth());
			pstmt.setString(5, mDto.getPhone());
			pstmt.setString(6, mDto.getGender());
			pstmt.setString(7, mDto.getAddress());
			pstmt.setString(8, mDto.getMemId());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(pstmt, conn);
		}
		
		return result;
	}
	
	
	// 회원 목록
	public List<MembersDTO> selectMem() {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    List<MembersDTO> memList = new ArrayList<MembersDTO>();
	    
	    try {
	        conn = DBManager.getConnection();

	        String sql = "SELECT memId, memName, email, phone, address FROM member";  // 필요한 컬럼만 선택
	        pstmt = conn.prepareStatement(sql);
	        rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	            String memId = rs.getString("memId");
	            String memName = rs.getString("memName");
	            String email = rs.getString("email");
	            String phone = rs.getString("phone");
	            String address = rs.getString("address");
	            
	            // 기존 MembersDTO 생성자에 맞게 값 설정
	            MembersDTO mDto = new MembersDTO(memId, null, memName, email, null, phone, null, address, null, null, null);
	            memList.add(mDto);
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        DBManager.close(rs, pstmt, conn);
	    }
	    
	    return memList;
	}

	
	// 회원 탈퇴
	public int deleteMem(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = DBManager.getConnection();

			String sql = "DELETE FROM members WHERE memId=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(pstmt, conn);
		}
		
		return result;
	}
	
	// 회원 검색
	public List<MembersDTO> searchMem(MembersDTO tempName) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<MembersDTO> memList = new ArrayList<MembersDTO>();
		
		try {
			conn = DBManager.getConnection();

			String sql = "SELECT * "
					   + "FROM member "
					   + "WHERE memname like ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  "%" + tempName.getMemName() + "%");
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String memId = rs.getString("memId");
				String memName = rs.getString("memName");
				String email = rs.getString("email");
				String birth = rs.getString("birth");
				String phone = rs.getString("phone");
				String address = rs. getString("address");
				                  
				MembersDTO mDto = new MembersDTO(memId, null, memName, email, birth, phone, null, address, null, null, null);
				memList.add(mDto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(rs, pstmt, conn);
		}
		
		return memList; 
	}
	
	// 로그인
	public List<MembersDTO> login(String tempUserId, String tempPass) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<MembersDTO> memList = new ArrayList<MembersDTO>();
		
		try {
			conn = DBManager.getConnection();

			String sql = "SELECT * "
					   + "FROM members "
					   + "WHERE memId=? "
					   + "AND pwd=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tempUserId);
			pstmt.setString(2, tempPass);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String memId = rs.getString("memId");
				String pwd = rs.getString("pwd");
				String memName = rs.getString("memName");
				String email = rs.getString("email");
				String birth = rs.getString("birth");
				String phone = rs.getString("phone");
				String gender = rs.getString("gender");
				String address = rs.getString("address");
				String joinDate = rs.getString("joinDate");
				String wDate = rs.getString("withDate");
				String status = rs.getString("status");
				
				MembersDTO mDto = new MembersDTO(memId, pwd, memName, email, birth, phone, gender, address, joinDate, wDate, status);
				memList.add(mDto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(rs, pstmt, conn);
		}
		
		return memList;
		
	}
//	// 로그아웃
//	
//	// 로그인 체크
	public boolean loginChk(List<MembersDTO> loginInfo) {
		
		if (loginInfo == null) {
			return true;
		}
		
		return false;
	}
	
	public boolean isValidEmail(String email) {
        // 이메일 유효성 검사 정규표현식
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

	public MembersDTO getMemberInfo(String memId) {
        MembersDTO member = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBManager.getConnection();
            String sql = "SELECT * FROM MEMBERS WHERE memId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                member = new MembersDTO();
                member.setMemId(rs.getString("memId"));
                member.setMemName(rs.getString("memName"));
                member.setAddress(rs.getString("address"));
                member.setPhone(rs.getString("phone"));
                // 기타 필요한 회원 정보 설정
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs, pstmt, conn);
        }

        return member;
    }

	public String getMemName(String memId) {
        String memName = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBManager.getConnection();
            String sql = "SELECT memName FROM MEMBERS WHERE memId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                memName = rs.getString("memName");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs, pstmt, conn);
        }

        return memName;
    }

	public String getAddress(String memId) {
        String address = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBManager.getConnection();
            String sql = "SELECT address FROM MEMBERS WHERE memId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                address = rs.getString("address");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs, pstmt, conn);
        }

        return address;
    }
	
	public String getPhone(String memId) {
        String phone = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBManager.getConnection();
            String sql = "SELECT phone FROM MEMBERS WHERE memId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                phone = rs.getString("phone");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs, pstmt, conn);
        }

        return phone;
    }

	
}
	











