<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<script type="text/javascript">
function logout(){
	
	
	}
	
function empmod() {
	// action ��ü
	
	
}

</script>

<title>Insert title here</title>
</head>

<body>

<% HttpSession hs =request.getSession(); %>

<p> ȸ�����Կ� �����߽��ϴ�.</p>
<p> ����� ���̵�: <%= hs.getAttribute("j_id") %> <br>
<p> ����� ��й�ȣ: <%= hs.getAttribute("j_pw") %> <br>
<p> ����� ��й�ȣ Ȯ��: <%= hs.getAttribute("j_pwchk") %> <br>
<p> ����� �̸�: <%= hs.getAttribute("j_name") %> <br>
<p> ����� �̸���: <%= hs.getAttribute("j_email1") %> @ <%= hs.getAttribute("j_email2") %> <br>
 
<form name = "logoutForm" method = "POST" action="join.do">

	<input name = "p_code" type="hidden">
	<input name = "mod_emp" value= "��������" type="button" onclick="empmod()">
	<input name = "logout_bt" value= "�α׾ƿ�" type="button" onclick="logout()">
		
</form>

</body>
</html>


