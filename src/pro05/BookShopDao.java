package pro05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BookShopDao {

	public void insert(BookVo vo) {
		Connection conn = null;
		PreparedStatement psmt = null;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			String query = "";
			query = "insert into bookshop(id,title,author_name,state_code) VALUES(seq_bookshop_id.NEXTVAL,?,?,?)";

			psmt = conn.prepareStatement(query);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getAuthorName());
			psmt.setInt(3, 1);

			int count = psmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : " + e);
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error : " + e);
			}
		}

	}

	public void rent(int num) {
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			String query = "update bookshop set state_code = 0 " + 
						   "where id = ?";

			psmt = conn.prepareStatement(query);
			psmt.setInt(1, num);
			
			int count = psmt.executeUpdate();
			rentMsg(num);

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : " + e);
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error : " + e);
			}
		}


	}
	
	public void rentMsg(int num) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			String query = "select title from bookshop where id = ?";

			psmt = conn.prepareStatement(query);
			psmt.setInt(1, num);
			rs = psmt.executeQuery();
			while (rs.next()) {
				String title = rs.getString("title");
				System.out.println(title+"(가) 대여 됐습니다");
			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : " + e);
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error : " + e);
			}
		}
	}
	
	public List<BookVo> printAllBook() {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<BookVo> bookList = new ArrayList<BookVo>();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			String query = "select id, title, author_name, state_code from bookshop";

			psmt = conn.prepareStatement(query);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				
				BookVo vo = new BookVo();
				
				int bookId = rs.getInt("id");
				String bookTitle = rs.getString("title");
				String authorName = rs.getString("author_name");
				int stateCode = rs.getInt("state_code");
				
				vo.setId(bookId);
				vo.setTitle(bookTitle);
				vo.setAuthorName(authorName);
				vo.setStateCode(stateCode);
				
				bookList.add(vo);
				
				System.out.println(bookId+" " + "책 제목: " + bookTitle + ", " + "작가: "+ authorName + "," + "대여 유무: " + (stateCode == 0 ? "대여중" : "재고있음"));
			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : " + e);
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error : " + e);
			}
		}

		return bookList;
		
	
	}

}