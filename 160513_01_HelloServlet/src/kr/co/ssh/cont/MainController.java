package kr.co.ssh.cont;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class MainController extends HttpServlet{

/**
	 * 
	 */
	private static final long serialVersionUID = -6234485675338763074L;

@Override
public void init() throws ServletException {
	// ���� �ʱ�ȭ �޼ҵ� (���۽� 1���� ����)
	System.out.println("���� 1ȸ ����");
	super.init();
}

@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Client�� ��û������ �����Ǵ� �޼ҵ�
	System.out.println("Service ����");
	PrintWriter out = resp.getWriter();
		
	out.println("<html><head></head><body>"+ req.getRemoteHost() +"</body></html>");

	System.out.println(req.getRemoteHost()+ "����");

}


@Override
	public void destroy() {
		// Servlet���� �ÿ� �ѹ� ����
		// WAS�� ���� �� ��
		System.out.println("Servlet ����");
		super.destroy();
	}

}

