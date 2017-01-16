<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<style>
body  {
    background-image: url("image15.jpg");
    background-color: #cccccc;
     background-repeat: no-repeat;
     background-size: 100% 700%;
    
    
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CS Jobs - Reviewer</title>
</head>
<body>
<h2>CS Jobs (Exam Edition)</h2>

<p>You are logged in as <em><security:authentication property="principal.name" /></em>.</p>
<form action="<c:url value='/logout' />" method="post">
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
  <input name="submit" type="submit" value="Logout" />
</form>
<p>
  <security:authorize access="hasRole('ROLE_ADMIN')">
    [<a href="<c:url value='/admin.html' />">Admin Home</a>]
  </security:authorize>
  <security:authorize access="hasRole('ROLE_REVIEWER')">
    [Reviewer Home]
  </security:authorize>
    [<a href="<c:url value='/applicant.html' />">Applicant Home</a>]
</p>

<div class="container">
<form action="reviewer.html" method="get">
<input type="text" name="revid"/>
<input type="submit" name="search1" value="Search"/>
</form>
<table border="2">

<tr class="danger">
<th>Job </th>
<th>Applicant</th>
<th> CV</th>

 </tr>
 
<c:forEach items="${displayjob}" var="dis">
<tr>
<td >${dis.job.title}</td>
<td> ${dis.applicant.firstName}</td>
<td>${dis.cv.name}</td>


</tr> 
</c:forEach> 
</table>
</div>

</body>
</html>
