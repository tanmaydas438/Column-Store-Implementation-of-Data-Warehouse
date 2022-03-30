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
<h1>Product Name</h1>
<select name="productname" id="productname">
    <c:forEach items="${productNames}" var="productName">
        <option value="${productName}">${productName}</option>
    </c:forEach>
    </select>
    
<h1>Category</h1>
<select name="category" id="category">
    <c:forEach items="${categories}" var="category">
        <option value="${category}">${category}</option>
    </c:forEach>
    </select>
    
<h1>Customer City</h1>
<select name="customercity" id="customercity">
    <c:forEach items="${customerCities}" var="city">
        <option value="${city}">${city}</option>
    </c:forEach>
    </select>
    
<h1>Category</h1>
<select name="customertype" id="customertype">
    <c:forEach items="${customerTypes}" var="type">
        <option value="${type}">${type}</option>
    </c:forEach>
    </select>
    
<h1>SalersPerson Type</h1>
<select name="salespersontype" id="salespersontype">
    <c:forEach items="${salesPersonTypes}" var="type">
        <option value="${type}">${type}</option>
    </c:forEach>
    </select>
     
                             
</body>
</html>