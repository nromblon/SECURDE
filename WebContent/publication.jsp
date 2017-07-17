<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>

  <!-- Basic Page Needs
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <meta charset="utf-8">
  <title>SHS Online Library System</title>

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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/publicationpage.css">

  <!-- Favicon
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/favicon.png">

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
			  <a href="publicationpage.html" class="active">Publications</a>
			  <a href="roomspage.html">Rooms</a>
			  <a class="r-nav" href="loginpage.html">Login</a>
			</div>
		</div>
	</div>
		
	<!-- .container is main centered wrapper -->

	<br/><br/><br/><br/><br/><br/>

	<div class="row">
      <form class="unpadded" action="/search" method="post" name="searchtool" id="search" style="margin:20px;">
        Search:
        <select name="searchtype" id="searchtype">
          <option selected="selected"> Keyword</option>
          <option> Title</option>
          <option> Author</option>
          <option> Publisher</option>
          <option> Location</option>
        </select>
		
        <input type="hidden" name="SORT" id="SORT" value="D">
        <input type="text" maxlength="100" id="searcharg" size="20">
        <select name="searchscope" id="searchscope">
          <option value="1" selected="selected"> View Entire Collection</option> 
          <option value="2"> Books</option> 
          <option value="3"> Thesis</option> 
          <option value="4"> Magazines</option>  
        </select>
        <button type="button" class="btn btn-success">Submit</button>
      </form>
	</div>
	
	<div class="row">
		<div class="twelve columns">
		
		<select class="u-pull-right" name="pagenum" id="pageNum">
          <option selected="selected"> 1</option>
          <option> 2</option>
          <option> 3</option>
          <option> 4</option>
          <option> 5</option>
        </select>
		</div>
	</div>
	<div class="search-results">
		<div class="row" >
			<a href="publicationdetailpage.html">
				<div class="entry">
					<div class="one columns thumbnail">
						<img src="${pageContext.request.contextPath}resources/images/Publication/noimage.jpg">
					</div>
					<div class="nine columns book-details">
						<span id="book-title">Tara Road</span> <hr/>
						<span id="author"> Binchy, Maeve </span> <br/>
						<span id="publisher"> Orion Publishing Group</span> (<span id="year">1998</span>)
					</div>
				</div>
			</a>
		</div>
		
		<div class="row" >
			<a href="publicationdetailpage.html">
				<div class="entry">
					<div class="one columns thumbnail">
						<img src="images/Publication/noimage.jpg">
					</div>
					<div class="nine columns book-details">
						<span id="book-title">Tara Road</span> <hr/>
						<span id="author"> Binchy, Maeve </span> <br/>
						<span id="publisher"> Orion Publishing Group</span> (<span id="year">1998</span>)
					</div>
				</div>
			</a>
		</div>
		
	</div>
	
</div>

<!-- Note: columns can be nested, but it's not recommended since Skeleton's grid has %-based gutters, meaning a nested grid results in variable with gutters (which can end up being *really* small on certain browser/device sizes) -->

</body>
</html>