<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script type="text/javascript">
	function deluser(i) {
		var l_size = document.getElementsByName("allshow").length;
		var form = null;
		if(l_size <= 1) {
			form = document.allshow;
		} else {
			form = document.allshow[i];
		}
		form.test.value = "bbbb"
		alert("이 함수 실행 됨");
		form.submit();
	}
</script>



<title>관리자 전용 페이지입니다.</title>
</head>
<body>

	<h1>가입된 회원입니다.</h1>

	
		  <br>
		<br>
		<br>
		<table>
			<tr>
				<th>사용자 아이디</th>
				<th>사용자 이름</th>
				<th>사용자 이메일</th>
				<th>체크</th>

			</tr>
			<c:set var="cnt" value="0" />
			<c:forEach items="${AUL }" var="aul">
				<tr>
					<form action="user.do" method="post" name="allshow">
					<input type="hidden" name="p_code" value="delUser">
					<input type="hidden" name="del_id" value="${aul.u_id }">
					
					<td>${aul.u_id}</a></td>
					<td>${aul.u_name}</td>
					<td>${aul.u_email}</td>
					<td><input type="button" name="button" value="회원 삭제"
						onclick="deluser('${cnt}')"></td>
					</form>
				</tr>
				<c:set var="cnt" value="${cnt +1 }" />
			</c:forEach>


		</table>






</body>
</html>


