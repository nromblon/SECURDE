<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>SHS Online Library System</title>

  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/normalize.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/skeleton.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/global.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/publicationdetails.css">
  <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/favicon.png">

  <!-- Scripts [jquery always goes first!]-->
  <script src="${pageContext.request.contextPath}/resources/scripts/jquery-3.0.0.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/scripts/publicationdetails.js"></script>

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
		</div>
	</div>
	<!-- .container is main centered wrapper -->

	<br/><br/><br/><br/><br/><br/>

	<div class="container">
		<table class="u-full-width">
			<thead>
				<tr>
					<th>Name</th>
					<th>Username</th>
					<th>Email</th>
					<th>Privilege</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Maynard C. Si</td>
					<td>user</td>
					<td>maynard_si@dlsu.edu.ph</td>
					<td>User</td>
					<td><button class="button-primary">Lock</button></td>
				</tr>
				<tr>
					<td>Luis Q. Madrigal</td>
					<td>manager</td>
					<td>luis_madrigal@dlsu.edu.ph</td>
					<td>Library Manager</td>
					<td><button>Unlock</button></td>
				</tr>
				<tr>
					<td>Neil V. Romblon</td>
					<td>staff</td>
					<td>neil_romblon@dlsu.edu.ph</td>
					<td>Library Staff</td>
					<td><button>Unlock</button></td>
				</tr>

			</tbody>
		</table>
	</div>

</body>
</html>