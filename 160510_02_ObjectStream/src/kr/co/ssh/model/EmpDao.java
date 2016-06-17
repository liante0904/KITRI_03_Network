package kr.co.ssh.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.ssh.domain.EmpBean;

public class EmpDao {

	// DB연결 및 EmpTable 자료 가져오기



	public List<EmpBean> getEmpAll() {
		try {
			String className = "oracle.jdbc.driver.OracleDriver";
			Class.forName(className);
			// Connection 생성
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "hr", "hr");
			// SQL문 DB에 전달 
			String sql = "select employee_id, first_name, manager_id, job_id, hire_date, salary, commission_pct, department_id from employees";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			//ResultSet 에 있는 값.
			// ResultSet  객체를 Java에서 사용하는
			// EmpBean 객체에 복사
		/*	EmpBean eb = new EmpBean();
			rs.next();
			*/
			List<EmpBean> emp = new ArrayList<EmpBean>();  
			EmpBean eb = null ;
			while(rs.next())
			{
				eb = new EmpBean();
				eb.setEmpno(rs.getInt("employee_id"));
				eb.setEname(rs.getString("First_name"));
				eb.setMgr(rs.getInt("manager_id"));
				eb.setJob(rs.getString("job_id"));
				eb.setHiredate(rs.getString("hire_date"));
				eb.setSal(rs.getInt("salary"));
				eb.setComm(rs.getInt("commission_pct"));
				eb.setDeptno(rs.getInt("department_id"));
				
				
				emp.add(eb);
				int count = 0;
				//System.out.println(emp.get(count));
				count++;
			}
			rs.close(); //DB 데이터를 import한뒤 더이상 사용하지 않으므로 close한다.
			stmt.close();
			con.close();
			
			return emp; // al자료값 반환

		/*	while ( rs.next() ) 
			{
				int emp_id = rs.getInt("employee_id"); // 변수는 db의 데이터형과 맞추어준다.
				String fname = rs.getString("first_name");
				java.sql.Date hire = rs.getDate("hire_date");
				int salary = rs.getInt(4); // 인덱스 번호 순서를 넣어도 가능하다.
				System.out.println(emp_id + ":" + fname + ":" + hire + ":" + salary);
			} 
*/

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;
	}

}
