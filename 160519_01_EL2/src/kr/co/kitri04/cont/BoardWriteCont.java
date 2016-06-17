package kr.co.kitri04.cont;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.co.kitri04.model.BoardDao;
import kr.co.kitri04.model.BoardDto;

/**
 * Servlet implementation class BoardWriteCont
 */
@WebServlet("/write.do")
@MultipartConfig(fileSizeThreshold=1024*1024*2, maxFileSize=1024*1024*10,maxRequestSize=1024*1024*50) 
public class BoardWriteCont extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String SAVE_DIR = "uploadFiles";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getRemoteAddr());
		// DB에 실제 값을 전달하여 입력할 수 있는 기능의 컨트롤러
		// DML 문 실행 시 접근할 컨트롤러
		// Code값 호출 = p_code
		//
		request.setCharacterEncoding("EUC-KR");
		response.setCharacterEncoding("EUC-KR");
		String p_code = request.getParameter("p_code");
		if (p_code.equals("write_ok")) {
			// gets absolute path of the web application
	        String appPath = request.getServletContext().getRealPath("");
	        // constructs path of the directory to save uploaded file
	        String savePath = appPath + File.separator + SAVE_DIR;
	         
	        // creates the save directory if it does not exists
//	        File fileSaveDir = new File(savePath);
//	        if (!fileSaveDir.exists()) {
//	            fileSaveDir.mkdir();
//	        }
//	         
//	        for (Part part : request.getParts()) {
//	            String fileName = extractFileName(part);
//	            part.write(savePath + File.separator + fileName);
//	        }
			// board_input.jsp 에서 받아 올 파라미터값을
			// 세팅하여 Dao를 통해 실행
			BoardDto bDto = new BoardDto();
			bDto.setTitle(request.getParameter("title"));
			bDto.setWriter(request.getParameter("writer"));
			bDto.setPassword(request.getParameter("password"));
			bDto.setPds_link(request.getParameter("pds_link"));
			bDto.setContents(request.getParameter("contents"));

			// bDto를 DAO 전달한 후 DB 전송
			BoardDao bd = new BoardDao();
			int chk = bd.writeContents(bDto);
			// 글 목록으로 갈 수 있도록 구성
			if (chk == 1) {
				move(request, response, "read.do?p_code=list");
			} else {

			}
		} else if (p_code.equals("modify_ok")) {
			// 수정 작업 완료 시키기
			BoardDto bDto = new BoardDto();
			bDto.setBoard_id(Integer.parseInt(request.getParameter("p_bid")));
			bDto.setTitle(request.getParameter("title"));
			bDto.setWriter(request.getParameter("writer"));
			bDto.setPassword(request.getParameter("password"));
			bDto.setPds_link(request.getParameter("pds_link"));
			bDto.setContents(request.getParameter("contents"));

			// 수정 될 값 Dao 에 전달
			BoardDao bd = new BoardDao();
			int chk = bd.modifyContents(bDto);
			if (chk == 1) {
				move(request, response, "read.do?p_code=contents&b_id=" + bDto.getBoard_id());
			} else {

			}

		} else if (p_code.equals("delete_ok")) {
			String b_id = request.getParameter("p_bid");
			BoardDao bDao = new BoardDao();
			int chk = bDao.delContents(b_id);

			if (chk == 1) {
				move(request, response, "read.do?p_code=list");
			} else {

			}
		} else if (p_code.equals("rep_write_ok")){
			String b_id = request.getParameter("p_bid");
			
			BoardDto bDto = new BoardDto();
			bDto.setBoard_id(Integer.parseInt(b_id));
			bDto.setTitle(request.getParameter("title"));
			bDto.setWriter(request.getParameter("writer"));
			bDto.setPassword(request.getParameter("password"));
			bDto.setPds_link(request.getParameter("pds_link"));
			bDto.setContents(request.getParameter("contents"));
			
			BoardDao bDao = new BoardDao();
			int chk = bDao.rep_writeContents(bDto);
			if (chk == 1) {
				move(request, response, "read.do?p_code=list");
			} else {

			}
			
			
			
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
	
	private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }

}
