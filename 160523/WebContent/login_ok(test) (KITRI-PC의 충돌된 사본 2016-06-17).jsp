<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script type="text/javascript">

function modify() {
	 var loopback = document.loginokForm;
	 loopback.p_code.value = "modify";
	 alert(loopback.p_code.value);
	 loopback.submit();
}

function logout() {
	 var loopback = document.loginokForm;
	 loopback.p_code.value = "logout";
	 alert(loopback.p_code.value);
	 loopback.submit();
}

function admin() {
	 var loopback = document.loginokForm;
	 loopback.p_code.value = "admin_page";
	 loopback.submit();
}
function allshow() {
	 var loopback = document.loginokForm;
	 loopback.p_code.value = "allshow";
	 loopback.submit();
}
</script>
<title>�α��� �Ϸ�������</title>
</head>
<body>


<% HttpSession hs =request.getSession(); %>
<h1>�α��ο� �����߽��ϴ�.</h1>
<p> ����� ��ȣ: <%= hs.getAttribute("u_id") %> <br>
<p> ����� �̸�: <%= hs.getAttribute("u_pw") %> <br>
 
<form name = "loginokForm" method = "POST" action="user.do">

	<input name = "p_code" type="hidden">
	<input name = "mod_emp" value= "��������" type="button" onclick="modify()">
	<input name = "logout_bt" value= "�α׾ƿ�" type="button" onclick="logout()">
<br><br><br>
	<h3> ������ ����</h3>
	<input type="button" name="name" value="������������" onclick="admin()">
<input type="button" name="name" value="��� ȸ�� ����" onclick="allshow()">
		
</form>


</body>
</html>


