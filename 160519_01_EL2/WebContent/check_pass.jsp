<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<script type="text/javascript">
	function chk_pass_script(){
		alert("test");
		var chk_form = document.chk_pass;
		chk_form.submit();
	}
</script>
<title>:: Check Password ::</title>
</head>
<body>
<form name="chk_pass" action="read.do" method="POST">
�н����� : <input type="password" name="p_pass">
<br>
<input type="hidden" value="chk_pass" name="p_code">
<input type="hidden" value="${P_BID}" name="p_bid">
<input type="button" value="Ȯ��" onclick="chk_pass_script()"/>
</form>
</body>
</html>