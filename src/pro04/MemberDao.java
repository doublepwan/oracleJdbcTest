package pro04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MemberDao {
	// MemberDao 를 작성합니다.
	
	public void insertMember(MemberVo vo) {
		Connection conn = null;
		PreparedStatement psmt = null;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			String query = "";
			query = "insert into member values(seq_member_id.nextval, ?, ?, ?, ?)";

			psmt = conn.prepareStatement(query);
			psmt.setString(1, vo.getName());
			psmt.setString(2, vo.getEmail());
			psmt.setString(3, vo.getPassword());
			psmt.setString(4, vo.getGender());

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

	public void updatePassword(MemberVo vo) {
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			String query = "update member set password = ? " + 
						   "where email = ?";

			psmt = conn.prepareStatement(query);
			psmt.setString(1, vo.getPassword());
			psmt.setString(2, vo.getEmail());

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

	public void deleteMember(String email) {
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			String query = "delete from member where email = ?";

			psmt = conn.prepareStatement(query);
			psmt.setString(1, email);

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

	
	public List<MemberVo> getListAll() {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<MemberVo> memberList = new ArrayList<MemberVo>();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			String query = "select id, name, email, password, gender from member";

			psmt = conn.prepareStatement(query);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				
				MemberVo vo = new MemberVo();
				
				int memberId = rs.getInt("id");
				String memberName = rs.getString("name");
				String memberEmail = rs.getString("email");
				String memberPass = rs.getString("password");
				String memberGender = rs.getString("gender");
				
				vo.setId(memberId);
				vo.setName(memberName);
				vo.setEmail(memberEmail);
				vo.setPassword(memberPass);
				vo.setGender(memberGender);
				
				memberList.add(vo);
				
				System.out.println(memberId + " | " + memberName + " | " + memberEmail + " | " + memberPass + " | " + memberGender);
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

		return memberList;
		
	}
}
