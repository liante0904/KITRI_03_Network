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

	// DB���� �� EmpTable �ڷ� ��������



	public List<EmpBean> getEmpAll() {
		try {
			String className = "oracle.jdbc.driver.OracleDriver";
			Class.forName(className);
			// Connection ����
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "hr", "hr");
			// SQL�� DB�� ���� 
			String sql = "select employee_id, first_name, manager_id, job_id, hire_date, salary, commission_pct, department_id from employees";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			//ResultSet �� �ִ� ��.
			// ResultSet  ��ü�� Java���� ����ϴ�
			// EmpBean ��ü�� ����
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
			rs.close(); //DB �����͸� import�ѵ� ���̻� ������� �����Ƿ� close�Ѵ�.
			stmt.close();
			con.close();
			
			return emp; // al�ڷᰪ ��ȯ

		/*	while ( rs.next() ) 
			{
				int emp_id = rs.getInt("employee_id"); // ������ db�� ���������� ���߾��ش�.
				String fname = rs.getString("first_name");
				java.sql.Date hire = rs.getDate("hire_date");
				int salary = rs.getInt(4); // �ε��� ��ȣ ������ �־ �����ϴ�.
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
