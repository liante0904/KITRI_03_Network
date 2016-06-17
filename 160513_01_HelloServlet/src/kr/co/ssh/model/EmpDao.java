package kr.co.ssh.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmpDao {
	// Emp자료 접근용 Data Access Object
	private Connection con = null;
	private Statement stmt = null;
	private ResultSet rs = null;


	// DB ID 값 비교하여 접근 하기

	public int chk_id(String empno, String ename) {
		String sql = "SELECT count(*) FROM employees WHERE employee_id="+empno+" AND first_name="+ename+"'" ;

		rs = select(sql);
		int login_chk = 0;
		try {
			if(rs.next()){
				//로그인 체크
				login_chk = rs.getInt(1);
				//1이면 로그인 완료
				//0이면 로그인 실패
			}
			closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return login_chk;
	}
	// Connection 생성 메소드
	private Connection getCon() {
		// Driver 호출
		try {
			Class.forName("oracle.jdbc.OracleDrvier");
			try {
				con  = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "hr", "hr");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	// Statement 생성
	private ResultSet select(String sql){
		try {
			stmt = getCon().createStatement();
			rs = stmt.executeQuery(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*Connection con = getCon();
		con.createStatement();
		 */
		return rs;
	}

	private void closeDB() {
		// Con, Stmt, RS
		if(rs!=null)
		{
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(stmt!=null)
		{
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(con!=null)
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
