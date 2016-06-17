<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

1. text : <%= request.getParameter("p_name") %>
2. pw : <%= request.getParameter("p_pw") %>
3. radio : <%= request.getParameter("p_radio") %>
4. radio2 : <%= request.getParameter("p_radio2") %>
5. chk : <% String[] test = request.getParameterValues("p_chk") %>
		<% for(int i =0; i<test.length; i++){ %>
			<%= test[i] %>
		<% } %> <br>

6. combo: <%= request.getParameter("p_combo") %> <br>
7. tarea: <%= request.getParameter("p_tarea") %> <br>


</body>
</html>