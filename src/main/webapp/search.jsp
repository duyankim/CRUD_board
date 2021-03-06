<%@ page import="java.util.*, java.net.*"%>
<%@page
	import="kr.ac.kopo.domain.*, kr.ac.kopo.dao.*, kr.ac.kopo.service.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>post list</title>
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
	String searchWord = request.getParameter("search");
	String boardidS = request.getParameter("board");
	int boardid = boardidS == null ? 1 : Integer.parseInt(boardidS);
	
	BoardService bs = BoardServiceImpl.getInstance();
	String[] boardTitles = bs.viewAllTitles();
	
	BoardItemService bis = BoardItemServiceImpl.getInstance();
	List<BoardItem> matchedPosts = bis.viewSearchPost(searchWord);
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
		style="background-image: url('https://images.unsplash.com/photo-1514320291840-2e0a9bf2a9ae?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80')">
		<div class="overlay"></div>
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-md-10 mx-auto">
					<div class="site-heading">
						<h1>Search Result</h1>
						<span class="subheading">posts containing "<%=searchWord%>"</span>
					</div>
				</div>
			</div>
		</div>
	</header>

	<!-- Main Content-->
	<div class="container">
		<div class="row">
			<div class="col-lg-8 col-md-10 mx-auto">
				<%
                for (int j = 0; j < matchedPosts.size(); j++) {
                	BoardItem item = matchedPosts.get(j);
                	String preview;
                	int end;
                	if (item.getContent() != null) {
                		if (item.getContent().length() > 50) {
                			end = 50;
                		} else {
                			end = item.getContent().length();
                		}
                		preview = item.getContent().substring(0, end);
                	} else {
                		preview = "";
                	}
                	
                	out.println("<div class='post-preview'>");
                	out.println("<a href='postview.jsp?board=" + boardid + "&post=" + item.getId() +"'>");
                	out.println("<h2 class='post-title'>" + item.getTitle() +"</h2>");
                	out.println("<h3 class='post-subtitle'>"+ preview +"...</h3>");
                	out.println("</a>");
                	out.println("<p class='post-meta'>Posted by");
                	out.println("<span>" + item.getAuthor() +"</span>");
                	out.println("on "+ item.getDate());
                	out.println("</p>");
                	out.println("</div>");
	                if (j != matchedPosts.size()-1) {
	                	out.println("<hr />");
	                    }
	                }
                %>
			</div>
		</div>
	</div>

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