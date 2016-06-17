package kr.co.mproject.cont;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.mproject.model.dao.UserDAO;
import kr.co.mproject.model.dao.UserDTO;

/**
 * Servlet implementation class logincontroller
 */
@WebServlet("/user.do")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO udao = new UserDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String u_id = request.getParameter("u_id"); // 사용자가 입력한 로그인 ID
		//String u_pw = request.getParameter("u_pw"); // PW


		String code = null; // 판별값
		// 주 로그인 컨트롤
		if(request.getParameter("p_code")== null){
			code = "reset";
		} else {
			code = request.getParameter("p_code");
		}
		// 로그인이 안되어있는 상태 처리
		if(code.equals("reset")){
			HttpSession hs = request.getSession();
			if(!(hs.getAttribute("u_id")==null)){
				move(request, response, "login_ok(test).jsp");
			} else {			
				//로그인 화면 출력해주기
				String url="login.jsp";
				response.sendRedirect(url);
			} 
		}else if(code.equals("login_chk")){
			// 로그인 체크
			//login.jsp에서 u_ud, u_pw 가져오기

			String u_id = request.getParameter("u_id");
			String u_pw = request.getParameter("u_pw");

			//받은 데이터 DB와 비교
			UserDAO udao = new UserDAO();
			int chk_id = udao.loginUser(u_id, u_pw);
			if(chk_id ==1){
				// 로그인 성공시 모니터링 페이지로 이동
				//Session 값을 두고 ID값를 세션에 삽입
				HttpSession hs = request.getSession();
				hs.setAttribute("u_id", u_id);
				hs.setAttribute("u_pw", u_pw);

				move(request, response, "login_ok(test).jsp");
				//move(request, response, "monitor.jsp");
			}else if(chk_id==0){
				//로그인 실패 페이지 출력 (다시 로그인 페이지로 돌아감)
				response.sendRedirect("login.jsp");
			}
		} else if(code.equals("join_chk")){
			// 로그인 성공시 모니터링 페이지로 이동
			//Session 값을 두고 ID값를 세션에 삽입

			String j_id = request.getParameter("j_id"); // 회원가입 페이지에서 입력한 값
			String j_pw = request.getParameter("j_pw");
			String j_name = request.getParameter("j_name");
			String j_email1 = request.getParameter("j_email1");
			String j_email2 = request.getParameter("j_email2");
			String j_email = j_email1 + "@" + j_email2;
			UserDTO udto = new UserDTO();
			udto.setU_id(j_id);
			udto.setU_name(j_name);
			udto.setU_pw(j_pw);
			udto.setU_email(j_email);

		int	join_chk =  udao.addUser(udto);
			if(join_chk == 1){
				response.sendRedirect("join_ok(test).jsp");

			} else { // 회원가입 실패시 
				response.sendRedirect("index.jsp");
			}
		} else if(code.equals("modify")){ // 회원 정보 수정클릭후

			HttpSession hs = request.getSession();
			String u_id = (String) hs.getAttribute("u_id");

			UserDAO udao = new UserDAO();
			UserDTO udto = udao.getuser(u_id);

			System.out.println(udto.getU_id());
			request.setAttribute("name", udto.getU_name());
			String email = udto.getU_email();
			System.out.println(email);
			String email1 = email.substring(0, email.lastIndexOf("@"));
			String email2 = email.substring(email.lastIndexOf("@")+1);
			request.setAttribute("email1", email1);
			request.setAttribute("email2", email2);
			move(request, response, "modify.jsp");
		} else if(code.equals("mod_chk")){
			//  수정 정보 입력후 submit
			//  DB반영
			String j_id = request.getParameter("j_id"); // 회원가입 페이지에서 입력한 값
			String j_pw = request.getParameter("j_pw");
			String j_name = request.getParameter("j_name");
			String j_email1 = request.getParameter("j_email1");
			String j_email2 = request.getParameter("j_email2");
			String j_email = j_email1 + "@" + j_email2;
			UserDTO udto = new UserDTO();
			udto.setU_id(j_id);
			udto.setU_name(j_name);
			udto.setU_pw(j_pw);
			udto.setU_email(j_email);

			int join_chk = udao.updateUser(udto);
			if(join_chk == 1){
				response.sendRedirect("login_ok(test).jsp");
			} else if(join_chk==0){
				move(request, response, "modify.jsp");
			}
		} else if(code.equals("logout")){
			HttpSession hs = request.getSession();
			hs.removeAttribute("u_id");
			hs.removeAttribute("u_pw");
			String url="login.jsp";
			response.sendRedirect(url);
		} else if(code.equals("admin_page")){ // 관리자 페이지 이동시 처리
			UserDAO udao = new UserDAO();
			List<UserDTO> unautorizedUserList = udao.getUnauthorizedUser();
			request.setAttribute("AL", unautorizedUserList);
			System.out.println(unautorizedUserList);
			move(request, response, "admin.jsp");
						
		} else if(code.equals("authUser")){
			udao.setAuthAllUser();			
		} else if(code.equals("allshow")){ // 테스트용 조건 
			UserDAO udao = new UserDAO();
			List<UserDTO> allUserList = udao.getallUserList();
			request.setAttribute("AUL", allUserList);
			System.out.println(allUserList);
			move(request, response, "allshow.jsp");		
		} else if(code.equals("delUser")) { // p_code =deluser
			System.out.println("여기 온건가");
			HttpSession hs = request.getSession();
			String del_id = request.getParameter("del_id");
			System.out.println(del_id);
			int delete_chk = 0;
			delete_chk = udao.deleteUser(del_id);
			if(delete_chk == 1){
				System.out.println("회원삭제 성공");
			}
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


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
