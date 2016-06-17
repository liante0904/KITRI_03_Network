<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<script type="text/javascript">
	
	
	function b_modify(){
		var v_form = document.form_view;
		// ������ �� �ֵ��� ����
		// p_code = modify;
		v_form.p_code.value="modify";
		// action = read.do
		v_form.action="read.do";
		v_form.submit();
	}
	
	function b_delete(){
		var v_form = document.form_view;
		// p_code = modify;
		v_form.p_code.value="delete";
		// action = read.do
		v_form.action="read.do";
		v_form.submit();
	}
	
	function b_reply(){
		var v_form = document.form_view;
		// p_code = modify;
		v_form.p_code.value="reply";
		// action = read.do
		v_form.action="read.do";
		v_form.submit();
	}

</script>
<title>:: �� ���� ::</title>
</head>
<body>
<table border="1">
	<tr>
		<th>�۹�ȣ</th>
		<th>������</th>
		<th>�ۼ���</th>
		<th>��ȸ��</th>
		<th>�ۼ�����</th>
	</tr>
	<tr>
		<td>${BDTO.board_id}</td>
		<td>${BDTO.title}</td>
		<td>${BDTO.writer}</td>
		<td>${BDTO.read_cnt}</td>
		<td>${BDTO.wdate}</td>		
	</tr>
	<tr>
		<th colspan="3">��õ</th>
		<th colspan="2">����</th>
	</tr>
	<tr>
		<td colspan="3">${BDTO.con_like}</td>
		<td colspan="2">${BDTO.con_unlike}</td>
	</tr>
	<tr>
		<th colspan="5">÷������</th>		
	</tr>
	<tr>
		<td colspan="5">		
			<c:choose>
			 	<c:when test="${BDTO.pds_link eq null}">÷�� ���� ����</c:when>
				<c:otherwise>${BDTO.pds_link}</c:otherwise>
			</c:choose>
		</td>		
	</tr>
	<tr>
		<th>���� ����</th>
		<td><textarea rows="60" cols="80" readonly="readonly">${BDTO.contents}</textarea> </td>
	</tr>
</table>
<form name="form_view" method="post">
<p><input type="button" value="��۴ޱ�" onclick="b_reply()"> </p>
<p><input type="button" value="�����ϱ�" onclick="b_modify()"> </p>
<p><input type="button" value="�����ϱ�" onclick="b_delete()"> </p>
<input type="hidden" name="p_code">
<input type="hidden" name="p_bid" value="${BDTO.board_id}">
</form>
</body>
</html>