<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">


<script type="text/javascript">

function authUser() {
	 var loopback = document.adminForm;
	 loopback.p_code.value = "authUser";
	 alert(loopback.p_code.value);
	 loopback.submit();
	alert('ȸ������ ��û�� ���� �Ͽ����ϴ�.');	
	location.href="user.do?p_code=login_chk";
}

</script>



<title>������ ���� �������Դϴ�.</title>
</head>
<body>

<h1>���� ���� ��� ȸ���Դϴ�.</h1>

	<form action="user.do" method="post" name="adminForm">
	 <input type="hidden" name="p_code" value="">
	<br><br><br><table>
<tr>
<th>����� ���̵� </th>
<th>����� �̸� </th>
<th>����� �̸��� </th>

</tr>
<c:forEach items="${AUL }" var="aul">
<tr>
<td>${aul.u_id}</td>
<td>${aul.u_name}</td>
<td>${aul.u_email}</td>
</tr>
</c:forEach>


</table>
	
	
<input type="button" name="name" value="ȸ������" onclick="authUser()">



</form>

</body>
</html>


