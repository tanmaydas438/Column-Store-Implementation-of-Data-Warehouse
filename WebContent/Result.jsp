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
<h1>Sales List</h1>

<table width="59%" border="1">
	<tr>
	<th>Product Name</th>
	<th>Category</th>
	<th>Customer City</th>
	<th>Customer Type</th>
	<th>SalesPerson Type</th>
	<th>Experience</th>
	<th>Sales Units</th>
	</tr>
	<c:forEach items="${salesList}" var="sale">
	<tr>
	<% if(4==4){ %>
	<td>${sale.getProduct().getPRODUCTNAME()}</td>
	<% } %>
	<td>${sale.getProduct().getCATEGORY()}</td>
	<td>${sale.getCustomer().getCUSTOMERCITY()}</td>
	<td>${sale.getCustomer().getCUSTOMERTYPE()}</td>
	<td>${sale.getSalesperson().getSALESPERSONTYPE()}</td>
	<td>${sale.getSalesperson().getSALESPERSONEXPERIENCE()}</td>
	
	<td>${sale.getSlaesUnit()}</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>