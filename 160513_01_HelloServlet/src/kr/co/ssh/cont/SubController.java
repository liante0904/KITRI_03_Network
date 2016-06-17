package kr.co.ssh.cont;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/subcont.do")
public class SubController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4351109403968641598L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
			//req.getRemoteAddr(); // 서브 컨트롤러
		PrintWriter out = resp.getWriter();

		Date now = new Date();
		out.println("<html>");
		out.println("<head><title>현재시간</title></head>");
		out.println("<body>");
		out.println("서블릿 생성 현재 시간:");
		out.println(now.toString());
		out.println("</body>");
		out.println("</html>");
		out.close();
		
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}


}
