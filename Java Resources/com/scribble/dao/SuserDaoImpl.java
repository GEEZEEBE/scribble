package com.scribble.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.scribble.vo.SuserVo;

public class SuserDaoImpl implements SuserDao{
	
	//DB Connection
	private Connection getConnection() throws SQLException {
	    Connection conn = null;
	    try {
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      String dburl = "jdbc:oracle:thin:@10.211.55.4:1521:xe";
	      conn = DriverManager.getConnection(dburl, "webdb", "1234");
	    } catch (ClassNotFoundException e) {
	      System.err.println("JDBC 드라이버 로드 실패!");
	    }
	    return conn;
	  }
	
	//List
	public List<SuserVo> getList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SuserVo> list = new ArrayList<SuserVo>();

		try {
			conn = getConnection();
			String query = " select * from susers order by user_id desc ";
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int user_id = rs.getInt("user_id");
				String email = rs.getString("email");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String isdeleted = rs.getString("isdeleted");

				SuserVo vo = new SuserVo(user_id, email, name, password, isdeleted);
				list.add(vo);
			}
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
		return list;
	}
	
	//Insert users' info
	public int insert(SuserVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			conn = getConnection();

			String query = "insert into susers values (seq_susers_no.nextval, ?, ?, ?, NULL)";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPassword());

			count = pstmt.executeUpdate();

			System.out.println(count + "건 등록");
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
		return count;
	}

	//Update users' info
	public int update(SuserVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			conn = getConnection();

			if(vo.getPassword() == "") {

				String query = "update susers set name = ? where user_id = ? and isdeleted is NULL ";

				pstmt = conn.prepareStatement(query);

				pstmt.setString(1, vo.getName());
				pstmt.setInt(2, vo.getUser_id());
				
				count = pstmt.executeUpdate();
			} else {

				String query = "update susers set name = ?, password = ? where user_id = ? and isdeleted is NULL ";

				pstmt = conn.prepareStatement(query);

				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getPassword());
				pstmt.setInt(3, vo.getUser_id());
				
				count = pstmt.executeUpdate();
			}
			System.out.println(count + "건 수정");
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
		return count;
	}

	//Delete users'info
	public int delete(int user_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			conn = getConnection();

<<<<<<< HEAD
			String query = "update susers set isdeleted = 'true' where user_id = ? ";
=======
			String query = "update susers set isdeleted = 'true' where user_id ";

>>>>>>> 5ab3834ec001986a47fc8af9acc44124e669e25a
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, user_id);

			count = pstmt.executeUpdate();

			System.out.println(count + "건 삭제");
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
		return count;
	}
	
	//Login
	public SuserVo getUser(String email, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SuserVo vo = null;

		try {
			conn = getConnection();

			String query = "select user_id, name from susers where email = ? and password = ? and isdeleted is NULL ";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, email);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int user_id = rs.getInt("user_id");
				String name = rs.getString("name");

				vo = new SuserVo();
				vo.setUser_id(user_id);
				vo.setName(name);
			}
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
		return vo;
	}	
	//Modify users' info
	public SuserVo getUser(int user_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SuserVo vo = null;

		try {
			conn = getConnection();
			
			String query = "select user_id, email, name from susers where user_id = ? and isdeleted is NULL ";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, user_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				user_id = rs.getInt("user_id");
				String email = rs.getString("email");
				String name = rs.getString("name");

				vo = new SuserVo();
				vo.setUser_id(user_id);
				vo.setEmail(email);
				vo.setName(name);
			}
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
		return vo;
	}
	//Check ID	
	public JSONObject checkId(String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONObject data = null;
		
		try{
			conn = getConnection();
			String query = "select count(*) from susers where email =? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int cnt = rs.getInt(1);
				data = new JSONObject();
				data.put("count", cnt);
			}
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
		return data;
	}

}
