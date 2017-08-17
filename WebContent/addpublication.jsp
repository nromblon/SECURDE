<%@page import="java.util.ArrayList"%>
<%@page import="com.objects.Tag"%>
<%@page import="com.objects.PubType"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>

  <meta charset="utf-8">
  <title>Login</title>
  <meta name="description" content="">
  <meta name="author" content="">

  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/normalize.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/skeleton.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/global.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addpublication.css">

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
			  <% if(session.getAttribute("userId") != null) {%>
			  		<a class="r-nav" href="change">Change Password</a>
			  <% } %>
			</div>
		</div>
	</div>
	
    <form action="../addbook" method = "POST" class = "add-forms">
        <div class="row">
        <div class="three columns"><img src="${pageContext.request.contextPath}/resources/images/Publication/noimage.jpg"></div>
            <div class="seven columns">
                <table>
                <tbody>
                    <tr>
                    <td>
                        <label for="pub-title">Publication Title</label>
                        <input name = "title" class="u-full-width" type="text" id="pub-title">
                    </td>
                    <td></td>
                    </tr>
                    <tr>
                    <td>
                        <select name = "type" id = "pub-type-select">
			                  <% ArrayList<PubType> pubTypes = (ArrayList<PubType>)request.getAttribute("pubTypes"); 
			                  	 for(int i = 0; i < pubTypes.size(); i++) { %>
		                  	 		<option value = <%= pubTypes.get(i).getId() %>><%= pubTypes.get(i).getName() %></option>
			                  <% } %>
			              </select>
                    </td>
                    <td></td>
                    </tr>
                    <tr>
                    <td>
                        Author
                    </td>
                    <td>
                        <input name = "author" class="u-full-width" type="text" id="pub-author">
                    </td>
                    </tr>
                    <tr>
                    <td>
                        Publisher
                    </td>
                    <td>
                        <input name = "publisher" class="u-full-width" type="text" id="pub-publisher">
                    </td>
                    </tr>
                    <tr>
                    <td>Year</td>
                    <td>
                        <select name = "year" id = "pub-year-select">
                        </select>
                    </td>
                    </tr>
                    <tr>
                    <td>Location</td>
                    <td>
                        <input name = "location" class="u-full-width" type="text" id="pub-location">
                    </td>
                    </tr>
                    <tr>
                    <td>Tags</td>
                    <td>
                        <div id = "pub-tag-select">
			              <% ArrayList<Tag> allTags = (ArrayList<Tag>)request.getAttribute("allTags"); 
			                 for(int i = 0; i < allTags.size(); i++) { %>
			              		<input type="checkbox" name="tags" value=<%= allTags.get(i).getId() %>> <%= allTags.get(i).getName() %><br>
			              <% }%>
            			</div>
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

</body>

</html>
