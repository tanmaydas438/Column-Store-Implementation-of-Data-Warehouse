<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type = "text/javascript" src="function1.js"></script>
<script type = "text/javascript" src="PieChart1.js"></script>
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
	
	<%	if(!request.getAttribute("attr4").equals("")){ %>
	<th>${attr4}</th>
	<% ;}%>
	
	<%	if(!request.getAttribute("attr5").equals("")){ %>
	<th>${attr5}</th>
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
	
	<%if(!request.getAttribute("attr4").equals("")){ %>
	<td>${nodeValue.getAttr4()}</td>
	<% } %>
	
	<%if(!request.getAttribute("attr5").equals("")){ %>
	<td>${nodeValue.getAttr5()}</td>
	<% } %>
	
	<td>${nodeValue.getValue()}</td>
	</tr>
	</c:forEach>
</table>



<div >
        
        <a href="Lattice1.html">Roll Up</a>

 </div>
 
 <div>
 		<div>
 		<%if(!request.getAttribute("attr1").equals("")){ %>
 			<%String attr1=(String)request.getAttribute("attr1"); %>
			<label id="attr1Type"><%=attr1%></label>
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
			<label id="attr2Type"><%=attr2%></label>
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
			<label id="attr3Type"><%=attr3%></label>
			<select name="attr3" id="attr3">
			<option value="none">---</option>
			<c:forEach items="${uniqueAttr3List}" var="att3Value">
			<option value="${att3Value}">${att3Value}</option>
			</c:forEach>
			</select>
        <% ;}%>
        </div>
        <div>
 		<%if(!request.getAttribute("attr4").equals("")){ %>
 			<%String attr4=(String)request.getAttribute("attr4"); %>
			<label id="attr4Type"><%=attr4%></label>
			<select name="attr4" id="attr4">
			<option value="none">---</option>
			<c:forEach items="${uniqueAttr4List}" var="att4Value">
			<option value="${att4Value}">${att4Value}</option>
			</c:forEach>
			</select>
        <% ;}%>
        </div>
        <div>
 		<%if(!request.getAttribute("attr5").equals("")){ %>
 			<%String attr5=(String)request.getAttribute("attr5"); %>
			<label id="attr5Type"><%=attr5%></label>
			<select name="attr5" id="attr5">
			<option value="none">---</option>
			<c:forEach items="${uniqueAttr5List}" var="att5Value">
			<option value="${att5Value}">${att5Value}</option>
			</c:forEach>
			</select>
        <% ;}%>
        </div>
        <div><input type = "button" onclick = "filterfunction()" value = "Filter"></div>
 		<div><input type="button" value="Clear Filter" onClick="document.location.reload(true)"></div>
 </div>
 
<div style="float: right;width:50%">
<canvas id="myBarChart"></canvas>
</div>
 	
 <div style="float: left;width:50%">
<canvas  id="myPieChart" ></canvas>
</div>

</body>
</html>