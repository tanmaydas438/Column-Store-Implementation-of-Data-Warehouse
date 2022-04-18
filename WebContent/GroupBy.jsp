<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table width="59%" border="1" align="center" bordercolor="#FF5733" bgcolor="#E7F7F1">
	<tr>
	<%	if(!request.getAttribute("attr1").equals("")){ %>
	<th>${attr1}</th>
	<% ;}%>
	<%	if(!request.getAttribute("attr2").equals("")){ %>
	<th>${attr2}</th>
	<% ;}%>
	
	<%	if(!request.getAttribute("attr3").equals("")){ %>
	<th>${attr3}</th>
	<% ;}%>
	
	<th>Sales Units</th>
	</tr>
	<c:forEach items="${nodeValueList}" var="nodeValue">
	<tr>
	
	<%if(!request.getAttribute("attr1").equals("")){ %>
	<td>${nodeValue.getAttr1()}</td>
	<% } %>
	
	
	
	<%if(!request.getAttribute("attr2").equals("")){ %>
	<td>${nodeValue.getAttr2()}</td>
	<% } %>
	
	<%if(!request.getAttribute("attr3").equals("")){ %>
	<td>${nodeValue.getAttr3()}</td>
	<% } %>
	
	<td>${nodeValue.getValue()}</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>