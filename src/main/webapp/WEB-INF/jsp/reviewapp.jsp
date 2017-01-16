<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style>
body  {
    background-image: url("image15.jpg");
    background-color: #cccccc;
     background-repeat: no-repeat;
     background-size: 100% 600%;
    
    
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>CS Jobs (Exam Edition)</h2>
<%-- <h3>${appli.jtitle}</h3> --%>

<p>Applicant: ${applis.applicant.firstName} ${applis.applicant.lastName}</p>
<p>Submitted: <fmt:formatDate value="${applis.submitDate}" pattern="M/d/yyyy" /></p>
<p>Current Position: ${applis.currentJobTitle} at ${applis.currentJobInstitution}
since ${applis.currentJobYear}</p>

<table class="table table-striped">
<tr><th>Degree</th><th>School</th><th>Year</th></tr>
<c:forEach items="${applis.degrees}" var="degree">
<tr>
  <td>${degree.name}</td>
  <td>${degree.school}</td>
  <td>${degree.year}</td>
</tr>
</c:forEach>
</table>

<a href="../download.html?filena=${appli.cv.name}">${appli.cv.name}</a>
<a href="../download.html?filena=${appli.researchStatement.name}">${appli.researchStatement.name}</a>
<a href="../download.html?filena=${appli.teachingStatement.name}">${appli.teachingStatement.name}</a>


</body>
</html>
