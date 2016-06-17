<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<script type="text/javascript">
	function write_board(){
		// �� �ۼ��ϴ� �������� �� �� �ֵ��� ��Ʈ�ѷ� ����		
		location.href="read.do?p_code=write";
	}
</script>
<title>:: KITRI Board List ::</title>
</head>
<body>
	<p>KITRI �Խ���</p>
	<table border="1">
		<tr>
			<th>�Խñ۹�ȣ</th>
			<th>�Խñ�����</th>
			<th>�ۼ���</th>
			<th>�ۼ�����</th>
			<th>��ȸ��</th>
			<th>��õ</th>
		</tr>
		<c:forEach items="${BL}" var="blist" >
		<tr>
			<td>${blist.board_id}</td>
			<td><a href="read.do?p_code=contents&b_id=${blist.board_id}">${blist.title}</a></td>
			<td>${blist.writer}</td>
			<td>${blist.wdate}</td>
			<td>${blist.read_cnt}</td>
			<td>${blist.con_like}</td>
		</tr>	
		</c:forEach>	
	</table>
	<p><input type="button" value="�۾���" onclick="write_board()"></p>
</body>
</html>