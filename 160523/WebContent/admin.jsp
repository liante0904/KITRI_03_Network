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
	alert('회원가입 신청을 승인 하였습니다.');	
}
function removeUser() {
	alert('회원정보를 삭제하였습니다.');	
	
}




</script>
<title>관리자 전용 페이지입니다.</title>
</head>
<body>

<h1>관리자 페이지입니다.</h1>


<input type="submit" name="name" value="회원가입승인" onclick="authUser()">

<input type="submit" name="name" value="회원삭제" onclick="removeUser()">

</body>
</html>


