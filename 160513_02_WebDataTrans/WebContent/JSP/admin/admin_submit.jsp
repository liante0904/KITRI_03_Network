<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>::: Admin Submit</title>
</head>
<body>
	ID : <%= request.getAttribute("adm_id") %> <br>
	PASS : <%= request.getAttribute("adm_pass") %><br>
	email : <%= request.getAttribute("email") %><br>
<br><br>	ID : ${adm_id} <br>
	PASS : ${param.adm_pass} <br>
	email : ${param.email}

</body>
</html>
