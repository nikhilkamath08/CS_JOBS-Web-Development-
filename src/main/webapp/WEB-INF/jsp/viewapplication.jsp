<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
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

<p>Applicant: ${appli.applicant.firstName} ${appli.applicant.lastName}</p>
<p>Submitted: <fmt:formatDate value="${appli.submitDate}" pattern="M/d/yyyy" /></p>
<p>Current Position: ${appli.currentJobTitle} at ${appli.currentJobInstitution}
since ${appli.currentJobYear}</p>

<table class="table table-striped">
<tr><th>Degree</th><th>School</th><th>Year</th></tr>
<c:forEach items="${appli.degrees}" var="degree">
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
