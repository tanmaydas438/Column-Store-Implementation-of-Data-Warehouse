<%@ page language="java" contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
...
<html>
....
 
    <select name="country" id="country">
    <c:forEach items="${mapCountries}" var="country">
        <option value="${country.key}">${country.value}</option>
    </c:forEach>
    </select>
 
....
 
</html>
