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
		 alert("���̵� �Է� ���ּ���.")
		 loopback.u_id.focus;
	 } else if(loopback.u_pw.value == ""){
		 alert("��й�ȣ�� �Է� ���ּ���.")
		 loopback.u_pw.focus;
	 } else {
		 loopback.p_code.value = "login_chk";
		 loopback.submit();
}

	 }

function keyevent() {
		// Ű���� enterŰ �������� �����ϱ�
		if(event.keyCode ==13){
			// TODO : �����ų �ڵ�
			loginUser();
		}
	}
  
 
</script>
<title>����</title>
</head>
<body>


<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>


<form action="user.do" method="post" name="loginForm">
<p align="center">
 &nbsp;&nbsp;���̵� &nbsp;  :<input type="text" name="u_id" placeholder="ID" onkeydown="keyevent()">
</p>

<p align="center">
  ��й�ȣ  &nbsp;: 		<input type="password" name="u_pw" placeholder="Password" onkeydown="keyevent()">
</p>
<p align="center">
&nbsp;&nbsp;			<input type="button" name="name" value="ȸ������" onclick="addUser()">
&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" name="name" value="�α���" onclick="loginUser()">
<br><br><br>
<input type="button" name="name" value="������������" onclick="location.href='admin.jsp'">
</P>
<input type="hidden" name="p_code" value="">

</form>

</body>
</html>
