<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="agile.scapp.controller.UserLoginPage" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MNC Home</title>
<link href="includes/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="includes/style.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
</head>
<body>
	<div class="home-container">
		<div class="navbar-container">
			<img class="logo" alt="Brand" src="includes/images/Logo_O.png" />
			<ul>
				<li><a class="active" href="#home">About</a></li>
				<li><a href="#">Contact</a></li>
				<li><a href="#">News</a></li>
				<li><a href="#">Home</a></li>
			</ul>
		</div>
		<!--<nav class="navbar navbar-default custom-navbar">
			  <div class="container-fluid">
			    <div class="navbar-header">
			      <a class="navbar-brand" href="#">
			        <img alt="Brand" src="includes/images/Logo_O.png">
			      </a>
			    </div>
			  </div>
			</nav>-->
		<%-- <form action="StudentIdeaForm" method="get">
			<input type="hidden" id=UserName name="username"
				value=<%=request.getAttribute("Username")%>> <input
				type="hidden" name="dept"
				value=<%=request.getAttribute("department")%>>
				 <input
				type="hidden" name="firstname"
				value=<%=request.getAttribute("FirstName")%>>
		</form> --%>
		<% 
		session.setAttribute("username",request.getAttribute("Username"));
		session.setAttribute("firstname",request.getAttribute("FirstName"));
		session.setAttribute("department",request.getAttribute("department"));
		%>
		<div class="menu-container">
			<img src="includes/images/profile.png">
			<h3>Hi <%=request.getAttribute("FirstName")%></h3>
			<a href="htmlPages/sampleOne.html"><h1>Profile</h1></a> <a
				href="htmlPages/sampleTwo.html"><h1>Dashboard</h1></a> <a
				href="htmlPages/mncView.jsp"><h1>View Ideas</h1></a><!--  <a
				href="htmlPages/sampleFour.html"><h1>Title 4</h1></a> <a
				href="htmlPages/sampleFive.html"><h1>Title 5</h1></a> --> <a
				href="index.html"><h1>Logout</h1></a>
		</div>
		<div class="content-from-menu"></div>
	</div>

	<script type=text/javascript>
		$(function() {
			var $menu = $('.menu-container'), $target = $('.content-from-menu');
			$menu.on('click', '> a', function(event) {
				var $this = $(this);
				event.preventDefault();
				$target.load($this.attr('href'));
			});
		});
	</script>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="includes/js/bootstrap.min.js"></script>
</body>
</html>
