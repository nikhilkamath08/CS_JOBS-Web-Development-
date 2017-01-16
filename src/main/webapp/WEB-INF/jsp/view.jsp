<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
body  {
    background-image: url("image72.jpg");
    background-color: #cccccc;
     background-repeat: no-repeat;
     background-size: 100% 290%;
    
    
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<br/><br/><hr>
<table>
<th>ID</th><td>${disjob.id}</td></tr>
<th>title</th><td>${disjob.title}</td></tr>
<th>description</th><td>${disjob.description}</td></tr>

<th>publishdate</th><td>${disjob.publishDate}</td></tr>
<th>closedate</th><td>${disjob.closeDate}</td></tr>

<th>Committee chair</th><td>${disjob.committeeChair.firstName }</td></tr>
</table>
<h4>committee members</h4>
<table border="1">
<c:forEach items="${disjob.committeeMembers}" var="comm">

<tr>
<td>${comm.firstName}</td>
</tr>

</c:forEach>

</table>

<hr>

</body>
</html>