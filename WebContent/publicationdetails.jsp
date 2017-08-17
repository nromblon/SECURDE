<%@page import="java.util.ArrayList"%>
<%@page import="com.objects.User"%>
<%@page import="com.objects.Review"%>
<%@page import="com.objects.Publication"%>
<%@page import="com.objects.Tag"%>
<%@page import="com.objects.PubType"%>
<%@page import="com.constants.Privilege"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>

  <meta charset="utf-8">
  <title>Publication details Page</title> <!-- TODO: change to publication name -->
  <meta name="description" content="">
  <meta name="author" content="">

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
<style type="text/css">

</style>
<body>
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
	            <option value = "Books">Books</option>
	            <option value = "Thesis">Thesis</option>
	            <option value = "Magazines">Magazines</option>
	        </select>
	    </div>
	</form>
</div>
<!-- TODO: null authors -->
<div class="row">
  <div class="three columns"><img src="${pageContext.request.contextPath}/resources/images/Publication/noimage.jpg"></div>
  <div class="seven columns">
    <table>
    <% Publication pub = (Publication)request.getAttribute("details"); %>
      <tbody>
        <tr>
          <td>
            <div class = "description-elems">
              <h3 id = "pub-title-text"><%= pub.getName() %></h3>
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
              <span id = "pub-type-text"><i class="icon-book"></i><%= pub.getType() %></span>
            </div>
            <div class = "edit-elems hidden">
              <select id = "pub-type-select">
                  <% ArrayList<PubType> pubTypes = (ArrayList<PubType>)request.getAttribute("pubTypes"); 
                  	 for(int i = 0; i < pubTypes.size(); i++) {
                  	 	if(pubTypes.get(i).getName().equals(pub.getType())) {%>
                  	 		<option value = <%= pubTypes.get(i).getId() %> selected><%= pubTypes.get(i).getName() %></option>
                  	 	<%} else { %>
                  	 		<option value = <%= pubTypes.get(i).getId() %>><%= pubTypes.get(i).getName() %></option>
                  	 	<%} %>
                  <% } %>
              </select>
            </div>
          </td>
        </tr>
        <tr>
          <td>Author</td>
          <td>
            <div class = "description-elems">
              <span id = "pub-author-text"><%= pub.getAuthor() %></span>
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
              <span id = "pub-publisher-text"><%= pub.getPublisher() %></span>
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
              <span id = "pub-year-text"><%= pub.getYear() %></span>
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
              <span id = "pub-location-text"><%= pub.getLocation() %></span>
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
              <div id = "pub-tags">
              	<% ArrayList<Tag> pubTags = (ArrayList<Tag>)request.getAttribute("pubTags"); 
              	   for(int i = 0; i < pubTags.size(); i++) {%>
              	   		<span><%= pubTags.get(i).getName() %></span>
             	<% } %>
              </div>
               
            </div>
            <div id = "pub-tag-select" class = "edit-elems hidden">
              <% ArrayList<Tag> allTags = (ArrayList<Tag>)request.getAttribute("allTags"); 
            	 boolean hasTag = false;
                 for(int i = 0; i < allTags.size(); i++) {
                	hasTag = false;
                 	for(int j = 0; j < pubTags.size(); j++) {%>
                 		<% if(allTags.get(i).isSameTag(pubTags.get(j))) {
                 				hasTag = true;
                 				break;
                 		   } %>
              <% 	} %>
              		<% if(hasTag) { %>
              				<input type="checkbox" name="tags" value=<%= allTags.get(i).getId() %> checked> <%= allTags.get(i).getName() %><br>
              		<% } else {%>
              				<input type="checkbox" name="tags" value=<%= allTags.get(i).getId() %>> <%= allTags.get(i).getName() %><br>
              		<% } %>
              <% }%>
            </div>
          </td>
        </tr>
        <tr>
          <td>Status</td>
          <td>
            <div>
              <span id = "pub-status-text"><%= pub.getStatus() %></span>
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
  <button id = "edit-button" class = "button-primary submit-button hidden">EDIT</button>
  </div>
  <div class = "one column">
  <button id = "delete-button" class = "button-primary submit-button hidden">DELETE</button>
  </div>
</div>

<% if(session.getAttribute("privilege") != null) {
	int privilege = Integer.valueOf((String)session.getAttribute("privilege"));
	if(privilege == Privilege.USER) {%>
		<div class = "row">
		  <div class = "six columns description-elems">
		  	<% if((boolean)request.getAttribute("reservedByMe")) {%>
		    	<p>You have already reserved this book.</p>
		    <% }else if((boolean)request.getAttribute("alreadyReserved")) {%>
		    	<p>This book has already been reserved.</p>
		    <% }else { %>
		    	<button id = "reserve-button" class = "button-primary submit-button u-pull-right">RESERVE</button>
		    <% } %>
		  </div>
		</div>
<%  } else if(privilege == Privilege.LIB_MANAGER) {%>
		<div class = "row">
		  <div class = "six columns">
		  	<% if(request.getAttribute("userWhoReserved") != null) {
		  		User user = (User)request.getAttribute("userWhoReserved");%>
		  		<% if((boolean) request.getAttribute("alreadyBorrowed")){  %>
			    	<span><%= user.getUserType() %> <%= user.getIdNumber() %> is currently borrowing this book.</span>
			    <% } else { %>
			    	<span><%= user.getUserType() %> <%= user.getIdNumber() %> wants to reserve this book.</span>
			    	<button id = "override-reserve" class = "button-primary submit-button u-pull-right">OVERRIDE</button>
			    <% } %>
		    <% } %>
		  </div>
		</div>
<%  } 
  }%>
<div class = "review-section">
	<% if((boolean)request.getAttribute("userHasBorrowed")) {%>
		<div class = "row">
		  <form action = "../addreview?pubId=<%= request.getParameter("id") %>" method = "post">
		    <h6>Leave Review</h6>
		    <textarea name = "reviewText" rows="4" cols="100"></textarea>
		    <div class = "twelve columns">
		      <input id = "submit-review" class="button-primary submit-button" type="submit" value="SUBMIT">
		    </div>
		  </form>
		</div>
	<% } %>
	
	<div class = "reviews">
	  <!--one review-->
  		<% 	ArrayList<Review> reviews = (ArrayList<Review>)request.getAttribute("reviews");
           	if(reviews != null) {
           		for(int i = 0; i < reviews.size(); i++) {%>
				  <div class = "row">
				    <div class = "twelve columns">
				      <p class = "review-author"><%= reviews.get(i).getUsername() %></p>
				      <p class = "review-desc"><%= reviews.get(i).getReviewText() %></p>
				    </div>
				  </div>
				  <hr>
		<%  	}
           	}%>
	  
	</div>
</div>
</div>
</body>
<script type = "text/javascript">
	var privilege = <%= session.getAttribute("privilege") %>;
</script>
</html>
