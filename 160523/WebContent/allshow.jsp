<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script type="text/javascript">
	function deluser(i) {
		var l_size = document.getElementsByName("allshow").length;
		var form = null;
		if(l_size <= 1) {
			form = document.allshow;
		} else {
			form = document.allshow[i];
		}
		form.test.value = "bbbb"
		alert("�� �Լ� ���� ��");
		form.submit();
	}
</script>



<title>������ ���� �������Դϴ�.</title>
</head>
<body>

	<h1>���Ե� ȸ���Դϴ�.</h1>

	
		  <br>
		<br>
		<br>
		<table>
			<tr>
				<th>����� ���̵�</th>
				<th>����� �̸�</th>
				<th>����� �̸���</th>
				<th>üũ</th>

			</tr>
			<c:set var="cnt" value="0" />
			<c:forEach items="${AUL }" var="aul">
				<tr>
					<form action="user.do" method="post" name="allshow">
					<input type="hidden" name="p_code" value="delUser">
					<input type="hidden" name="del_id" value="${aul.u_id }">
					
					<td>${aul.u_id}</a></td>
					<td>${aul.u_name}</td>
					<td>${aul.u_email}</td>
					<td><input type="button" name="button" value="ȸ�� ����"
						onclick="deluser('${cnt}')"></td>
					</form>
				</tr>
				<c:set var="cnt" value="${cnt +1 }" />
			</c:forEach>


		</table>






</body>
</html>


