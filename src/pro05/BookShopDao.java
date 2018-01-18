package pro05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pro04.MemberVo;

public class BookShopDao {

	public void insert(BookVo vo) {
		Connection conn = null;
		PreparedStatement psmt = null;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			String query = "";
			query = "insert into bookshop values(seq_bookshop_id.nextval, ?, ?, ?, ?, ?)";

			psmt = conn.prepareStatement(query);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getPubs());
			psmt.setString(3, vo.getPubDate());
			psmt.setString(4, vo.getAuthorName());
			psmt.setInt(5, vo.getStateCode());

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
		BookVo vo = new BookVo();
		if (vo.getStateCode() == 0) {
			System.out.println(vo.getTitle() + " 이(가) 대여됐습니다.");
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
				
				System.out.println("책 제목: " + bookTitle + ", " + "작가: "+ authorName + "," + "대여 유무: " + (stateCode == 0 ? "대여중" : "재고있음"));
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
