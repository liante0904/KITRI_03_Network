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

	}
	}