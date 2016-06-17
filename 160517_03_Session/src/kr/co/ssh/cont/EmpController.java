package kr.co.ssh.cont;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ssh.model.EmpDTO;
import kr.co.ssh.model.EmpDao;

/**
 * Servlet implementation class EmpController
 */
@WebServlet("/emp.do")
public class EmpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmpDao empdao = new EmpDao();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. Session -> empno
		HttpSession hs = request.getSession();
		if(hs.getAttribute("empno") == null)
		{//세션에 empno가 없다 = 로그인 페이지로 전달
			response.sendRedirect("/login.do");
		} else {
			//세션이 존재할 경우 (로그인 된 경우)
			//code 값에 따라 Controller가 실행 될수 있게 구성
			String code = "def";
			if(request.getParameter("p_code") == null || request.getParameter("p_code").equals("")){
				code = "def";
			} else {
					code=request.getParameter("p_code");
				
			}


			if (code.equals("def")) {
				// 회원 정보 수정 할수 있도록 함
				System.out.println("EmpController 실행 -> 회원정보 수정 Controller실행");
				String empno = hs.getAttribute("empno").toString();
				// 정보 수정을 위해 사원번호가 일치하는 컬럼을 update하게 해야한다.
				EmpDTO ed = empdao.getEmp(empno); 
				RequestDispatcher rd = request.getRequestDispatcher("modify_emp.jsp");
				request.setAttribute("ed", ed);
				rd.forward(request, response);
			} else if(code.equals("emp_update"))
			{// 자료받아서 update
				EmpDTO ed = new EmpDTO();
				ed.setEmployee_id(Integer.parseInt(request.getParameter("p_empno")));
				ed.setFirst_name(request.getParameter("p_fname"));
				ed.setLast_name(request.getParameter("p_lname"));
				ed.setPhone_number(request.getParameter("p_pnum"));
				ed.setEmail(request.getParameter("p_email"));
				ed.setHire_date(request.getParameter("p_hiredate"));
				ed.setSalary(Integer.parseInt(request.getParameter("p_salary")));
				ed.setCommission_pct(Integer.parseInt(request.getParameter("p_compct")));
				ed.setManager_id(Integer.parseInt(request.getParameter("p_mgr")));
				ed.setJob_id(request.getParameter("p_jobid"));

				//변경된 ED값 세팅
				// DAO => update문 쿼리 실행

				EmpDao edao = new EmpDao();
				int cnt = edao.modEmp(ed);
				// 몇건의 데이터가 수정되었습니다.
				request.setAttribute("cnt", cnt);
				RequestDispatcher dis = request.getRequestDispatcher("emp_mod_ok.jsp");
				dis.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("test");
		doGet(request, response);
	}

}
