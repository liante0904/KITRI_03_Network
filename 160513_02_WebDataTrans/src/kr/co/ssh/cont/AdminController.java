package kr.co.ssh.cont;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/admin.do")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AdminController실행");
		// adm_id, adm_pass, adm_code(Controller 조작값)
		String adm_code = request.getParameter("adm_code");
		if(adm_code.equals("adm_login")){
			//로그인 버튼 눌렀을 시 처리
			String adm_id = request.getParameter("adm_id");
			String adm_pass = request.getParameter("adm_pass");
			String email = request.getParameter("email1")+"@"+request.getParameter("email2");
			// id값이 admin이 아니라면, 다시 로그인페이지로 리다이렉트
			if (!adm_id.equals("admin")) {
				System.out.println("admin -> admin 아닐 시 실행");
				response.sendRedirect(request.getContextPath()+"/JSP/admin/admin_login_chk.jsp");				
			} else {
				System.out.println("admin -> admin 실행 시");
				RequestDispatcher dis = request.getRequestDispatcher("/JSP/admin/admin_login_chk.jsp");
				request.setAttribute("adm_id", adm_id);
				request.setAttribute("adm_pass", adm_pass);
				dis.forward(request, response);
			}

		} else if(adm_code.equals("adm_login_ok")) {
			System.out.println("로그인 성공 Controller");
			RequestDispatcher dis = request.getRequestDispatcher("/JSP/admin/admin_submit.jsp");
			request.setAttribute("adm_id", request.getParameter("adm_id"));
			request.setAttribute("adm_pass", request.getParameter("adm_pass"));
			dis.forward(request, response);
			
		}

	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
