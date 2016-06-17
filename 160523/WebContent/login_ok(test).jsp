<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script type="text/javascript">

function mod_chk() {
	 var loopback = document.loginokForm;
	 loopback.p_code.value = "mod_chk";
	 alert(loopback.p_code.value);
	 loopback.submit();
}

</script>
<title>로그인 완료페이지</title>
</head>
<body>


<% HttpSession hs =request.getSession(); %>

<p> 로그인에 성공했습니다.</p>
<p> 사용자 번호: <%= hs.getAttribute("u_id") %> <br>
<p> 사용자 이름: <%= hs.getAttribute("u_pw") %> <br>
 
<form name = "loginokForm" method = "POST" action="user.do">

	<input name = "p_code" type="hidden">
	<input name = "mod_emp" value= "정보수정" type="button" onclick="mod_chk()">
	<input name = "logout_bt" value= "로그아웃" type="button" onclick=>
		
</form>


</body>
</html>


