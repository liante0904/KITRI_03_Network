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
       
    /**
     * @see HttpServlet#HttpServlet()
     */


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 주 로그인 컨트롤러 
		String key = null;
		if(request.getParameter("p_code").equals(""))
		{
			key = "def";
		} else {
			key = request.getParameter("p_code"); 
		}

		// 로그인이 안되있는 상태 였을때 처리 방법
		if(key.equals("def"))
		{ // 로그인 화면 출력해주기 
			// login_form에서부터 p_empno, p_name값 가져오기
			String url = "login_form.jsp";
			move(request, response, url);
		} else if(key.equals("login_chk")){
			String empno = request.getParameter("p_empno");
			String ename = request.getParameter("p_ename");
			//DB와 비교할수 있도록 구성
			EmpDao ed = new EmpDao();
			int chk_id = ed.chk_id(empno, ename);
			if(chk_id ==1)
			{
				// 로그인 성공 페이지
				// Session 값을 두고 empno를 세션에 삽입
				HttpSession hs = request.getSession();
				hs.setAttribute("empno", empno);
				request.setAttribute("ename", ename);
				move(request, response, "login_ok.jsp");
			} else if(chk_id ==0)
			{
				//로그인 실패 페이지
			move(request, response, "login_form.jsp");
			}
		} else if(key.equals("log_out")){
			//로그아웃 처리
			HttpSession hs = request.getSession();
			//세션 삭제
			hs.invalidate();
			move(request, response, "login_form.jsp");
		}
		// 로그인이 되었을 시에 처리 방법
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	//dispatch Forward(?)
	private void move(HttpServletRequest request, HttpServletResponse response, String url) {
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
