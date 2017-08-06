<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>

  <meta charset="utf-8">
  <title>Registration</title>
  <meta name="description" content="">
  <meta name="author" content="">

  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/normalize.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/skeleton.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/global.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/registrationpage.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/roomspage.css">

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
	
    <form class = "registration-forms" method = "POST" action = "register">
      <div class="row">
        <div class="twelve columns offset-by-three">
          <label for="firstname">First Name</label>
          <input class="half-width-form" type="text" name="firstname" id="firstname">
        </div>
      </div>
      <div class="row">
        <div class="twelve columns offset-by-three">
          <label for="midinitial">Middle Initial</label>
          <input class="half-width-form" type="text" name="midinitial" id="midinitial">
        </div>
      </div>
      <div class="row">
        <div class="twelve columns offset-by-three">
          <label for="lastname">Last Name</label>
          <input class="half-width-form" type="text" name="lastname" id="lastname">
        </div>
      </div>
      <div class="row">
        <div class="twelve columns offset-by-three">
          <label for="username">Username</label>
          <input class="half-width-form" type="text" name="username" id="username">
        </div>
      </div>
      <div class="row">
        <div class="twelve columns offset-by-three">
          <label for="password">Password</label>
          <input class="half-width-form" name="password" type="password" id="password">
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
          <label for="calendar">Birthday</label>
          <input class="full-width-form" name="calendar" type="date" id="calendar">
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
          <input class="button-primary submit-button" type="submit" value="register">
        </div>
      </div>

    </form>
  </div>

</body>
</html>
