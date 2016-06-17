package kr.co.mproject.model.dao;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class UserDAO {

	public Connection con = null;
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
		
		String sql = "insert into USERS (U_ID, U_NAME, U_PW, U_EMAIL, AUTH_YN) VALUES("+ "'"+udto.getU_id() + "','"+udto.getU_name()+"','"+udto.getU_pw()+"','" + udto.getU_email()+"','"+ "N"+ "')" ;
		int join_chk = 0;
		try {
			join_chk = insert(sql);
		} finally {
			closeDB();
		}
		return join_chk;
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


	public void updateUser(UserDTO edto) {
		String sql = "UPDATE employees SET first_name = ?, last_name = ?, phone_number = ?, email = ?, job_id = ?, hire_date = TO_DATE(?,'YYYY-MM-DD'), salary = ?, commission_pct = ?, manager_id = ? WHERE employee_id = ?";
		
		int chk = 0;
		try {
			pstmt = getCon().prepareStatement(sql);
			pstmt.setString(1, edto.getU_id());
			pstmt.setString(2, edto.getU_name());
			pstmt.setString(3, edto.getU_pw());
		
			chk = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public Connection getCon(){
		//Driver »£√‚
		try {

			Class.forName("core.log.jdbc.driver.OracleDriver");
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "kitri04", "kitri04");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
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







}

