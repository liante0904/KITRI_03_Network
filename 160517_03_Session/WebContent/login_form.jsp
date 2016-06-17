<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script type="text/javascript">
	function login() 
	{
		// Controller
	// p_code = 특정값이 들어가도록 입력
	// p_code = login_chk
	// 빈값이 들어갔을시 처리
	if(document.loginForm.p_empno.value == "")
	{
		alert("사원번호를 입력 해주세요.")
		document.loginForm.p_empno.focus();
		
	} else if(document.loginForm.p_ename.value == "")
	{
		alert("사원이름를 입력 해주세요.")
		document.loginForm.p_ename.focus();
	} else {
		document.loginForm.p_code.value = "login_chk";
		document.loginForm.submit();
		
	}
	
	}

	function join() 
	{	// Controller
		// p_code = user_reg
		
	}
	function keyevent() {
		// 키보드 enter키 눌렀을때 동작하기
		if(event.keyCode ==13){
			// TODO : 실행시킬 코드
			login();
		}
	}
</script>
<title>Login page</title>
</head>
<body>

	<form action="login.do" method="post" name="loginForm">
	<p>
		사원 번호 : <input name="p_empno" type="text" onkeydown="keyevent()">
	</p>
	<p>
		사원 이름 : <input name="p_ename" type="text" onkeydown="keyevent()">
	</p>
	<input name="p_code" type="hidden">
	<p>
		<input name="p_bt_login" type="button" value="로그인" onclick="login()">
	</p>
	<p>
		<input name="p_bt_reg" type="button" value="회원가입" onclick="join()">
	</p>
</form>
</body>
</html>


