<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script type="text/javascript">
function mod_ok() {
	document.modForm.submit();
}
</script>
<title>Modify Employee Page</title>
</head>
<body>
<form action="emp.do" method="post" name="modform">

<p>��� ��ȣ : <input type="text" name="p_empno" value="${eb.employee_id }" readonly="readonly"></p>
<p>��� �̸� : <input type="text" name="p_fname" value="${eb.first_name }"></p>
<p>��� �� : <input type="text" name="p_lname" value="${eb.last_name }"></p>
<p>��� E-mail: <input type="text" name="p_email" value="${eb.email }"></p>
<p>��� PhoneNumber : <input type="text" name="p_pnum" value="${eb.phone_number }"></p>
<p>��� job_id : <input type="text" name="p_jobid" value="${eb.job_id }"></p>
<p>��� �Ի��� : <input type="text" name="p_hiredate" value="${eb.hire_date }"></p>
<p>��� �޿� : <input type="text" name="p_salary" value="${eb.salary }"></p>
<p>��� ��%: <input type="text" name="p_compct" value="${eb.commission_pct }%"></p>
<p>�Ŵ��� id: <input type="text" name="p_mgr" value="${eb.manager_id }"></p>


	<input type="button" value="����" onclick="mod_ok()">
	<input type="hidden" value="emp_update" name="p_code">
	

</form>

</body>
</html>


