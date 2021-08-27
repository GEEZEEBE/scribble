package com.scribble.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.scribble.etc.PageVo;
import com.scribble.vo.SCommentUserVo;
import com.scribble.vo.SCommentVo;

public class SCommentDaoImpl implements SCommentDao {
	
	// Get DB connection.
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dburl = "jdbc:oracle:thin:@10.211.55.4:1521:xe";
//			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
//			conn = DriverManager.getConnection(dburl, "c##webdb", "1234");
			conn = DriverManager.getConnection(dburl, "webdb", "1234");
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC 드라이버 로드 실패!");
		}
		return conn;
	}

	// Get list of postings in 'board' table, joined 'users' w/ and w/o searching keyword.
	@Override
	public List<SCommentUserVo> getList(PageVo pvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SCommentUserVo> list = new ArrayList<>();

		int start = pvo.getPageStart();
		int end = pvo.getCountPerPage();
		int boardId = Integer.parseInt(pvo.getKeyword());
		
		try {
			conn = getConnection();

			String query = "SELECT *									" + 
				    	   "FROM ( SELECT ROWNUM AS RNUM, A.*			" + 
				    	   "		FROM ( SELECT *						" + 
				    	   "			   FROM scomment c				" + 
				    	   "			   JOIN susers u				" + 
				    	   "			   ON c.user_id = u.user_id		" + 
				    	   "			   WHERE c.board_id	= ?			" + 
				    	   "			   AND c.isdeleted is NULL		" + 
				    	   "			   ORDER BY c.reg_date DESC		" + 
				    	   "		) A									" + 
				    	   "		WHERE ROWNUM <= ? + ? 				" + 
				    	   ") 											" + 
				    	   "WHERE RNUM > ?								";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, boardId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			pstmt.setInt(4, start);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				SCommentUserVo vo = new SCommentUserVo();
				
				vo.setCommentId(rs.getInt("comment_id"));
				vo.setContent(rs.getString("content"));
				vo.setBoardId(rs.getInt("board_id"));
				vo.setUserId(rs.getInt("user_id"));
				vo.setRegDate(rs.getString("reg_date"));
				vo.setIsdeleted(rs.getString("isdeleted"));	
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
	public SCommentUserVo get(int comment_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SCommentUserVo vo = new SCommentUserVo();

		try {
			conn = getConnection();
			String query = "SELECT *					" + 
				    	   "FROM scomment c				" + 
				    	   "JOIN susers u				" + 
				    	   "ON c.user_id = u.user_id	" + 
				    	   "WHERE c.comment_id = ?		"; 
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, comment_id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				vo.setCommentId(rs.getInt("comment_id"));
				vo.setContent(rs.getString("content"));
				vo.setBoardId(rs.getInt("board_id"));
				vo.setUserId(rs.getInt("user_id"));
				vo.setRegDate(rs.getString("reg_date"));
				vo.setIsdeleted(rs.getString("isdeleted"));	
				vo.setName(rs.getString("name"));
			}
//			System.out.println(vo);
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
	public int insert(SCommentVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;

		try {
			conn = getConnection();
			
			String query = "INSERT										" +
						   "INTO scomment								" +
						   "VALUES ( seq_board_no.nextval, ?, ?, ?, 	" + 
						   "		 SYSDATE, NULL )					";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, vo.getContent());
			pstmt.setInt(2, vo.getBoardId());
			pstmt.setInt(3, vo.getUserId());

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
	public int update(SCommentVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int updatedCount = 0;

		try {
			conn = getConnection();

			String query = "UPDATE scomment													" +
						   "SET content = ?, board_id = ?, user_id = ?, reg_date = SYSDATE,	" +
						   "WHERE comment_id = ?													";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, vo.getContent());
			pstmt.setInt(2, vo.getBoardId());
			pstmt.setInt(3, vo.getUserId());
			pstmt.setInt(4, vo.getCommentId());
			
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
	public int delete(int comment_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deletedCount = 0;

		try {
			conn = getConnection();

			String query = "UPDATE scomment			" +
						   "SET isdeleted = 'true'	" +
						   "WHERE comment_id = ?	";
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, comment_id);

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
	public int getTotalCount(int boardId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalCount = 0;
		
		try {
			conn = getConnection();
			
			String query = "SELECT COUNT(comment_id)	" +
						   "FROM scomment				" +
						   "WHERE board_id = ?			" +
						   "AND isdeleted is NULL		";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, boardId);
			
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

	
	//페이징 및 블럭 테스트를 위한 게시물 저장 메소드 
	public void comment123(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = getConnection();
			sql = "INSERT INTO scomment VALUES VALUES (seq_board_no.nextval, '답변 테스트', 1, 1, SYSDATE, NULL)";
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < 123; i++) {
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
		new SCommentDaoImpl().comment123();
		System.out.println("DB Data Insertion SUCCESS");
	}

}
