
<%@ page import="java.util.*, java.net.*"%>
<%@page
	import="kr.ac.kopo.domain.*, kr.ac.kopo.dao.*, kr.ac.kopo.service.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>write post</title>
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.15.3/js/all.js"
	crossorigin="anonymous"></script>
<!-- Google fonts-->
<link
	href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
	rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="./assets/css/styles.css" rel="stylesheet" />
<link href="./assets/css/search.css" rel="stylesheet" />
</head>
<body>
	<%
	BoardService bs = BoardServiceImpl.getInstance();
	BoardItemService bis = BoardItemServiceImpl.getInstance();
	String[] boardTitles = bs.viewAllTitles();	
	
	String postIdS = request.getParameter("post");
	String boardIdS = request.getParameter("board");
	int postId = Integer.parseInt(postIdS);
	bis.delete(postId);
	
	%>

	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-light fixed-top"
		id="mainNav">
		<div class="container">
			<a class="navbar-brand" href="index.jsp">Board</a>
			<button class="navbar-toggler navbar-toggler-right" type="button"
				data-toggle="collapse" data-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				Menu <i class="fas fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
					<%
                    for (int i = 0; i < boardTitles.length; i++) {
                    	out.println("<li class='nav-item'><a class='nav-link' href='postlist.jsp?board="+ (i+1) +"'>"+ boardTitles[i] +"</a></li>");
                    }
                    %>
				</ul>
				<div class="searchbar">
					<form class="searchForm" method="post" action="search.jsp">
						<input class="search_input" type="text" name="search"
							placeholder="Search..." required>
						<button type="submit" class="search_icon"></button>
						<i class="fas fa-search"></i>
					</form>
				</div>
			</div>
		</div>
	</nav>

	<!-- Page Header-->
	<header class="masthead"
		style="background-image: url('https://images.unsplash.com/photo-1493225457124-a3eb161ffa5f?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80')">
		<div class="overlay"></div>
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-md-10 mx-auto">
					<div class="site-heading">
						<h1>delete post</h1>
					</div>
				</div>
			</div>
		</div>
	</header>

	<div class="container mt-4 mb-4">
		<div class="row justify-content-md-center">
			<div class="col-md-12 col-lg-8">
				<h1 class="h2 mb-4">The post has been deleted</h1>
				<form class="writeForm" method="post" name="insertForm"
					id="insertForm" action="update.jsp">
					<div class="d-flex justify-content-end">
						<button type="button" class="btn btn-secondary"
							onclick="window.location.href='index.jsp'">go to home</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<hr>

	<!-- Footer-->
	<footer>
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-md-10 mx-auto">
					<p class="copyright text-muted">Copyright &copy; kopo03</p>
				</div>
			</div>
		</div>
	</footer>

	<!-- Bootstrap core JS-->
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="./assets/js/scripts.js"></script>
</body>
</html>