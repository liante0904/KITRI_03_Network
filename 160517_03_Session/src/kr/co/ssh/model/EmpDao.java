package kr.co.ssh.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmpDao {
	private PreparedStatement pStmt;
	private Connection con = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	public int chk_id(String empno, String ename){
		String sql = "SELECT count(*) FROM employees WHERE employee_id="+ empno +" AND first_name='"+ ename +"' ";
		rs = select(sql);
		int login_chk = 0;
		try {
			if(rs.next()){
				login_chk = rs.getInt(1);
			} else return 0;
			closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return login_chk;

	}

	//Emp자료 접근용 Data Access Object
	//DB ID값 비교하여 접근하기
	private Connection getCon(){
		//Driver 호출
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "hr", "hr");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	// 결과 Select 메소드
	private ResultSet select(String sql) {
		try {
			getCon();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}
	
	// 회원정보를 EmpDto 객체에 저장하여 반환하는 메소드
	public EmpDTO getEmp(String empno) {
		String sql = "SELECT employee_id, first_name, last_name, email, phone_number, TO_CHAR(hire_date, 'YYYY-MM-DD'), job_id, salary, commission_pct * 100, manager_id FROM employees WHERE employee_id = ?";
		EmpDTO ed = null;
		try {
			getCon();
			pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, Integer.parseInt(empno));
			rs = pStmt.executeQuery();
			if(rs.next()) {
				ed = new EmpDTO();
				ed.setEmployee_id(rs.getInt(1));
				ed.setFirst_name(rs.getString(2));
				ed.setLast_name(rs.getString(3));
				ed.setEmail(rs.getString(4));
				ed.setPhone_number(rs.getString(5));
				ed.setHire_date(rs.getString(6));
				ed.setJob_id(rs.getString(7));
				ed.setSalary(rs.getInt(8));
				ed.setCommission_pct(rs.getInt(9));
				ed.setManager_id(rs.getInt(10));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return ed;
	}
	private void closeDB(){
		//Con, Stmt, Rs
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
	
	// 수정된 객체를 DB에 반영하는 메소드
	public int modEmp(EmpDTO ed) {
		int cnt = 0;
		String sql = null;
		
		try {
			
			// 이전 값과 비교하여 수정된 값만 update 하기 위한 if문 코드
			EmpDTO oldEd = getEmp(Integer.toString(ed.getEmployee_id()));
			getCon();
			sql = "UPDATE employees SET ";
			boolean currentAdd = false;
			
			if(!oldEd.getFirst_name().equals(ed.getFirst_name())) {
				sql += "first_name = '" + ed.getFirst_name() + "'";
				currentAdd = true;
			}
			
			if(!oldEd.getLast_name().equals(ed.getLast_name())) {
				if(currentAdd)
					sql += ", ";
				
				 sql += "last_name = '" + ed.getLast_name() + "'";
				 currentAdd = true;
			}
			
			if(!oldEd.getEmail().equals(ed.getEmail())) {
				if(currentAdd)
					sql += ", ";
				
				sql += "email = '" + ed.getEmail() + "'";
				currentAdd = true;
			}
			
			if(!oldEd.getPhone_number().equals(ed.getPhone_number())) {
				if(currentAdd)
					sql += ", ";
				
				sql += "phone_number = '" + ed.getPhone_number() +"'";
				currentAdd = true;
			}
			
			if(!oldEd.getHire_date().equals(ed.getHire_date())) {
				if(currentAdd)
					sql += ", ";
				
				sql += "hire_date = TO_DATE('" + ed.getHire_date() +"', 'YYYY-MM-DD')";
				currentAdd = true;
			}
			
			if(!oldEd.getJob_id().equals(ed.getJob_id())) {
				if(currentAdd)
					sql += ", ";
				
				sql += "job_id = '" + ed.getJob_id() +"'";
				currentAdd = true;
			}
			
			if(oldEd.getSalary() != ed.getSalary()) {
				if(currentAdd)
					sql += ", ";
				
				sql += "salary = " + ed.getSalary();
				currentAdd = true;
			}
			
			if(oldEd.getCommission_pct() != ed.getCommission_pct()) {
				if(currentAdd)
					sql += ", ";
				
				if(ed.getCommission_pct() == 0)
					// DB에서 commission_pct는 0이 아니라 NULL로 저장된다.
					sql += "commission_pct = NULL";
				else
					sql += "commission_pct = " + (double)ed.getCommission_pct() / 100;
				currentAdd = true;
			}
			
			if(oldEd.getManager_id() != ed.getManager_id()) {
				if(currentAdd)
					sql += ", ";
				
				if(ed.getManager_id() == 0)
					// 매니저 아이디가 0이면 관리자가 존재하지 않는 경우이므로 DB에서는 외래키 무결성 제약조건에 의해 NULL로 처리되어야 한다.
					sql += "manager_id = NULL";
				else
					sql += "manager_id = " + ed.getManager_id();
				currentAdd = true;
			}
			
			sql += " WHERE employee_id = " + ed.getEmployee_id();
			
			if(currentAdd) {
				// currentAdd == true 이면 한개의 속성값은 수정되었다는 이야기이므로 이 때만 update를 수행한다.
				stmt = con.createStatement();
				cnt = stmt.executeUpdate(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return cnt; 
		
	}

	// empno에 해당하는 이름만 반환하는 메소드
	public String get_name(String empno) {
		String ename = null;
		try {
			getCon();
			stmt = con.createStatement();
			String sql = "SELECT first_name FROM employees WHERE employee_id = " + empno;
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				ename = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return ename;
	}
}
