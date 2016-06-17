<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script type="text/javascript">

function write_board() {
	//글 작성하는 페이지로 갈수 있도록
	
	location.href="read.do?p_code=write";
	
}

</script>
<title>KITRI Board List</title>
</head>
<body>


<p> KITRI Board List </p>
<table border="1" bordercolor="blue">
<tr>
<th>게시글 번호 </th>
<th>게시글 제목 </th>
<th>게시글 작성자 </th>
<th>작성일자 </th>
<th>조회수 </th>
<th>추천 </th>
<th>작성일자 </th>
</tr>
<c:forEach items="${BL }" var="blist">

<tr>
<td>${blist.board_id}</td>
<td><a href="read.do?p_code=contents&b_id=${blist.board_id}"> ${blist.title} </td> </a>
<td>${blist.title}</td>
<td>${blist.writer}</td>
<td>${blist.wdate}</td>
<td>${blist.read_cnt}</td>
<td>${blist.cont_like}</td>
</tr>

</c:forEach>


</table>

<p><input type="button" value="글쓰기" onclick="write_board()"></p>

</body>
</html>


