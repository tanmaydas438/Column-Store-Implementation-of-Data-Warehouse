<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type = "text/javascript" src="function.js"></script>
<script type = "text/javascript" src="PieChart.js"></script>
<script
src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js">
</script>
</head>
<body onload="caller()">
<table width="59%" border="1" align="center" bordercolor="#FF5733" bgcolor="#E7F7F1" id="myTable">
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

<div >
        
        <a href="LatticeOfCuboid.jsp">Lattice Of Cuboid</a>

</div>

<div >
        
        <a href="Lattice.html">Roll Up</a>

 </div>
 
 <div>
 		<div>
 		<%if(!request.getAttribute("attr1").equals("")){ %>
 			<%String attr1=(String)request.getAttribute("attr1"); %>
			<label><%=attr1%></label>
			<select name="attr1" id="attr1">
			<option value="none">---</option>
			<c:forEach items="${uniqueAttr1List}" var="att1Value">
			<option value="${att1Value}">${att1Value}</option>
			</c:forEach>
			</select>
        <% ;}%>
        </div>
        <div>
 		<%if(!request.getAttribute("attr2").equals("")){ %>
 			<%String attr2=(String)request.getAttribute("attr2"); %>
			<label><%=attr2%></label>
			<select name="attr2" id="attr2">
			<option value="none">---</option>
			<c:forEach items="${uniqueAttr2List}" var="att2Value">
			<option value="${att2Value}">${att2Value}</option>
			</c:forEach>
			</select>
        <% ;}%>
        </div>
        <div>
 		<%if(!request.getAttribute("attr3").equals("")){ %>
 			<%String attr3=(String)request.getAttribute("attr3"); %>
			<label><%=attr3%></label>
			<select name="attr3" id="attr3">
			<option value="none">---</option>
			<c:forEach items="${uniqueAttr3List}" var="att3Value">
			<option value="${att3Value}">${att3Value}</option>
			</c:forEach>
			</select>
        <% ;}%>
        </div>
        <div><input type = "button" onclick = "filterfunction()" value = "Filter"></div>
 		<div><input type="button" value="Clear Filter" onClick="document.location.reload(true)"></div>
 </div>
 
<canvas id="myPieChart" style="width:100%; max-width:700px"></canvas>
<canvas id="myBarChart" style="width:100%; max-width:700px"></canvas>

</body>
</html>