<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script type="text/javascript">

function mod_chk() {
	 var loopback = document.modForm;
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
		 loopback.p_code.value = "mod_chk";
		 alert(loopback.p_code.value);
		 loopback.submit();
			}
	 }

	 
</script>
<title>ȸ�� ���� ���� ������</title>
</head>
<body>
<form action="user.do" method="post" name="modForm">




<h2> ȸ�� ���� ���� ������ </h2>

*���̵� : &nbsp;&nbsp;<input type="text" name="j_id" size="10" value="<%= session.getAttribute("u_id") %>" readonly="readonly" ><br> <br> 
*��й�ȣ : <input type="password" name="j_pw" size="15" placeholder="Password">
*��й�ȣ Ȯ�� : <input type="password" name="j_pwchk" value="" size="15" placeholder="Password"><br> <br>
*�̸� : <input type="text" name="j_name" size="8" value="${name }" readonly="readonly" >	<br> <br>

		<p>
		
*�̸��� <input type="text" name="j_email1" value="${email1 }" size="3">
@ <input type="text" name="j_email2" value="${email2 }" size="10"><br>
		</p>

 &nbsp; &nbsp;&nbsp; 
<input type="button" value="ȸ������ ����" name="name" onclick="mod_chk()">


<input type="hidden" name="p_code" value="">
	

</form>

</body>
</html>