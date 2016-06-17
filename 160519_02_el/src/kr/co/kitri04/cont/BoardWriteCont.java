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
		// DB에 실제값을 전달하여 입력할수 있는 기능의 컨트롤값
		// DML 문 실행시 접근 할 컨트롤러
		// Code값 호출 = p_code
		request.setCharacterEncoding("EUC-KR"); // 한글 인코딩 값 설정 코드
		response.setCharacterEncoding("EUC-KR"); // 웹 브라우저에게 인코딩 값을 세팅


		String p_code = request.getParameter("p_code");
		if(p_code.equals("write_ok")){
			// board_input.jsp 에서 받아올 파라미터값을 세팅하여 dao를 통해 실행
			BoardDto bDto = new BoardDto();
			bDto.setTitle(request.getParameter("title"));
			bDto.setWriter(request.getParameter("writer"));
			bDto.setPassword(request.getParameter("password"));
			bDto.setPds_link(request.getParameter("pds_link"));
			bDto.setContents(request.getParameter("contents"));

			//bDto를 DAO전달한후 DB전송

			BoardDao bd = new BoardDao();

			int chk =bd.writeContents(bDto);
			// 글 목록으로 갈수 있도록 구성
			if(chk == 1){
				move(request, response, "read.do?p_code=list");
			} else {
				
			}
		} else if(p_code.equals("modify_ok")){
			//수정작업 완료 시키기
			BoardDto bDto = new BoardDto();
			bDto.setTitle(request.getParameter("title"));
			bDto.setWriter(request.getParameter("writer"));
			bDto.setPassword(request.getParameter("password"));
			bDto.setPds_link(request.getParameter("pds_link"));
			bDto.setContents(request.getParameter("contents"));
			bDto.setBoard_id(Integer.parseInt(request.getParameter("p_bid")));
			// 수정 될 값 DAO에 전달
			
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


