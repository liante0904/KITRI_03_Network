package kr.co.ssh.cont;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ssh.model.EmpBean;
import kr.co.ssh.model.EmpDao;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/main.do")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		EmpBean eb = new EmpBean();
		EmpDao ed = new EmpDao();
		List<EmpBean> list = ed.getEmpList(); 

		// Excel�� �ش� list�� �ִ� ���� ����
		String root = getServletContext().getRealPath("/");
		ed.setExcel(list,root);
		File path = new File(root + "workbook.xlsx");
		byte b[] = new byte[4096];
		response.reset();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename = " + "workbook.xlsx");
		FileInputStream in = new FileInputStream(path);
		ServletOutputStream out2= response.getOutputStream();
		int numRead;
		// ����Ʈ �迭 b�� 0�� ���� numRead�� ���� ���Ͽ� ���� (���)
		while ((numRead = in.read(b, 0, b.length)) != -1) {
			out2.write(b, 0, numRead);
		}

		out2.flush();
		out2.close();
		in.close();
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
