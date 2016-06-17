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
	 var loopback = document.adminForm;
	 loopback.p_code.value = "authUser";
	 alert(loopback.p_code.value);
	 loopback.submit();
	alert('회원가입 신청을 승인 하였습니다.');	
	location.href="user.do?p_code=login_chk";
}

</script>



<title>관리자 전용 페이지입니다.</title>
</head>
<body>

<h1>가입 승인 대기 회원입니다.</h1>

	<form action="user.do" method="post" name="adminForm">
	 <input type="hidden" name="p_code" value="">
	<br><br><br><table>
<tr>
<th>사용자 아이디 </th>
<th>사용자 이름 </th>
<th>사용자 이메일 </th>

</tr>
<c:forEach items="${AUL }" var="aul">
<tr>
<td>${aul.u_id}</td>
<td>${aul.u_name}</td>
<td>${aul.u_email}</td>
</tr>
</c:forEach>


</table>
	
	
<input type="button" name="name" value="회원삭제" onclick="authUser()">



</form>

</body>
</html>


