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
<title>로그인 완료페이지</title>
</head>
<body>


<% HttpSession hs =request.getSession(); %>
<h1>로그인에 성공했습니다.</h1>
<p> 사용자 번호: <%= hs.getAttribute("u_id") %> <br>
<p> 사용자 이름: <%= hs.getAttribute("u_pw") %> <br>
 
<form name = "loginokForm" method = "POST" action="user.do">

	<input name = "p_code" type="hidden">
	<input name = "mod_emp" value= "정보수정" type="button" onclick="modify()">
	<input name = "logout_bt" value= "로그아웃" type="button" onclick="logout()">
<br><br><br>
	<h3> 관리자 전용</h3>
	<input type="button" name="name" value="관리자페이지" onclick="admin()">
<input type="button" name="name" value="모든 회원 보기" onclick="allshow()">
		
</form>


</body>
</html>


