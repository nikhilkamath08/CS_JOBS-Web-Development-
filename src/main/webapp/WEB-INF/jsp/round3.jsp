<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>Insert title here</title>
</head>

<body>
<form modelAttribute="r3" method="post">
<b>AddComment:</b> <br/><br/>
<textarea rows="5" cols="60" name="comment"></textarea>
<br/>
Rank:<input type="text" name= "rank" />


<input type="hidden" name="_csrf" value="${_csrf.token}" />
<input type="submit" name="submit" value="Submit">
</form>

</body>
</html>