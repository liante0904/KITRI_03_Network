<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

  <script type="text/javascript">

 function addUser() {
	 location.href = "join.jsp";
}
 
 function loginUser() {
	 var loopback = document.loginForm;
	 if(loopback.u_id.value == "")
	 {
		 alert("아이디를 입력 해주세요.")
		 loopback.u_id.focus;
	 } else if(loopback.u_pw.value == ""){
		 alert("비밀번호를 입력 해주세요.")
		 loopback.u_pw.focus;
	 } else {
		 loopback.p_code.value = "login_chk";
		 loopback.submit();
}

	 }

function keyevent() {
		// 키보드 enter키 눌렀을때 동작하기
		if(event.keyCode ==13){
			// TODO : 실행시킬 코드
			loginUser();
		}
	}
  

</script>
<title>ㅋㅋ</title>
</head>
<body>


<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>


<form action="user.do" method="post" name="loginForm">
<p align="center">
 &nbsp;&nbsp;아이디 &nbsp;  :<input type="text" name="u_id" placeholder="ID" onkeydown="keyevent()">
</p>

<p align="center">
  비밀번호  &nbsp;: 		<input type="password" name="u_pw" placeholder="Password" onkeydown="keyevent()">
</p>
<p align="center">
&nbsp;&nbsp;			<input type="button" name="name" value="회원가입" onclick="addUser()">
&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" name="name" value="로그인" onclick="loginUser()">
<br><br><br>

</P>
<input type="hidden" name="p_code" value="">

</form>

</body>
</html>
