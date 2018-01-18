package pro03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pro03 {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			// JDBC 드라이버(Oracle)로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "hr", "hr ");

			// sql문 준비 / 바인딩 / 실행
			String query = " select e.employee_id, e.last_name, e.email, j.job_title, d.department_name, l.city "
					+ " from employees e, departments d, jobs j, locations l "
					+ " where e.department_id = d.department_id "
					+ " and d.location_id = l.location_id "
					+ " and e.job_id = j.job_id"
					+ " and j.job_id like "
					+ " 'PU_CLERK' ";

			psmt = conn.prepareStatement(query);
			rs = psmt.executeQuery();

			// 결과처리
			System.out.println("employee_id | last_name | email | job_title | department_name | city");
			while (rs.next()) {
				int employeeId = rs.getInt("employee_id");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				String jobTitle = rs.getString("job_title");
				String departmentName = rs.getString("department_name");
				String city = rs.getString("city");
				System.out.println(employeeId + "            " + lastName + "     " + email + "     " + jobTitle +
									"     " + departmentName + "    " + city);
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

	}


