<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<h3> Ranking Process</h3>
<p>Applicant: ${rank.applicant.firstName} ${rank.applicant.lastName}</p>
<p>Submitted: <fmt:formatDate value="${rank.submitDate}" pattern="M/d/yyyy" /></p>
<p>Current Position: ${rank.currentJobTitle} at ${rank.currentJobInstitution}
since ${rank.currentJobYear}</p>
<h4> Rounds</h4>

<h4>Click below to Add Comment</h4><a href="round1.html?jid=${rank.id}&indexes=0"><h3>Round1</h3></a>
<h4>Click below to Add Comment</h4><a href="round2.html?jid=${rank.id}&indexes=1"><h3>Round2</h3></a>
<h4>Click below to Add Comment</h4><a href="round3.html?jid=${rank.id}&indexes=2"><h3>Round3</h3></a>

</body>
</html>