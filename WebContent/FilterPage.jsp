<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<style>

* {
     margin: 0px;
     padding: 0px;
     /* width: 100%; */
     overflow: hidden;

}
.container{
     overflow: hidden;
     /* width: 800%; */
     white-space: nowrap;
}
.container > div {
     width: 250px;
     height: 100px;
     float: left;
     margin: 0 4px 0 0;
     background-color:rgb(167, 228, 225);
}  
.bgrnd {
background-color: rgb(167, 228, 225);
 /* background-image: url("Filter.jpg"); */
 background-repeat: no-repeat;
 background-position: center;

 height=100%;
}
.text-center {
  text-align: center;
  align: center;
  margin-top : 10;
  /* height: 20%; */
  left: 50%;
  margin-bottom: 10;
  
}
</style>
<body class="bgrnd">
<form method="post" action="filterFromDat">

<div>
    <h1 style="text-align: center;">Slice And Dice</h1>
</div>
<br>
<br>
<br>
<div class="container">

    <div>
        <h1>Product :</h1>
    </div>
    <div> 
        <h1>Product Name</h1>
        <select name="productname" id="productname">
    	<option value="">---</option>
        <c:forEach items="${productNames}" var="productName">
            <option value="${productName}">${productName}</option>
        </c:forEach>
        <option></option>
    </select>
</div>
<div>
    
    <h1>Category</h1>
    
    <select name="category" id="category">
        <option value="">---</option>
        <c:forEach items="${categories}" var="category">
            <option value="${category}">${category}</option>
        </c:forEach>
    </select>
</div>
</div>

<div class="container">

    <div>
        <h1>Customer :</h1>
    </div>
    
    <div>
        
    <h1>Customer City</h1>
    <select name="customercity" id="customercity">
        <option value="">---</option>
        <c:forEach items="${customerCities}" var="city">
            <option value="${city}">${city}</option>
        </c:forEach>
    </select>
    </div>
    
    
    <div>
        <h1>Customer Type</h1>
        <select name="customertype" id="customertype">
            <option value="">---</option>
            <c:forEach items="${customerTypes}" var="type">
                <option value="${type}">${type}</option>
            </c:forEach>
        </select>
    </div>
</div>
  
<div class="container">

    <div>
        <h1>SalesPerson :</h1>
    </div>
    
    <div>
        
        
        <h1>SalesPerson Type</h1>
        <select name="salespersontype" id="salespersontype">
            <option value="">---</option>
            <c:forEach items="${salesPersonTypes}" var="type">
                <option value="${type}">${type}</option>
            </c:forEach>
        </select>
    </div>
</div>

<div class="container">
    <div>
        <label for="Product Name">Product Name</label>
        <input type="checkbox" name="ProductName" id="Product Name" value="abc">
    </div>
    <div>
        <label for="Category">Category</label>
        <input type="checkbox" name="Category" id="Category" value="abc">    
    </div>
    <div>
        <label for="Customer City">Customer City</label>
        <input type="checkbox" name="CustomerCity" id="Customer City" value="abc">
    </div>
    <div>
        <label for="Customer Type">Customer Type</label>
        <input type="checkbox" name="CustomerType" id="Customer Type" value="abc">

    </div>
    <div>
        <label for="SalesPersonType">SalesPersonType</label>
        <input type="checkbox" name="SalesPersonType" id="SalesPersonType" value="abc">
    </div>
    <div>
        <label for="SalesPersonExp">SalesPersonExperience</label>
        <input type="checkbox" name="SalesPersonExp" id="SalesPersonExp" value="abc">
    </div>
</div>
<div>
        <label for="ExportReport">Want To Export Report</label>
        <input type="checkbox" name="exportReport" id="exportReport" value="abc">
    </div>
<div class="text-center" align="center">
    <input style="font-size: 150%; font-weight:bold; padding:0.3%" type="submit" value="Filter" id="filter"/>    
</div>

</form>                             
</body>
</html>