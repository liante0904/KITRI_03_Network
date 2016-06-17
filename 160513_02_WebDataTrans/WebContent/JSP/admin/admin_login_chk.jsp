<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Login Check</title>
<script type="text/javascript">
function loginChk(a) {
	alert(a);
	if (a == "admin") {
		alert("[" + a + "] 님이 로그인 하셨습니다.");
		// admin_submit.jsp 가 호출되도록 불러오기
		//location.href = "JSP/admin/admin_submit.jsp?adm_id="+"test"+"&adm_pass="+"test";
		var form1 = document.form1;
		var id = '<%=request.getAttribute("adm_id")%>';
		var pass = '<%=request.getAttribute("adm_pass")%>';
			form1.adm_id.value = id;
			form1.adm_pass.value = pass;
			form1.adm_code.value = "adm_login_ok";
			form1.submit();
		} else {
			alert("로그인 하지 못했습니다.");
			location.href = "admin_input.html";
		}
	}
</script>

</head>
<body onload="loginChk('<%=request.getAttribute("adm_id")%>')">
<form action="admin.do" method="post" name="form1">
	<input type="hidden" name="adm_id">
	<input type="hidden" name="adm_pass">
	<input type="hidden" name="adm_code">
</body>
</html>


