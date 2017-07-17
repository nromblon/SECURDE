<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
  <meta name="description" content="">
  <meta name="author" content="">

  <!-- Mobile Specific Metas
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <!-- FONT
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <!--<link href="//fonts.googleapis.com/css?family=Raleway:400,300,600" rel="stylesheet" type="text/css">-->

  <!-- CSS
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <link rel="stylesheet" href="css/normalize.css">
  <link rel="stylesheet" href="css/skeleton.css">
  <link rel="stylesheet" href="css/global.css">
  <link rel="stylesheet" href="css/registrationpage.css">

  <!-- Favicon
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <link rel="icon" type="image/png" href="images/favicon.png">

  <!-- Scripts [jquery always goes first!]-->
  <script src="${pageContext.request.contextPath}/resources/scripts/jquery-3.0.0.min.js"></script>

</head>
<body>
  <div class = "container">
   <!--nav header-->
   <div class="header">
    <div class="row">
     <div class="navheader twelve columns">
      <h3> SHS Online Library System </h3>
    </div>
  </div>
  <div class="row">
   <div class="topnav twelve columns">
    <a href="../admin/create">Create New Account</a>
    <a href="../admin/users">View Users</a>
    <a href="../admin/tools">Admin Tools</a>
    <a id="login-link" class="r-nav active" href="../login">
		  <script>
		  	if(<%= session.getAttribute("userId") %>!=null){
		  		$("#login-link").attr("href","../logout");
		  		$("#login-link").html("Logout");
		  	}else{
		  		$("#login-link").attr("href","../login");
		  		$("#login-link").html("Login");
		  	}
		  </script>
	  </a>
  </div>
</div>

<br/><br/><br/><br/><br/><br/>

<div class = "container">
  <button class="button-primary">Export All publications (xls)</button>
  <button class="button-primary">Export All publications (xls)</button>
  <button class="button-primary">Export All Rooms (xml)</button>
  <button class="button-primary">Export All Rooms (xml)</button>

</div>


</body>
</html>