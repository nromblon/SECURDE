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
       <a href="searchpage.html">Publications</a>
       <a href="roomspage.html">Rooms</a>
       <a class="r-nav" href="loginpage.html">Login</a>
     </div>
   </div>
 </div>
</header>
<br/><br/><br/><br/><br/><br/>

<!-- Search bar -->
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
    <label for="searchscope" style="display:none;">Search Scope</label>
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
  <div class="three columns"><img src="${pageContext.request.contextPath}/resources/images/Publication/noimage.jpg"></div>
  <div class="seven columns">
    <table>
      <tbody>
        <tr>
          <td>
            <div class = "description-elems">
              <h3 id = "pub-title-text">Tara Road</h3>
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
              <span id = "pub-type-text"><i class="icon-book"></i>Book</span>
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
              <span id = "pub-author-text">Binchy, Maeve</span>
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
              <span id = "pub-publisher-text">Orion Publishing Group</span>
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
              <span id = "pub-year-text">1998</span>
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
              <span id = "pub-location-text">823.914</span>
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
                <button class = "button-primary submit-button">SUBMIT</button>
          </div>
        </td>
      </tbody>
    </table>
  </div>
  <div class = "one column">
    <!--can only be seen by lib managers and lib staff-->
    <button id = "edit-button" class = "button-primary submit-button">EDIT</button>
  </div>
  <div class = "one column">
    <!--can only be seen by lib managers and lib staff-->
    <button id = "delete-button" class = "button-primary submit-button">DELETE</button>
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
