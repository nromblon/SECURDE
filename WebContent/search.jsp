<%@page import="java.util.ArrayList"%>
<%@page import="com.objects.Publication"%>
<%@page import="com.constants.Privilege"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>

  <meta charset="utf-8">
  <title>SHS Online Library System</title>
  <meta name="description" content="">
  <meta name="author" content="">

  <meta name="viewport" content="width=device-width, initial-scale=1">
	
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/normalize.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/skeleton.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/global.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/searchpage.css">

  <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/favicon.png">

  <script src="${pageContext.request.contextPath}/resources/scripts/jquery-3.0.0.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/scripts/search.js"></script>
</head>
<body>


  <div class = "container max-width">
  
	<!-- nav header -->
	<div class="header">
		<div class="row">
			<div class="navheader twelve columns">
				<h3> SHS Online Library System </h3>
			</div>
		</div>
		<div class="row">
			<div class="topnav twelve columns">
			  <a href="search" class="active">Publications</a>
			  <a href="rooms">Rooms</a>
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
			</div>
		</div>
	</div>
		
	<!-- .container is main centered wrapper -->

	<br/><br/><br/><br/>
  		<!--  TODO: fix value of select to be less descriptive -->
      <div class = "row">
          <div class = "nine columns">
            <div class = "container">
                <div class="row">
                    <form action = "get_pubs" class = "search-forms" method = "get">
                        <div class="two columns">
                            <select name = "searchBy" class = "search-select" id = "searchBy">
                                <%= request.getAttribute("searchBySelect") %>
                            </select>
                        </div>
                        <div class="seven columns">
                        	<% if(request.getAttribute("searchTerm") == null) {%>
                            	<input name = "searchTerm" class="u-full-width" placeholder="Search..." type="text" id="username" maxlength="140">
                            <% } else { %>
                            	<input name = "searchTerm" class="u-full-width" placeholder="Search..." type="text" id="username" maxlength="140" value = <%= request.getAttribute("searchTerm")%>>
                            <% } %>
                        </div>
                        <div class="one column">
                            <select name = "category" class = "search-select" id = "category">
                                <%= request.getAttribute("categorySelect") %>
                            </select>
                        </div>
                    </form>
                </div>
                <!-- TODO: decide if to include this shit -->
                <!-- <div class = "row">
                    <span>Sort by
                        <span class = "link">Relevance</span> | 
                        <span class = "link">Date</span> | 
                        <span class = "link">Title</span> 
                    </span>
                </div>
                <div class = "row">
                    <span>Page
                        <span class = "link">1</span> | 
                        <span class = "link">2</span> | 
                        <span class = "link">3</span> | 
                        <span class = "link">4</span> | 
                        <span class = "link">5</span> | 
                        <span class = "link">6</span> |
                        <span class = "link">Next</span>
                    </span>
                </div> -->

                <div class = "search-results">
                    <!--one result-->
                    <div class = "result">
                        <!--<div class = "container">-->
                        <% 	ArrayList<Publication> publications = (ArrayList<Publication>)request.getAttribute("publications");
                        	if(publications != null && !publications.isEmpty()) {
                        		for(int i = 0; i < publications.size(); i++) {%>
	                        		<div class = "row">
										<a href="publication/details?id=<%= publications.get(i).getId()%>">
											<div class="entry">
												<div class = "three columns thumbnail">
													<img src="${pageContext.request.contextPath}/resources/images/Publication/noimage.jpg">
												</div>
												<div class = "nine columns">
													<span class = "link result-title"><%= publications.get(i).getName() %></span>
													<hr/>
													<span class = "result-author"><%= publications.get(i).getAuthor() %></span>
													<span class = "result-publisher"><%= publications.get(i).getPublisher() %></span> (<span class="result-year"><%= publications.get(i).getYear() %></span>)
												</div>
											</div>
										</a>
		                            </div>
                        <% 		} 
                        	} else { %>
                        		<div class = "row">
									<span> <% 
										if(request.getAttribute("message") == null) {
											out.print("There seems to be nothing here...");
										}
										else {
											out.print(request.getAttribute("message"));
										}
										%> </span>
	                            </div>
                        	<% } %>           
                    </div>
                </div>
            </div>
          </div>

          <div class = "three columns">
            <div class = "container side-bar">
                <span class = "side-bar-header">Search by Material Type</span>
                <ul>
                    <li class = "link side-bar-link">Books</li>
                    <li class = "link side-bar-link">Thesis</li>
                    <li class = "link side-bar-link">Magazines</li>
                </ul>
                <hr>
                
                <!--can only be seen by the lib manager-->
                <div id = "additional-options" class = "hidden">
	                <span class = "side-bar-header">Library Manager</span>
	                <ul>
	                    <li class = "link side-bar-link"><a href = "publication/add" class = "link">Add publication</a></li>
	                </ul>
	                <hr>
	            </div>
	            
	            <!--can only be seen by a registered user  -->
	            <%  if(session.getAttribute("privilege") != null && Integer.parseInt((String)session.getAttribute("privilege")) == Privilege.USER) {%>
		            <div id = "user-options">
		            	<span class = "side-bar-header">Reserved Books</span>
		            	<ul>
		            		<% if(request.getAttribute("reservedPubs") != null) {
		            				ArrayList<Publication> reservedPubs = (ArrayList<Publication>) request.getAttribute("reservedPubs");
		            				for(int i = 0; i < reservedPubs.size(); i++) { %>
		            					<li class = "link side-bar-link"><a href = "publication/details?id=<%= reservedPubs.get(i).getId()%>" class = "link"><%= reservedPubs.get(i).getName() %></a></li>
		            				<%}
		            		}%>
		            	</ul>
		            	<hr>
		            	
		            	<span class = "side-bar-header">Borrowed Books</span>
		            	<ul>
		            		<% if(request.getAttribute("borrowedPubs") != null) {
		            				ArrayList<Publication> borrowedPubs = (ArrayList<Publication>) request.getAttribute("borrowedPubs");
		            				for(int i = 0; i < borrowedPubs.size(); i++) { %>
		            					<li class = "link side-bar-link"><a href = "publication/details?id=<%= borrowedPubs.get(i).getId()%>" class = "link"><%= borrowedPubs.get(i).getName() %></a></li>
		            				<%}
		            		}%>
		            	</ul>
		            	<hr>
		            </div>
	            <%} %>
            </div>
          </div>
        </div>
  </div>
</body>

<script type="text/javascript">
  var privilege = <%= session.getAttribute("privilege") %>;
</script>
</html>
