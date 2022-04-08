<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#72ECBF">
<h1 align="center" style="color: purple; ">Sales List</h1>

<table width="59%" border="1" align="center" bordercolor="#FF5733" bgcolor="#E7F7F1">
	<tr>
	<%	if(request.getAttribute("checkBoxProductName")!=null){ %>
	<th>Product Name</th>
	<% }%>
	<% if(request.getAttribute("checkBoxCategory")!=null){ %>
	<th>Category</th>
	<% } %>
	
	<% if(request.getAttribute("checkBoxCustomerCity")!=null){ %>
	<th>Customer City</th>
	<%} %>
	
	<% if(request.getAttribute("checkBoxCustomerType")!=null){ %>
	<th>Customer Type</th>
	<% } %>
	
	<% if(request.getAttribute("checkSalesPersonType")!=null){ %>
	<th>SalesPerson Type</th>
	<%} %>
	
	<% if(request.getAttribute("checkSalesPersonExp")!=null){ %>
	<th>Experience</th>
	<% } %>
	<th>Sales Units</th>
	</tr>
	<c:forEach items="${salesList}" var="sale">
	<tr>
	<%if(request.getAttribute("checkBoxProductName")!=null){ %>
	<td>${sale.getProduct().getPRODUCTNAME()}</td>
	<% } %>
	
	<% if(request.getAttribute("checkBoxCategory")!=null){ %>
	<td>${sale.getProduct().getCATEGORY()}</td>
	<% } %>
	
	<% if(request.getAttribute("checkBoxCustomerCity")!=null){ %>
	<td>${sale.getCustomer().getCUSTOMERCITY()}</td>
	<%} %>
	
	<% if(request.getAttribute("checkBoxCustomerType")!=null){ %>
	<td>${sale.getCustomer().getCUSTOMERTYPE()}</td>
	<% } %>
	
	<% if(request.getAttribute("checkSalesPersonType")!=null){ %>
	<td>${sale.getSalesperson().getSALESPERSONTYPE()}</td>
	<%} %>
	
	<% if(request.getAttribute("checkSalesPersonExp")!=null){ %>
	<td>${sale.getSalesperson().getSALESPERSONEXPERIENCE()}</td>
	<% } %>
	
	<td>${sale.getSlaesUnit()}</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>