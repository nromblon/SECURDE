<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>

  <meta charset="utf-8">
  <title>Login</title>
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
			  <a href="search">Publications</a>
			  <a href="rooms">Rooms</a>
			  <a id="login-link" class="r-nav active" href="../login">
				  <script>
				  	if(<%= session.getAttribute("id") %>!=null){
				  		$("#login-link").attr("href","logout");
				  		$("#login-link").html("Logout");
				  	}else{
				  		$("#login-link").attr("href","login");
				  		$("#login-link").html("Login");
				  	}
				  </script>
			  </a>
			</div>
		</div>
	</div>
	
    <form action = "login" method ="POST" class = "login-forms">
      <div class="row">
        <div class="twelve columns offset-by-three">
          <label for="username">Username</label>
          <input class="half-width-form" name="username" type="text" id="username" required>
        </div>
      </div>
      <div class="row">
        <div class="twelve columns offset-by-three">
          <label for="password">Password</label>
          <input class="half-width-form" name="password" type="password" id="password" required>
        </div>
      </div>
      <div class="row">
        <div class="twelve columns">
          <input class="button-primary submit-button" type="submit" value="LOGIN">
        </div>		
      </div>
      <!-- TODO: fix di naka center puta -->
      <div class="row">
        <div class="six columns offset-by-four">
	        <span class="error" name="error">
				<%
					if(request.getAttribute("error") == null) {
						request.setAttribute("error", "");
					}
					out.print(request.getAttribute("error"));
				%>
			</span>
        </div>
      </div>
      <div class="row">
        <div class="six columns offset-by-five">
          <a class = "link" href="register">New User? Register here</a>
        </div>
      </div>
    </form>
  </div>

</body>
</html>
