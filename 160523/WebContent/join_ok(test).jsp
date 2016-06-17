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
	// action 교체
	
}
</script>

<title>Insert title here</title>
</head>

<body>

<% HttpSession hs =request.getSession(); %>

<p> 회원가입에 성공했습니다.</p>
<p> 사용자 아이디: <%= hs.getAttribute("j_id") %> <br>
<p> 사용자 비밀번호: <%= hs.getAttribute("j_pw") %> <br>
<p> 사용자 비밀번호 확인: <%= hs.getAttribute("j_pwchk") %> <br>
<p> 사용자 이름: <%= hs.getAttribute("j_name") %> <br>
<p> 사용자 이메일: <%= hs.getAttribute("j_email1") %> @ <%= hs.getAttribute("j_email2") %> <br>
 
<form name = "logoutForm" method = "POST" action="join.do">

	<input name = "p_code" type="hidden">
	<input name = "mod_emp" value= "정보수정" type="button" onclick="empmod()">
	<input name = "logout_bt" value= "로그아웃" type="button" onclick="logout()">
		
</form>

</body>
</html>


