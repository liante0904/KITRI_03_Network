<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script type="text/javascript">

	function w_accept(c) {
		
		var form1 = document.board_form;
		
		if (c == "mod") {
			form1.p_code.value = "modify_ok";
			form1.submit();
		} else if (c == "add") {
			form1.p_code.value = "write_ok";
			form1.submit();
		} 
		form1.submit();
	}
	
</script>
<title>
<c:choose>
	<c:when test="${BDTO ne null }"> :::Modify Page:::</c:when>
	<c:otherwise>:::Write Page:::</c:otherwise>
</c:choose>

</title>
</head>

<body>
<form action="write.do" method="post" name="board_form">
<table border="1" >

<tr>
<th>글제목</th>
<th>작성자</th>
<th>비밀 번호</th>
</tr>

<tr>
	<td><input type="text" name="title" value='<c:if test="${BDTO ne null}">${BDTO.title}</c:if>'> </td>
	<td><input type="text" name="writer" value='<c:if test="${BDTO ne null}">${BDTO.writer}</c:if>'> </td>
	<td><input type="password" name="password" value='<c:if test="${BDTO ne null}">${BDTO.password}</c:if>'> </td>
	<td>${BDTO.wdate}</td>
</tr>



<tr>
<th colspan="3">첨부파일</th>
</tr>

<tr>
<td colspan="3">
<input type="file" name="pds_link"> 
<c:choose>
<c:when test="${BDTO.pds_link eq null}">첨부파일 없음</c:when>
<c:otherwise>${BDTO.pds_link}</c:otherwise>
</c:choose>
</td>
</tr>

<tr>
<th>본문 내용</th>
<td><textarea rows="100" cols="200" name="contents" ><c:if test="${BDTO ne null}">${BDTO.password}</c:if> >${BDTO.contents}</textarea></td>
</tr>

</table>


<c:choose>
	<c:when test="${BDTO ne null}">
	<p>
	<input type="hidden" name="p_bid" value="${BDTO.board_id}">
	<input type="button" name="board_id" value="수정" onclick="w_accept('mod')"> </p>
	</c:when>
	
	<c:otherwise>
	<p><input type="button" name="board_id" value="작성" onclick="w_accept('add')"> </p>
	</c:otherwise>
</c:choose>

<input type="hidden" name="p_code" > 

</form>

</body>
</html>


