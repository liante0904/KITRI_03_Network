package kr.co.ssh.model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EmpDao {


	public List<EmpBean> getEmpList() {

		SetupDB sd = new SetupDB();
		ResultSet rs = null;
		String sql = "SELECT EMPLOYEE_ID, FIRST_NAME, SALARY, TO_CHAR(HIRE_DATE, 'YYYY-MM-DD') FROM EMPLOYEES";
		rs = sd.select(sql);
		
		List<EmpBean> list = new ArrayList<EmpBean>();
		try {
			while(rs.next())
			{
				EmpBean eb = new EmpBean();
				eb.setEmployee_id(rs.getInt(1));
				eb.setFirst_name(rs.getString(2));
				eb.setSalary(rs.getDouble(3));
				eb.setHire_date(rs.getString(4));
				list.add(eb);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
			sd.closeDB();
		}
		return list;
	}

	public void setExcel(List<EmpBean> list, String root) {
		
		System.out.println(root);
		// Excel 생성하기
		
		Workbook wb = new XSSFWorkbook();
		 try {
			FileOutputStream fileOut = new FileOutputStream(root + "workbook.xlsx");
			Sheet sh1 = wb.createSheet("TestSheet1");
			
			
			for (int i = 0; i < list.size(); i++) {

				Row row1 = sh1.createRow((short)i);
				Cell cell1 = row1.createCell((short)0);
				Cell cell2 = row1.createCell((short)1);
				Cell cell3 = row1.createCell((short)2);
				Cell cell4 = row1.createCell((short)3);
				cell1.setCellValue(list.get(i).getEmployee_id());
				cell2.setCellValue(list.get(i).getFirst_name());
				cell3.setCellValue(list.get(i).getSalary());
				cell4.setCellValue(list.get(i).getHire_date());
			}
			
			try {
				wb.write(fileOut);
				wb.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 
		}
		
		
	}

