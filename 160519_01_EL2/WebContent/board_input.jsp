<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<script type="text/javascript">
	function w_accept(c) {
		var form1 = document.board_form;
		if (c == "mod") {			
			form1.p_code.value = "modify_ok";	
		} else if (c == "add") {			
			form1.p_code.value = "write_ok";
		} else if (c == "re_add") {
			form1.p_code.value = "rep_write_ok";
		}
		form1.submit();
	}
</script>

<title><c:choose>
		<c:when test="${BDTO ne null}">:: �� ���� ::</c:when>
		<c:when test="${REP eq 'reply'}">:: ��� �ۼ� ::</c:when>
		<c:otherwise>:: �� �ۼ� ::</c:otherwise>
	</c:choose></title>
</head>
<body>
<!-- 	<form action="write.do" method="post" name="board_form" enctype="multipart/form-data"> -->
	<form action="write.do" method="post" name="board_form">
		<table border="1">
			<tr>
				<th>������</th>
				<th>�ۼ���</th>
				<th>��й�ȣ</th>
			</tr>
			<tr>
				<td><input type="text" name="title"
					value='<c:if test="${BDTO ne null}">${BDTO.title}</c:if>'></td>
				<td><input type="text" name="writer"
					value='<c:if test="${BDTO ne null}">${BDTO.writer}</c:if>'></td>
				<td><input type="password" name="password"
					value='<c:if test="${BDTO ne null}">${BDTO.password}</c:if>'></td>
			</tr>
			<tr>
				<th colspan="3">÷������</th>
			</tr>
			<tr>
				<td colspan="3"><input type="file" name="pds_link"
					value='<c:if test="${BDTO ne null}">${BDTO.pds_link}</c:if>'>
				</td>
			</tr>
			<tr>
				<th>���� ����</th>
				<td><textarea rows="60" cols="80" name="contents"><c:if
							test="${BDTO ne null}">${BDTO.contents}</c:if></textarea></td>
			</tr>
		</table>
		<c:choose>
			<c:when test="${BDTO ne null}">
				<p>
					<input type="hidden" name="p_bid" value="${BDTO.board_id}">
					<input type="button" value="����" onclick="w_accept('mod')">
				</p>
			</c:when>
			<c:when test="${REP eq 'reply'}">
				<input type="hidden" name="p_bid" value="${P_BID}">				
				<input type="button" value="�ۼ�" onclick="w_accept('re_add')">
			</c:when>			
			<c:otherwise>
				<p>
					<input type="button" value="�ۼ�" onclick="w_accept('add')">
				</p>
			</c:otherwise>
		</c:choose>
		
		<input type="hidden" name="p_code">
		
	</form>
</body>
</html>