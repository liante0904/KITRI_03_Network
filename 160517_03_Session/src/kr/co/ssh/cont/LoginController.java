package kr.co.ssh.cont;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ssh.model.EmpDao;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�� �α��� ��Ʈ�ѷ�
		
		String key = null;
		if(request.getParameter("p_code")==null){
			key = "def";
		} else {
			key = request.getParameter("p_code");
		}
		//�α����� �ȵ��ִ� ���� ���� �� ó�� ���
		if(key.equals("def")){

			HttpSession hs = request.getSession();
			if(!(hs.getAttribute("empno")==null)){
				move(request, response, "login_ok.jsp");
			} else {
				//�α��� ȭ�� ������ֱ�
				String url="login_form.jsp";
				move(request, response, url);
			}
		}else if(key.equals("login_chk")){
			//�α��� üũ�ϱ�
			//login_form�������� p_empno, p_name�� ��������
			String empno = request.getParameter("p_empno");
			String ename = request.getParameter("p_ename");

			//DB�� ���� �� �ֵ��� ����
			EmpDao ed = new EmpDao();
			int chk_id = ed.chk_id(empno, ename);
			if(chk_id == 1){
				//�α��� ���� ������ ���
				//Session ���� �ΰ� empno�� ���ǿ� ����
				HttpSession hs = request.getSession();
				hs.setAttribute("empno", empno);
				hs.setAttribute("ename", ename);
				move(request, response, "login_ok.jsp");
			}else if(chk_id==0){
				//�α��� ���� ������ ���
				move(request, response, "login_form.jsp");
			}
		}else if(key.equals("log_out")){
			//�α׾ƿ� ó��
			HttpSession hs = request.getSession();
			//���� ���� ����
			hs.invalidate();
			move(request, response, "login_form.jsp");
		}
		//�α����� �Ǿ��� �ÿ� ó�� ���
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	//Dispatch forward
	private void move(HttpServletRequest request, HttpServletResponse response, String url){
		RequestDispatcher dis = request.getRequestDispatcher(url);
		try {
			dis.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}
