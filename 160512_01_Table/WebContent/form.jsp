<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>


	function bt1click() {
		alert("버튼 클릭");

	}

	function formchk() {
	// input에서 입력할 form이 빈칸없도록 구성
	var form  =	document.frm1;
	alert(form.p_name.value);
	if (form.p_name.value =="") {
		form.p_name.focus();
		alert("p_name 값을 입력하시오.")
	} else if (form.p_pw.value == "") {
		alert("p_password 값을 입력하시오.")
		form.p_pw.focus();
		form.p_pw.value= "test";
		
</body>
</html>