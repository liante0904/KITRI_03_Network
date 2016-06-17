package kr.co.mproject.model.dao;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





public class UserDAO {
	public Connection con;
	public Statement stmt = null;
	public ResultSet rs = null;
	public PreparedStatement pstmt = null;
	public HttpServletRequest request= null;
	public HttpServletResponse response = null;

	public int loginUser(String u_id, String u_pw) {
		// TODO Auto-generated method stub
		String sql = "SELECT count(*) FROM USERS WHERE U_ID ='"+ u_id +"' AND U_PW='"+ u_pw +"' ";
		rs =  select(sql);
		int login_chk = 0;
		try {
			if(rs.next()){
				login_chk = rs.getInt(1);
				System.out.println(login_chk);
			} else return 0;
			closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return login_chk;
	}



	public int addUser(UserDTO udto) {
		SetupDB db = new SetupDB();
		String sql = "INSERT INTO USERS (U_ID, U_NAME, U_PW, U_EMAIL, AUTH_YN)"+ 
				" VALUES(?,?,?,?,'N')";
		int cnt = 0;
		try {
			pstmt = db.getCon().prepareStatement(sql);
			pstmt.setString(1, udto.getU_id());
			pstmt.setString(2, udto.getU_name());
			pstmt.setString(3, udto.getU_pw());
			pstmt.setString(4, udto.getU_email());	
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		} return cnt; 
	} 

	public int updateUser(UserDTO udto) {
		String sql = "UPDATE USERS SET U_NAME = ?, U_PW = ?, U_EMAIL = ?, AUTH_YN = 'Y' WHERE U_ID = ?";
		int update_chk = 0;
		try {
			pstmt = getCon().prepareStatement(sql);
			pstmt.setString(1, udto.getU_name());
			pstmt.setString(2, udto.getU_pw());
			pstmt.setString(3, udto.getU_email());
			pstmt.setString(4, udto.getU_id());
			update_chk = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();	
		}
		return update_chk;
	}


	private int insert(String sql) {
		int row = 0;
		try {
			stmt = getCon().createStatement();
			row = stmt.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}



	public Connection getCon(){
		Connection db = null;
		//Driver 호출
		try {
			Class.forName("core.log.jdbc.driver.OracleDriver");
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			db = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "kitri04", "kitri04");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return db;
	}


	public UserDTO getuser(String u_id) {
		String sql = "SELECT u_id, u_name, u_email " + " FROM USERS WHERE U_ID=?";
		System.out.println(u_id);
		UserDTO udto = new UserDTO();
		try {
			pstmt = getCon().prepareStatement(sql);
			pstmt.setString(1, u_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				udto.setU_id(rs.getString(1));
				udto.setU_name(rs.getString(2));
				udto.setU_email(rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return udto;
	}

	// 관리자 페이지 이동시, AUTH_YN = N인 유저 출력을 위한 method (DB 검색)
	public List<UserDTO> getUnauthorizedUser() { 
		SetupDB sd = new SetupDB();
		String sql = "SELECT u_id, u_name, u_email " + " FROM USERS WHERE AUTH_YN= 'N' ";
		ResultSet rs = sd.select(sql);
		List<UserDTO> unautorizedUserList = new ArrayList<UserDTO>();

		try {
			while(rs.next()){
				UserDTO udto = new UserDTO();
				udto.setU_id(rs.getString(1));
				udto.setU_name(rs.getString(2));
				udto.setU_email(rs.getString(3));
				unautorizedUserList.add(udto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeDB();
		}
		return unautorizedUserList;
	}

	//가입 승인 대기 회원 , 일괄 승인 method
	public int setAuthAllUser() { 
		String sql = "UPDATE USERS SET AUTH_YN = 'Y' WHERE AUTH_YN = 'N' ";
		int auth_chk = 0;
		try {
			pstmt = getCon().prepareStatement(sql);
			auth_chk = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();	
		}
		return auth_chk;

	}
	public ResultSet select(String sql){
		try {
			stmt = getCon().createStatement();
			rs = stmt.executeQuery(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}
	public void closeDB(){
		//Con, Stmt, Rs
		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	private void move(HttpServletRequest req, HttpServletResponse resp, String url){
		RequestDispatcher rd = req.getRequestDispatcher(url);
		try {
			rd.forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 테스트 메소드
	public List<UserDTO> getallUserList() {
		SetupDB sd = new SetupDB();
		String sql = "SELECT u_id, u_name, u_email FROM USERS WHERE u_id IS NOT NULL ";
		ResultSet rs = sd.select(sql);
		List<UserDTO> allUserList = new ArrayList<UserDTO>();

		try {
			while(rs.next()){
				UserDTO udto = new UserDTO();
				udto.setU_id(rs.getString(1));
				udto.setU_name(rs.getString(2));
				udto.setU_email(rs.getString(3));
				allUserList.add(udto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeDB();
		}
		return allUserList;
	}


	// 회원 account 삭제 (DB DELETE)
	public int deleteUser(String del_id) {
		SetupDB sd = new SetupDB();
		String sql = "DELETE FROM USERS WHERE U_ID =" + "'"+ del_id +"'";
		int delete_chk = 0;
		try {
			pstmt = getCon().prepareStatement(sql);

			delete_chk = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();	
		}
		return delete_chk;
	}























}

