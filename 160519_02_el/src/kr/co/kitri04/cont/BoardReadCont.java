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
		response.setCharacterEncoding("EUC-KR"); // 웹 브라우저에게 인코딩 값을 세팅
		request.setCharacterEncoding("EUC-KR"); // 한글 인코딩 값 설정 코드
		// COde값 호출 = p_code
		String p_code = request.getParameter("p_code");
		// 1. 글 목록 호출(list)
		if(p_code.equals("list")){
			// DB 접근하여 Database에 전체 값 받아오기
			BoardDao bd = new BoardDao();
			// 쿼리문을 통해 Board Table에 값을 받아오기
			// 전체 Board Table 제목값, //전체 Board Table 글번호, 제목, 작성자, 작성시간, 추천수, 조회수
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
			// DB 가져온 bDTO jsp에 출력
			// board_view.jsp
			request.setAttribute("BDTO", bDto);
			move(request, response, "board_view.jsp");
		} else if(p_code.equals("write")){
			// 글작성 페이지 호출
			move(request, response, "board_input.jsp");
			
		} else if(p_code.equals("modify")){
			// 글 수정할 수 있는 페이지 호출
			String b_id = request.getParameter("p_bid");
			// DB 에 접근해서 b_id에 일치하는 값 받아오기
			BoardDao bDao = new BoardDao();
			BoardDto bDto = bDao.getContents(b_id);
			// DB 에서 가져온 bDto 값을 수정페이지 전달
			request.setAttribute("BDTO", bDto);
			//view page에서 수정페이지로 활용할 내용인지 선택
			//request.setAttribute("MODIFY", "modify");
			move(request, response, "board_input.jsp");
			
		} else if(p_code.equals("delete")){
			// 삭제전 password 묻기
			request.setAttribute("P_BID", request.getParameter("p_bid"));
			String url = "check_pw.jsp";
			move(request, response, url);
		} else if(p_code.equals("chk_pass")){
			// 패스워드 체크 관련 처리
			String p_pass = request.getParameter("p_pass");
			String b_id = request.getParameter("p_bid");
			
			BoardDao bd = new BoardDao();
			BoardDto bDto = bd.getContents(b_id);
			if(p_pass.equals(bDto.getPassword())){
				// Pass 워드 일치
				// 삭제 진행
				String url = "write.do?p_code=delete_ok&p_bid="+b_id;
				move(request,response,url);
			} else {
				// Pass 워드 불일치 
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
