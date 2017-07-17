<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>

  <!-- Basic Page Needs
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <meta charset="utf-8">
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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/normalize.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/skeleton.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/global.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/registrationpage.css">

  <!-- Favicon
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/favicon.png">

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
			  <a class="r-nav" href="login">Login</a>
			</div>
		</div>
	</div>
  <!-- Primary Page Layout
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->

    <form class = "registration-forms">
      <div class="row">
        <div class="twelve columns offset-by-three">
          <label for="firstname">First Name</label>
          <input class="half-width-form" type="text" id="firstname">
        </div>
      </div>
      <div class="row">
        <div class="twelve columns offset-by-three">
          <label for="midinitial">Middle Initial</label>
          <input class="half-width-form" type="text" id="midinitial">
        </div>
      </div>
      <div class="row">
        <div class="twelve columns offset-by-three">
          <label for="lastname">Last Name</label>
          <input class="half-width-form" type="text" id="lastname">
        </div>
      </div>
      <div class="row">
        <div class="twelve columns offset-by-three">
          <label for="username">Username</label>
          <input class="half-width-form" type="email" id="username">
        </div>
      </div>
      <div class="row">
        <div class="twelve columns offset-by-three">
          <label for="password">Password</label>
          <input class="half-width-form" type="password" id="password">
        </div>
      </div>
      <div class="row">
        <div class="twelve columns offset-by-three">
          <label for="cpassword">Confirm Password</label>
          <input class="half-width-form" type="password" id="cpassword">
        </div>
      </div>
      <div class="row">
        <div class="twelve columns offset-by-three">
          <label for="email">Email Address</label>
          <input class="half-width-form" type="email" id="email">
        </div>
      </div>
      <div class="row">
        <div class="twelve columns offset-by-three">
          <label for="idnumber">ID Number</label>
          <input class="half-width-form" type="text" id="idnumber">
        </div>
      </div>
      <div class="row">
        <div class="twelve columns offset-by-three">
          <label for="birthday">Birthdate</label>
          <input class="half-width-form" type="date" id="birthday">
        </div>
      </div>
      <div class="row">
        <div class="twelve columns offset-by-three">
          <label for="secretQuestion">Secret Question</label>
          <input class="half-width-form" type="text" id="secretQuestion">
        </div>
      </div>
      <div class="row">
        <div class="twelve columns">
          <input class="button-primary submit-button" type="submit" value="REGISTER">
        </div>
      </div>

    </form>
  </div>

<!-- End Document
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
</body>
</html>
