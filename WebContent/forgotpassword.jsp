<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forgot Password</title>
 <meta name="description" content="">
 <meta name="author" content="">

 <meta name="viewport" content="width=device-width, initial-scale=1">

 <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/normalize.css">
 <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/skeleton.css">
 <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/global.css">
 <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/loginpage.css">

 <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/favicon.png">
 
  <!-- Scripts [jquery always goes first!]-->
 <script src="${pageContext.request.contextPath}/resources/scripts/jquery-3.0.0.min.js"></script>

</head>
<body>
<div class="container">

	<!-- nav header -->
	<div class="header">
		<div class="row">
			<div class="navheader twelve columns">
				<h3> SHS Online Library System </h3>
			</div>
		</div>
		<div class="row">
			<div class="topnav twelve columns">
			  <a href="search">Publications</a>
			  <a class="active" href="rooms">Rooms</a>
			  <a id="login-link" class="r-nav active" href="login">
				  <script>
				  		$("#login-link").attr("href","login");
				  		$("#login-link").html("Login");
				  </script>
			  </a>
			</div>
		</div>
	</div>
		
	<!-- .container is main centered wrapper -->

	<br/><br/><br/><br/><br/><br/>
	<div id="forgot-password">
		<form action="forgot" method="POST">
			<div class="row">
				<div class="twelve columns offset-by-three">
					<label for="username">Username</label> 
					<input class = "half-width-form" name="username" id="username" type="text" /> 
				</div>
			</div>
			<div class="row">
				<div class="twelve columns">
					<input class="button-primary submit-button" type="submit" value="Continue" > 
				</div>
			</div>

		</form>
	</div>
	
	
</div>
</body>
</html>