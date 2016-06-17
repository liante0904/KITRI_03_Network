package kr.co.kitri04.cont;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import kr.co.kitri04.model.BoardDao;
import kr.co.kitri04.model.BoardDto;

/**
 * Servlet implementation class BoardWriteCont
 */
@WebServlet("/write.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class BoardWriteCont extends HttpServlet {

	private static final long serialVersionUID = 5754226193259900056L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet ����");
		System.out.println(request.getRemoteAddr());
		// DB�� ���� ���� �����Ͽ� �Է��� �� �ִ� ����� ��Ʈ�ѷ�
		// DML �� ���� �� ������ ��Ʈ�ѷ�
		// Code�� ȣ�� = p_code
		request.setCharacterEncoding("EUC-KR");
		response.setCharacterEncoding("EUC-KR");
		String p_code = request.getParameter("p_code");
		if (p_code.equals("write_ok")) {
			// Create a factory for disk-based file items

			// board_input.jsp ���� �޾� �� �Ķ���Ͱ���
			// �����Ͽ� Dao�� ���� ����
			BoardDto bDto = new BoardDto();
			bDto.setTitle((String) request.getAttribute("title"));
			bDto.setWriter((String) request.getAttribute("writer"));
			bDto.setPassword((String) request.getAttribute("password"));
			bDto.setPds_link((String) request.getAttribute("pds_link"));
			System.out.println((String) request.getAttribute("pds_link"));
			bDto.setContents((String) request.getAttribute("contents"));

			// bDto�� DAO ������ �� DB ����
			BoardDao bd = new BoardDao();
			int chk = bd.writeContents(bDto);
			// �� ������� �� �� �ֵ��� ����
			if (chk == 1) {
				response.sendRedirect("read.do?p_code=list");
			} else {

			}
		} else if (p_code.equals("modify_ok"))

		{
			// ���� �۾� �Ϸ� ��Ű��
			BoardDto bDto = new BoardDto();
			bDto.setBoard_id(Integer.parseInt(request.getParameter("p_bid")));
			bDto.setTitle(request.getParameter("title"));
			bDto.setWriter(request.getParameter("writer"));
			bDto.setPassword(request.getParameter("password"));
			bDto.setPds_link(request.getParameter("pds_link"));
			bDto.setContents(request.getParameter("contents"));

			// ���� �� �� Dao �� ����
			BoardDao bd = new BoardDao();
			int chk = bd.modifyContents(bDto);
			if (chk == 1) {
				response.sendRedirect("read.do?p_code=contents&b_id=" + bDto.getBoard_id());
			} else {

			}

		} else if (p_code.equals("delete_ok")) {
			String b_id = request.getParameter("p_bid");
			BoardDao bDao = new BoardDao();
			int chk = bDao.delContents(b_id);

			if (chk == 1) {
				response.sendRedirect("read.do?p_code=list");
			} else {

			}
		} else if (p_code.equals("rep_write_ok")) {
			String b_id = request.getParameter("p_bid");

			BoardDto bDto = new BoardDto();
			bDto.setBoard_id(Integer.parseInt(b_id));
			bDto.setTitle(request.getParameter("title"));
			bDto.setWriter(request.getParameter("writer"));
			bDto.setPassword(request.getParameter("password"));
			bDto.setPds_link(request.getParameter("pds_link_temp"));
			System.out.println("request.pdsLink : " + bDto.getPds_link());
			bDto.setContents(request.getParameter("contents"));

			BoardDao bDao = new BoardDao();
			int chk = bDao.rep_writeContents(bDto);
			if (chk == 1) {
				response.sendRedirect("read.do?p_code=list");
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
		request.setCharacterEncoding("EUC-KR");
		response.setCharacterEncoding("EUC-KR");
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		String p_code = "def";
		if (isMultipart) {
			System.out.println("Multipart ����");
			p_code = "write_pds_ok";
		} else {
			p_code = request.getParameter("p_code");
			// �Ϲ� Form�� ��� doGet ����
			// doGet(request, response);
		}
		if (p_code.equals("write_pds_ok")) {
			System.out.println("Write_ok_Post ����");

			System.out.println("Multipart boolean :" + isMultipart);

			if (isMultipart) {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				BoardDto bDto = new BoardDto();

				try {
					List<FileItem> items = upload.parseRequest(request);
					Iterator<FileItem> iterator = items.iterator();
					System.out.println("HasNext�� :" + iterator.hasNext());
					while (iterator.hasNext()) {
						FileItem item = iterator.next();
						System.out.println("Item �� : " + item);
						if (!item.isFormField()) {
							//File ó���� �� ���
							String fileName = item.getName();
							int len = fileName.length();
							int last = fileName.lastIndexOf("\\");
							// var dif = len - last;
							// var ext = str.substr(last + 1, dif); //���ϸ� ���� (
							// ����)
							String only_filename = fileName.substring(last + 1, len); // ���ϸ�
							System.out.println("FileName : " + only_filename);
							System.out.println(item.getFieldName());
							bDto.setPds_link(only_filename);

							String root = getServletContext().getRealPath("/");
							File path = new File(root + "/uploads");
							// File path = new File("C://uploads");
							if (!path.exists()) {
								boolean status = path.mkdirs();
								System.out.println(status);
							}

							// File uploadedFile = new File(fileName);
							String file = transMD5(fileName);
							bDto.setPds_link_temp(file);
							File uploadedFile = new File(path + "/" + file);
							System.out.println("uploadedFile : " + uploadedFile.getAbsolutePath());
							item.write(uploadedFile);
						} else {
							// Form �Ϲ� ����̸�
							// getFieldName() -> �Ķ���� �̸�, getString() -> �Ķ���� ������ ��
							request.setAttribute(item.getFieldName(), item.getString("EUC-KR"));
						}

					}
				} catch (FileUploadException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				bDto.setTitle((String) request.getAttribute("title"));
				bDto.setWriter((String) request.getAttribute("writer"));
				bDto.setPassword((String) request.getAttribute("password"));
				bDto.setContents((String) request.getAttribute("contents"));
				
				// bDto�� DAO ������ �� DB ����
				BoardDao bd = new BoardDao();
				int chk = 0;
				if(request.getAttribute("p_code").equals("write_ok")){
					System.out.println("�Ϲ� PDS ����");
					chk = bd.writeContents(bDto);
				} else if(request.getAttribute("p_code").equals("rep_write_ok")){
					bDto.setBoard_id(Integer.parseInt((String)request.getAttribute("p_bid")));
					System.out.println("��� PDS ����");
					chk = bd.rep_writeContents(bDto);
				}
				
				// �� ������� �� �� �ֵ��� ����
				if (chk == 1) {
					response.sendRedirect("read.do?p_code=list");
				} else {

				}

			} else {
				// Multi ��Ʈ�� �ƴϸ�
				doGet(request, response);
			}
		}
	}

	public String transMD5(String str) {
		String MD5 = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte byteData[] = md.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			MD5 = sb.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			MD5 = null;
		}
		return MD5;
	}

}
