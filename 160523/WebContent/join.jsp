<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

  <script type="text/javascript">

 
 function join_chk() {
	 var loopback = document.joinForm;
	 if(loopback.j_id.value == "")
	 {
		 alert("아이디를 입력 해주세요.")
		 loopback.j_id.focus;
	 } else if(loopback.j_pw.value == ""){
		 alert("비밀번호를 입력 해주세요.")
		 loopback.u_pw.focus;
	 } else if(loopback.j_pw.value != loopback.j_pwchk.value){
		 alert("비밀번호가  일치하지 않습니다. (비밀번호 확인)")
	 } else if(loopback.j_name.value == ""){
		 alert("이름을 입력 해주세요.")		 
	 } else if(loopback.j_email1.value == "" ){
		 alert("이메일을 입력 해주세요.")
	 } else if(loopback.j_email2.value == "" ){
		 alert("이메일을 입력 해주세요.")
	 } else {
		 loopback.p_code.value = "join_chk";
		 alert(loopback.p_code.value);
		 loopback.submit();
			}

	 }


 
</script>


<title>회원 가입 페이지</title>
</head>
<body>




<h2> 회원 가입 페이지</h2>

	<form action="user.do" method="post" name=joinForm>
*아이디 : &nbsp;&nbsp;<input type="text" name="j_id" size="10" placeholder="ID"> 
<br> <br> 
*비밀번호 : <input type="password" name="j_pw" size="15" placeholder="Password">
*비밀번호 확인 : <input type="password" name="j_pwchk" value="" size="15" placeholder="Password">
<br> <br>
*이름 : <input type="password" name="j_name" size="8" placeholder="Name">
		<br> <br>
		<p>
*이메일 <input type="text" name="j_email1" value="" size="3">
@ <input type="text" name="j_email2" value="" size="10"><br>
		</p>

 &nbsp; &nbsp;&nbsp; <input type="button" value="회원가입 제출" name="name" onclick="join_chk()">
 <input type="hidden" name="p_code" value="">


</form>



</body>
</html>


