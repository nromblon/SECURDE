<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
!DOCTYPE html>
<html lang="en">
<head>

  <!-- Basic Page Needs
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <meta charset="utf-8">
  <title>SHS Online Library System | Reserve Rooms</title>

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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/roomspage.css">

  <!-- Favicon
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/favicon.png">

  <!-- Scripts [jquery always goes first!]-->
  <script src="${pageContext.request.contextPath}/resources/scripts/jquery-3.0.0.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/scripts/roomspage.js"></script>
</head>
<body onload="init()">

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
			  <a href="searchpage.html">Publications</a>
			  <a class="active" href="roomspage.html">Rooms</a>
			  <a class="r-nav" href="loginpage.html">Login</a>
			</div>
		</div>
	</div>
		
	<!-- .container is main centered wrapper -->

	<br/><br/><br/><br/><br/><br/>
	<div class="row">
		<div class="twelve columns">
			<div class="two columns calendar">
				<label for="calendar">Date to Reserve:</label>
				<input class="full-width-form" type="date" id="calendar" class="calendar">
		</div>
		
	</div>
	
	
	<div class="row">
		<div class="twelve columns time-table">
				<table class="u-full-width">
					<thead>
						<tr>
							<th>Room</th>
							<th>7:00 AM</th>
							<th>8:00 AM</th>
							<th>9:00 AM</th>
							<th>10:00 AM</th>
							<th>11:00 AM</th>
							<th>12:00 AM</th>
							<th>1:00 PM</th>
							<th>2:00 PM</th>
							<th>3:00 PM</th>
							<th>4:00 PM</th>
							<th>5:00 PM</th>
							<th>6:00 PM</th>
							<th>7:00 PM</th>
						</tr>
					</thead>
					<tbody class="table-body">	
					</tbody>
				</table>
		</div>
	</div>
	<hr/>
	<div class="row">
		<div class="one columns">
		<br/>
			<input type="button" class="button-primary submit-button" value="Reserve"/>
		</div>
	</div>
</div>
</body>
</html>