<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">


<script type="text/javascript">

function authUser() {
	alert('ȸ������ ��û�� ���� �Ͽ����ϴ�.');	
}
function removeUser() {
	alert('ȸ�������� �����Ͽ����ϴ�.');	
	
}




</script>
<title>������ ���� �������Դϴ�.</title>
</head>
<body>

<h1>������ �������Դϴ�.</h1>


<input type="submit" name="name" value="ȸ�����Խ���" onclick="authUser()">

<input type="submit" name="name" value="ȸ������" onclick="removeUser()">

</body>
</html>


