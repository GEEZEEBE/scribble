package com.scribble.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.scribble.etc.PageVo;
import com.scribble.vo.SboardSuserVo;
import com.scribble.vo.SboardVo;

public class SboardDaoImpl implements SboardDao {
	
	// Get DB connection.
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
//			conn = DriverManager.getConnection(dburl, "c##webdb", "1234");
			conn = DriverManager.getConnection(dburl, "webdb", "1234");
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC 드라이버 로드 실패!");
		}
		return conn;
	}

	// Get list of postings in 'board' table, joined 'users' w/ and w/o searching keyword.
	@Override
	public List<SboardSuserVo> getList(PageVo pvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SboardSuserVo> list = new ArrayList<>();

		int start = pvo.getPageStart();
		int end = pvo.getCountPerPage();
		String keyword = pvo.getKeyword();
		
		try {
			conn = getConnection();
			if (keyword.equals("null") || keyword.equals("")) {
				String query = "SELECT *										" + 
					    	   "FROM ( SELECT ROWNUM AS RNUM, A.*				" + 
					    	   "		FROM ( SELECT *							" + 
					    	   "			   FROM sboard b					" + 
					    	   "			   JOIN susers u					" + 
					    	   "			   ON b.user_id = u.user_id 		" + 
					    	   "			   WHERE b.isdeleted is NULL		" + 
					    	   "			   ORDER BY reg_date DESC			" + 
					    	   "	 ) A										" + 
					    	   "	   WHERE ROWNUM <= ? + ? 					" + 
					    	   ") 												" + 
					    	   "WHERE RNUM > ?									";
				pstmt = conn.prepareStatement(query);
				
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				pstmt.setInt(3, start);
			
			} else {
				String query = "SELECT *															" + 
					    	   "FROM ( SELECT ROWNUM AS RNUM, A.*									" + 
					    	   "		FROM ( SELECT *												" + 
					    	   "			   FROM sboard b                                      " +
					    	   "			   JOIN susers u											" + 
					    	   "			   ON b.user_id = u.user_id									" + 
					    	   "			   WHERE (b.title || b.content || b.reg_date || u.name)	" + 
					    	   "			   LIKE ?												" + 
					    	   "			   AND isdeleted is NULL								" + 
					    	   "			   ORDER BY reg_date DESC								" + 
					    	   "		) A															" + 
					    	   "		WHERE ROWNUM <= ? + ? 										" + 
					    	   ") 																	" + 
					    	   "WHERE RNUM > ?														";
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, "%" + keyword + "%");
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				pstmt.setInt(4, start);
			}
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				SboardSuserVo vo = new SboardSuserVo();
				
				vo.setBoard_id(rs.getInt("board_id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setHit(rs.getInt("hit"));
				vo.setReg_date(rs.getString("reg_date"));
				vo.setImg_name("filestorage/" + rs.getString("img_name"));
				vo.setIsdeleted(rs.getString("isdeleted"));
				vo.setUser_id(rs.getInt("user_id"));				
				vo.setName(rs.getString("name"));

				list.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null)  conn.close();
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}

		return list;
	}

	// Get a posting in 'board' table, joined 'users'. 
	@Override
	public SboardSuserVo get(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SboardSuserVo vo = new SboardSuserVo();

		try {
			conn = getConnection();
			String query = "SELECT *					" + 
				    	   "FROM sboard b				" + 
				    	   "JOIN susers u				" + 
				    	   "ON b.user_id = u.user_id	" + 
				    	   "WHERE b.board_id = ?		"; 
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				vo.setBoard_id(rs.getInt("board_id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setHit(rs.getInt("hit"));
				vo.setReg_date(rs.getString("reg_date"));
				vo.setImg_name("filestorage/" + rs.getString("img_name"));
				vo.setIsdeleted(rs.getString("isdeleted"));
				vo.setUser_id(rs.getInt("user_id"));				
				vo.setName(rs.getString("name"));			
				
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null)  conn.close();
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}

		return vo;
	}

	// Register a posting.
	@Override
	public int insert(SboardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;

		try {
			conn = getConnection();
			
			String query = "INSERT										" +
						   "INTO sboard									" +
						   "VALUES ( seq_sboard_no.nextval, ?, ?, 0,	" + 
						   "		 SYSDATE, ?, NULL, ? ) 	";
	
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());						
			pstmt.setString(3, vo.getImg_name());
			pstmt.setInt(4, vo.getUser_id());
			
			insertedCount = pstmt.executeUpdate();

			System.out.println(insertedCount + "건 등록");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null)  conn.close();
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		
		return insertedCount;
	}

	// Modify a posting.
	@Override
	public int update(SboardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int updatedCount = 0;

		try {
			conn = getConnection();
			//참고용"board_id,  title,content, hit, reg_date,img_name,isdeleted, user_id"
			String query = "UPDATE sboard 													" +
						   "SET title = ?, content = ?, reg_date = SYSDATE, 		" +
						   "	img_name =  ?                                     	" +
						   "WHERE sboard_id = ?													";
			
			// 
			
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());			
			pstmt.setString(3, vo.getImg_name());			
			pstmt.setInt(4, vo.getBoard_id());
			
			updatedCount = pstmt.executeUpdate();
			
			System.out.println(updatedCount + "건 등록");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}

		return updatedCount; 
	}

	// Delete a posting. (Actually, is not deleted)
	@Override
	public int delete(int board_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deletedCount = 0;

		try {
			conn = getConnection();

			String query = "UPDATE sboard			" +
						   "SET isdeleted = 'true'	" +
						   "WHERE board_id = ?				";
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, board_id);

			deletedCount = pstmt.executeUpdate();

			System.out.println(deletedCount + "건 삭제");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null)  conn.close();
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		
		return deletedCount;
	}

	// Counting total number of postings.
	@Override
	public int getTotalCount(String keyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalCount = 0;
		
		try {
			conn = getConnection();
			
			if (keyword.equals("null") || keyword.equals("")) {
				String query = "SELECT COUNT(board_id)		" +
							   "FROM sboard					" +
							   "WHERE isdeleted is NULL		";
								
				pstmt = conn.prepareStatement(query);
				
			} else {
				String query = "SELECT COUNT(b.board_id)										" +
							   "FROM sboard b											" +
							   "JOIN susers u											" +
							   "ON b.user_id = u.user_id										" +
							   "WHERE (b.title || b.content || b.reg_date || u.name)	" +
							   "LIKE ?													" +
							   "AND isdeleted is NULL									";
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, "%" + keyword + "%");
			}
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				totalCount = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null)  conn.close();
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		
		return totalCount;
	}

	// Increase hit count of posting.
	@Override
	public void upHitCount(int board_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			
			String query = "UPDATE sboard		" +
						   "SET hit = hit + 1	" +
						   "WHERE board_id = ?		";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, board_id);

			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null)  conn.close();
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	}	

	@Override
	public List<SboardSuserVo> getHitTop4() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SboardSuserVo> list = new ArrayList<>();

		try {
			conn = getConnection();
			
			String query = "SELECT ROWNUM, A.*						" + 
				    	   "FROM ( SELECT *							" + 
				    	   "	   FROM sboard b					" + 
				    	   "	   JOIN susers u					" + 
				    	   "	   ON b.user_id = u.user_id 		" + 
				    	   "	   WHERE b.isdeleted is NULL		" + 
				    	   "	   ORDER BY reg_date DESC			" + 
				    	   ") A										" + 
				    	   "WHERE ROWNUM < 5						";
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				SboardSuserVo vo = new SboardSuserVo();
				
				vo.setBoard_id(rs.getInt("board_id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setHit(rs.getInt("hit"));
				vo.setReg_date(rs.getString("reg_date"));
				vo.setImg_name("filestorage/" + rs.getString("img_name"));
				vo.setIsdeleted(rs.getString("isdeleted"));
				vo.setUser_id(rs.getInt("user_id"));				
				vo.setName(rs.getString("name"));

				list.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null)  conn.close();
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}

		return list;
	}
	
	public List<SboardSuserVo> getMyList(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SboardSuserVo> list = new ArrayList<>();

		try {
			conn = getConnection();
			
			String query = "SELECT ROWNUM, A.*						" + 
				    	   "FROM ( SELECT *							" + 
				    	   "	   FROM sboard b					" + 
				    	   "	   JOIN susers u					" + 
				    	   "	   ON b.user_id = u.user_id 		" + 
				    	   "	   WHERE b.isdeleted is NULL		" + 
				    	   "	   AND b.user_id = ?				" + 
				    	   "	   ORDER BY reg_date DESC			" + 
				    	   ") A										" + 
				    	   "WHERE ROWNUM < 6						";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				SboardSuserVo vo = new SboardSuserVo();
				
				vo.setBoard_id(rs.getInt("board_id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setHit(rs.getInt("hit"));
				vo.setReg_date(rs.getString("reg_date"));
				vo.setImg_name("filestorage/" + rs.getString("img_name"));
				vo.setIsdeleted(rs.getString("isdeleted"));
				vo.setUser_id(rs.getInt("user_id"));				
				vo.setName(rs.getString("name"));

				list.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null)  conn.close();
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}

		return list;
	}
	
	//페이징 및 블럭 테스트를 위한 게시물 저장 메소드 
	public void post100(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = getConnection();
			
			//참고용"board_id,  title,content, hit, reg_date,img_name,isdeleted, user_id"
			sql = "INSERT INTO sboard VALUES (seq_board_no.nextval, '제목', '내용', 0, SYSDATE, 'pic01.jpg', NULL, 1)";
			//fk user_id 처리 : 0...?...
			
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < 100; i++) {
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null)  conn.close();
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	}
		
	//main
	public static void main(String[] args) {
		new SboardDaoImpl().post100();
		System.out.println("SUCCESS");
	}

}
