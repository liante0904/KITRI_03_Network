package kr.co.kitri04.cont;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.kitri04.model.BoardDao;
import kr.co.kitri04.model.BoardDto;

/**
 * Servlet implementation class BoardReadCont
 */
@WebServlet("/read.do")
public class BoardReadCont extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("EUC-KR"); // �� ���������� ���ڵ� ���� ����
		request.setCharacterEncoding("EUC-KR"); // �ѱ� ���ڵ� �� ���� �ڵ�
		// COde�� ȣ�� = p_code
		String p_code = request.getParameter("p_code");
		// 1. �� ��� ȣ��(list)
		if(p_code.equals("list")){
			// DB �����Ͽ� Database�� ��ü �� �޾ƿ���
			BoardDao bd = new BoardDao();
			// �������� ���� Board Table�� ���� �޾ƿ���
			// ��ü Board Table ����, //��ü Board Table �۹�ȣ, ����, �ۼ���, �ۼ��ð�, ��õ��, ��ȸ��
			List<BoardDto> board_list = bd.getList();
			String url  = "board_list.jsp";
			request.setAttribute("BL", board_list);
			System.out.println(board_list);
			move(request, response, url);
			
		} else if(p_code.equals("contents")){
			String b_id = request.getParameter("b_id");
			BoardDto bDto= null;
			BoardDao bd = new BoardDao();
			bDto = bd.getContents(b_id);
			// DB ������ bDTO jsp�� ���
			// board_view.jsp
			request.setAttribute("BDTO", bDto);
			move(request, response, "board_view.jsp");
		} else if(p_code.equals("write")){
			// ���ۼ� ������ ȣ��
			move(request, response, "board_input.jsp");
			
		} else if(p_code.equals("modify")){
			// �� ������ �� �ִ� ������ ȣ��
			String b_id = request.getParameter("p_bid");
			// DB �� �����ؼ� b_id�� ��ġ�ϴ� �� �޾ƿ���
			BoardDao bDao = new BoardDao();
			BoardDto bDto = bDao.getContents(b_id);
			// DB ���� ������ bDto ���� ���������� ����
			request.setAttribute("BDTO", bDto);
			//view page���� ������������ Ȱ���� �������� ����
			//request.setAttribute("MODIFY", "modify");
			move(request, response, "board_input.jsp");
			
		} else if(p_code.equals("delete")){
			// ������ password ����
			request.setAttribute("P_BID", request.getParameter("p_bid"));
			String url = "check_pw.jsp";
			move(request, response, url);
		} else if(p_code.equals("chk_pass")){
			// �н����� üũ ���� ó��
			String p_pass = request.getParameter("p_pass");
			String b_id = request.getParameter("p_bid");
			
			BoardDao bd = new BoardDao();
			BoardDto bDto = bd.getContents(b_id);
			if(p_pass.equals(bDto.getPassword())){
				// Pass ���� ��ġ
				// ���� ����
				String url = "write.do?p_code=delete_ok&p_bid="+b_id;
				move(request,response,url);
			} else {
				// Pass ���� ����ġ 
				String url = "read.do?p_code=contents&b_id="+b_id;
				move(request,response,url);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void move(HttpServletRequest req, HttpServletResponse resp, String url) {
		RequestDispatcher rd = req.getRequestDispatcher(url);
		try {
			rd.forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
