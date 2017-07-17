<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>

  <!-- Basic Page Needs
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <meta charset="utf-8">
  <title>Login</title>
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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addpublication.css">

  <!-- Favicon
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/favicon.png">

  <!-- Scripts [jquery always goes first!]-->
  <script src="${pageContext.request.contextPath}/resources/scripts/jquery-3.0.0.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/scripts/addpublication.js"></script>
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
			  <a href="../search">Publications</a>
			  <a href="../rooms">Rooms</a>
			  <a id="login-link" class="r-nav active" href="login">
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
	
  <!-- Primary Page Layout
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  
    <form action="publication/add" method = "POST" class = "add-forms">
        <div class="row">
        <div class="three columns"><img src="${pageContext.request.contextPath}/resources/images/Publication/noimage.jpg"></div>
            <div class="seven columns">
                <table>
                <tbody>
                    <tr>
                    <td>
                        <label for="pub-title">Publication Title</label>
                        <input class="u-full-width" type="text" id="pub-title">
                    </td>
                    <td></td>
                    </tr>
                    <tr>
                    <td>
                        <select id = "pub-type-select">
                            <option>Book</option>
                            <option>Thesis</option>
                            <option>Magazine</option>
                        </select>
                    </td>
                    <td></td>
                    </tr>
                    <tr>
                    <td>
                        Author
                    </td>
                    <td>
                        <input class="u-full-width" type="text" id="pub-author">
                    </td>
                    </tr>
                    <tr>
                    <td>
                        Publisher
                    </td>
                    <td>
                        <input class="u-full-width" type="text" id="pub-publisher">
                    </td>
                    </tr>
                    <tr>
                    <td>Year</td>
                    <td>
                        <select id = "pub-year-select">
                        </select>
                    </td>
                    </tr>
                    <tr>
                    <td>Location</td>
                    <td>
                        <input class="u-full-width" type="text" id="pub-location">
                    </td>
                    </tr>
                    <tr>
                    <td>Tags</td>
                    <td>
                        <select id = "pub-tag-select">
                            <option>Math</option>
                            <option>Science</option>
                        </select>
                    </td>
                    </tr>
                    <tr>
                    <td>
                        <button class = "button-primary submit-button">SUBMIT</button>
                    </td>
                    </tr>
                </tbody>
                </table>
            </div>
        </div>
      
    </form>
  </div>

<!-- End Document
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
</body>

</html>
