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
		 alert("���̵� �Է� ���ּ���.")
		 loopback.j_id.focus;
	 } else if(loopback.j_pw.value == ""){
		 alert("��й�ȣ�� �Է� ���ּ���.")
		 loopback.u_pw.focus;
	 } else if(loopback.j_pw.value != loopback.j_pwchk.value){
		 alert("��й�ȣ��  ��ġ���� �ʽ��ϴ�. (��й�ȣ Ȯ��)")
	 } else if(loopback.j_name.value == ""){
		 alert("�̸��� �Է� ���ּ���.")		 
	 } else if(loopback.j_email1.value == "" ){
		 alert("�̸����� �Է� ���ּ���.")
	 } else if(loopback.j_email2.value == "" ){
		 alert("�̸����� �Է� ���ּ���.")
	 } else {
		 loopback.p_code.value = "join_chk";
		 alert(loopback.p_code.value);
		 loopback.submit();
			}

	 }


 
</script>


<title>ȸ�� ���� ������</title>
</head>
<body>




<h2> ȸ�� ���� ������</h2>

	<form action="user.do" method="post" name=joinForm>
*���̵� : &nbsp;&nbsp;<input type="text" name="j_id" size="10" placeholder="ID"> 
<br> <br> 
*��й�ȣ : <input type="password" name="j_pw" size="15" placeholder="Password">
*��й�ȣ Ȯ�� : <input type="password" name="j_pwchk" value="" size="15" placeholder="Password">
<br> <br>
*�̸� : <input type="password" name="j_name" size="8" placeholder="Name">
		<br> <br>
		<p>
*�̸��� <input type="text" name="j_email1" value="" size="3">
@ <input type="text" name="j_email2" value="" size="10"><br>
		</p>

 &nbsp; &nbsp;&nbsp; <input type="button" value="ȸ������ ����" name="name" onclick="join_chk()">
 <input type="hidden" name="p_code" value="">


</form>



</body>
</html>


