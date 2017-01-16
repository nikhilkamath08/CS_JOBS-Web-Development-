<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- Latest compiled and minified CSS -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
     background-size: 100% 900%;
    
    
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table table class="table table-hover">

<tr>
<th>Applicants </th>
<th> View</th>
</tr>

<c:forEach items="${appjob}" var="app">
<tr>
<td>${app.applicant.firstName}</td>
 <td><a href="viewapplication.html?jobid=${app.id}">View Application</a></td>
</tr>
</c:forEach>
</table>
</body>
</html>