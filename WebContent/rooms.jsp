<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
!DOCTYPE html>
<html lang="en">
<head>

  <meta charset="utf-8">
  <title>SHS Online Library System | Reserve Rooms</title>

  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/normalize.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/skeleton.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/global.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/roomspage.css">

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
			  <a href="search">Publications</a>
			  <a class="active" href="rooms">Rooms</a>
			  <a id="login-link" class="r-nav active" href="login">
				  <script>
				  	if(<%= session.getAttribute("userId") %>!=null){
				  		$("#login-link").attr("href","logout");
				  		$("#login-link").html("Logout");
				  	}else{
				  		$("#login-link").attr("href","login");
				  		$("#login-link").html("Login");
				  	}
				  </script>
			  </a>
			  <% if(session.getAttribute("userId") != null) {%>
			  		<a class="r-nav" href="change">Change Password</a>
			  <% } %>
			</div>
		</div>
	</div>
		
	<!-- .container is main centered wrapper -->

	<br/><br/><br/><br/><br/><br/>
	<div class="row">
		<div class="twelve columns">
			<div class="two columns calendar">
				<label for="calendar">Date to Reserve:</label>
				<input class="full-width-form" type="date" id="calendar" class="calendar" oninput="updateSlots()">
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
			<input id="room-reserve-btn" type="button" class="button-primary submit-button" onclick="reserve()" value="Reserve"/>
			<script>
				var privilege = <%=session.getAttribute("privilege")%>;
				if (privilege == null){
					$("#room-reserve-btn").hide();
					$("#room-reserve-btn").attr("value","");
					$("#room-reserve-btn").attr("onclick","");
				}
				else{	
					if(privilege=='1'){
						$("#room-reserve-btn").show();
						$("#room-reserve-btn").attr("value","Reserve");
						$("#room-reserve-btn").attr("onclick","reserve()");
					}
					else if(privilege=='2'){
						$("#room-reserve-btn").show();
						$("#room-reserve-btn").attr("value","Remove Reservation");
						$("#room-reserve-btn").attr("onclick","delReservation()");
					}
					else{
						$("#room-reserve-btn").hide();
						$("#room-reserve-btn").attr("value","");
						$("#room-reserve-btn").attr("onclick","");
					}
				}
			</script>
		</div>
	</div>
</div>

<script>
function clickedTimebtn(source,schedDetails){
	var privilege = <%=session.getAttribute("privilege")%>;
	var parent = $("#"+source.id);
	if(privilege==1){
		if(!parent.hasClass("unavailable")){
			console.log(schedDetails);
			parent.toggleClass("selected");
		}
	}else if(privilege==2){
		if(parent.hasClass("unavailable")){
			console.log(schedDetails);
			parent.toggleClass("selected");
		}
	}
}
</script>
</body>
</html>