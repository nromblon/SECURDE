<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>

  <!-- Basic Page Needs
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <meta charset="utf-8">
  <title>Publication details Page</title> <!-- TODO: change to publication name -->
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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/publicationdetails.css">
  <!-- Favicon
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/favicon.png">

  <!-- Scripts [jquery always goes first!]-->
  <script src="${pageContext.request.contextPath}/resources/scripts/jquery-3.0.0.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/scripts/publicationdetails.js"></script>
</head>
<style type="text/css">

</style>
<body>

  <!--insert sick navbar @Maynard-->

  <!-- Primary Page Layout
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <div class="container">
    <header>

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
       <a id="login-link" class="r-nav active" href="../login">
		  <script>
		  	var id = <%= request.getParameter("id")%>
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
</header>
<br/><br/><br/><br/><br/><br/>

<!-- Search bar -->
<div class="row">
  <form action = "../get_pubs" class = "search-forms" method = "get">
	 <div class="two columns">
	     <select name = "searchBy" class = "search-select" id = "searchBy">
	         <option value = "Publication">Title</option>
	         <option value = "Author">Author</option>
	         <option value = "Publisher">Publisher</option>
	     </select>
	 </div>
	 <div class="seven columns">
	 	<% if(request.getAttribute("searchTerm") == null) {%>
	     	<input name = "searchTerm" class="u-full-width" placeholder="Search..." type="text" id="username">
	     <% } else { %>
	     	<input name = "searchTerm" class="u-full-width" placeholder="Search..." type="text" id="username" value = <%= request.getAttribute("searchTerm")%>>
	     <% } %>
	    </div>
	    <div class="one column">
	        <select name = "category" class = "search-select" id = "category">
	            <option>View Entire Collection</option>
	            <option value = "Book">Books</option>
	            <option value = "Magazine">Thesis</option>
	            <option value = "Thesis">Magazines</option>
	        </select>
	    </div>
	</form>
</div>

<div class="row">
  <div class="three columns"><img src="${pageContext.request.contextPath}/resources/images/Publication/noimage.jpg"></div>
  <div class="seven columns">
    <table>
      <tbody>
        <tr>
          <td>
            <div class = "description-elems">
              <h3 id = "pub-title-text"><%= request.getAttribute("title") %></h3>
            </div>
            <div class = "edit-elems hidden">
              <label for="pub-title">Publication Title</label>
              <input class="u-full-width" type="text" id="pub-title">
            </div>
          </td>
        </tr>
        <tr>
          <td>
            <div class = "description-elems">
              <span id = "pub-type-text"><i class="icon-book"></i><%= request.getAttribute("type") %></span>
            </div>
            <div class = "edit-elems hidden">
              <select id = "pub-type-select">
                  <option>Book</option>
                  <option>Thesis</option>
                  <option>Magazine</option>
              </select>
            </div>
          </td>
        </tr>
        <tr>
          <td>Author</td>
          <td>
            <div class = "description-elems">
              <span id = "pub-author-text"><%= request.getAttribute("author") %></span>
            </div>
            <div class = "edit-elems hidden">
              <input class="u-full-width" type="text" id="pub-author">
            </div>
          </td>
        </tr>
        <tr>
          <td>Publisher</td>
          <td>
            <div class = "description-elems">
              <span id = "pub-publisher-text"><%= request.getAttribute("publisher") %></span>
            </div>
            <div class = "edit-elems hidden">
              <input class="u-full-width" type="text" id="pub-publisher">
            </div>
          </td>
        </tr>
        <tr>
          <td>Year</td>
          <td>
            <div class = "description-elems">
              <span id = "pub-year-text"><%= request.getAttribute("year") %></span>
            </div>
            <div class = "edit-elems hidden">
              <select id = "pub-year-select">
              </select>
            </div>
          </td>
        </tr>
        <tr>
          <td>Location</td>
          <td>
            <div class = "description-elems">
              <span id = "pub-location-text"><%= request.getAttribute("location") %></span>
            </div>
            <div class = "edit-elems hidden">
              <input class="u-full-width" type="text" id="pub-location">
            </div>
          </td>
        </tr>
        <tr>
          <td>Tags</td>
          <td>
            <div class = "description-elems">
              <a href="">Domestic fiction</a> <a href="">Love stories</a>
            </div>
            <div class = "edit-elems hidden">
              <select id = "pub-tag-select">
                  <option>Math</option>
                  <option>Science</option>
              </select>
            </div>
          </td>
        </tr>
        <td>
          <div class = "edit-elems hidden">
                <button id = "submitEdit" class = "button-primary submit-button">SUBMIT EDIT</button>
          </div>
        </td>
      </tbody>
    </table>
  </div>
  <div class = "one column">
  <button id = "edit-button" class = "button-primary submit-button">EDIT</button>
  	<script>
		var privilege = <%= session.getAttribute("privilege") %>;		
  		if(privilege == "2" || privilege == "3")
  			$("#edit-button").show();
  		else
  			$("#edit-button").hide();
  		
	</script>
  </div>
  <div class = "one column">
  <button id = "delete-button" class = "button-primary submit-button">DELETE</button>
  	<script>
	  	var privilege = <%= session.getAttribute("privilege") %>;
  		if(privilege == "2" || privilege == "3")
			$("#delete-button").show();
  		else
  			$("#delete-button").hide();
	    	
	</script>
  </div>
</div>

<div class = "row">
  <div class = "six columns description-elems">
    <button class = "button-primary submit-button u-pull-right">RESERVE</button>
  </div>
</div>

<div class = "row">
  <form>
    <h6>Leave Review</h6>
    <textarea rows="4" cols="100"></textarea>
    <div class = "twelve columns">
      <input id = "submit-review" class="button-primary submit-button" type="submit" value="SUBMIT">
    </div>
  </form>
</div>

<div class = "reviews">
  <!--one review-->
  <div class = "row">
    <div class = "twelve columns">
      <p class = "review-author">Maynard Si</p>
      <p class = "review-desc">This is good book</p>
    </div>
  </div>
  <hr>
  
</div>

</div>
<!--<footer class="footer">
<div class="container">
  <p class="text-muted"> ©2017 Undefeated sa 3V3</p>
</div>
  
</footer>-->
<!-- End Document
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
</body>
</html>
