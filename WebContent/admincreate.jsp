<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Registration</title>
  <meta name="description" content="">
  <meta name="author" content="">

  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/normalize.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/skeleton.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/global.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/registration.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/roomspage.css">
  <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/favicon.png">

  <!-- Scripts [jquery always goes first!]-->
  <script src="${pageContext.request.contextPath}/resources/scripts/jquery-3.0.0.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/scripts/publicationdetails.js"></script>

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
			    <a href="create">Create New Account</a>
			    <a href="users">View Users</a>
			    <a href="tools">Admin Tools</a>
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
	</div>

  <div style="margin-top:150px;"> </div>
  <form class = "registration-forms"  method = "POST" action = "create">
    <div class="row">
      <div class="twelve columns offset-by-three">
        <label for="firstname">First Name</label>
        <input class="half-width-form" name="firstname" type="text" id="firstname">
      </div>
    </div>
    <div class="row">
      <div class="twelve columns offset-by-three">
        <label for="midinitial">Middle Initial</label>
        <input class="half-width-form" name="midinitial" type="text" id="midinitial">
      </div>
    </div>
    <div class="row">
      <div class="twelve columns offset-by-three">
        <label for="lastname">Last Name</label>
        <input class="half-width-form" name="lastname" type="text" id="lastname">
      </div>
    </div>
    <div class="row">
      <div class="twelve columns offset-by-three">
        <label for="username">Username</label>
        <input class="half-width-form" name="username" type="text" id="username">
      </div>
    </div>
    <div class="row">
      <div class="twelve columns offset-by-three">
        <label for="password">Account type</label>
        <select name = "privilege">
          <option value="2">Library Manager</option>
          <option value="3">Library Staff</option>
        </select>
      </div>
    </div>
    <div class="row">
      <div class="twelve columns offset-by-three">
        <label for="email">Email Address</label>
        <input class="half-width-form" name="email" type="email" id="email">
      </div>
    </div>
    <div class="row">
      <div class="twelve columns offset-by-three">
        <label for="idnumber">ID Number</label>
        <input class="half-width-form" name="idnumber" type="text" id="idnumber">
      </div>
    </div>
    <div class="row">
      <div class="twelve columns offset-by-three">
        <label for="birthday">Birthday</label>
        <input class="half-width-form" name="calendar" type="date" id="birthday">
      </div>
    </div>
    <div class="row">
      <div class="twelve columns offset-by-three">
          <label for="secretQuestion">Secret Question</label>
          <select name="secretQuestion" id="secretQuestion">
          	<!-- to change -->
          	<option value="1">What is the name of your first pet?</option>
          </select>
        </div>
    </div>
    <div class="row">
        <div class="twelve columns offset-by-three">
          <label for="answer">Answer to secret question</label>
          <input class="half-width-form" name="answer" type="text" id="answer">
        </div>
    </div>
    <div class="row">
      <div class="twelve columns">
        <input class="button-primary submit-button" type="submit" value="REGISTER">
      </div>
    </div>

  </form>

</div>
</body>
</html>