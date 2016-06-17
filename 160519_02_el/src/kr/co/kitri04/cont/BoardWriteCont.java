package kr.co.kitri04.cont;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.kitri04.model.BoardDao;
import kr.co.kitri04.model.BoardDto;

/**
 * Servlet implementation class BoardWriteCont
 */
@WebServlet("/write.do")
public class BoardWriteCont extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DB�� �������� �����Ͽ� �Է��Ҽ� �ִ� ����� ��Ʈ�Ѱ�
		// DML �� ����� ���� �� ��Ʈ�ѷ�
		// Code�� ȣ�� = p_code
		request.setCharacterEncoding("EUC-KR"); // �ѱ� ���ڵ� �� ���� �ڵ�
		response.setCharacterEncoding("EUC-KR"); // �� ���������� ���ڵ� ���� ����


		String p_code = request.getParameter("p_code");
		if(p_code.equals("write_ok")){
			// board_input.jsp ���� �޾ƿ� �Ķ���Ͱ��� �����Ͽ� dao�� ���� ����
			BoardDto bDto = new BoardDto();
			bDto.setTitle(request.getParameter("title"));
			bDto.setWriter(request.getParameter("writer"));
			bDto.setPassword(request.getParameter("password"));
			bDto.setPds_link(request.getParameter("pds_link"));
			bDto.setContents(request.getParameter("contents"));

			//bDto�� DAO�������� DB����

			BoardDao bd = new BoardDao();

			int chk =bd.writeContents(bDto);
			// �� ������� ���� �ֵ��� ����
			if(chk == 1){
				move(request, response, "read.do?p_code=list");
			} else {
				
			}
		} else if(p_code.equals("modify_ok")){
			//�����۾� �Ϸ� ��Ű��
			BoardDto bDto = new BoardDto();
			bDto.setTitle(request.getParameter("title"));
			bDto.setWriter(request.getParameter("writer"));
			bDto.setPassword(request.getParameter("password"));
			bDto.setPds_link(request.getParameter("pds_link"));
			bDto.setContents(request.getParameter("contents"));
			bDto.setBoard_id(Integer.parseInt(request.getParameter("p_bid")));
			// ���� �� �� DAO�� ����
			
			BoardDao bd = new BoardDao();
			int chk =bd.modifyContents(bDto);
			move(request, response, "read.do?p_code=contents&b_id="+bDto.getBoard_id());
			} else if (p_code.equals("delete_ok")) {
				String b_id = request.getParameter("p_bid");
				BoardDao bDao = new BoardDao();
				int chk = bDao.deleteContents(b_id);

				if (chk == 1) {
					move(request, response, "read.do?p_code=list");
				} else {

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


