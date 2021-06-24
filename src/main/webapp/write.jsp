<%@ page import = "java.util.*, java.net.*" %>
<%@page import="kr.ac.kopo.domain.*, kr.ac.kopo.dao.*, kr.ac.kopo.service.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>write post</title>
	<!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v5.15.3/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="./assets/css/styles.css" rel="stylesheet" />
</head>
<body>
	<%
	String boardidS = request.getParameter("board");
	int boardid = boardidS == null ? 1 : Integer.parseInt(boardidS);
	
	BoardService bs = BoardServiceImpl.getInstance();
	String[] boardTitles = bs.viewAllTitles();
	
	BoardItemService bis = BoardItemServiceImpl.getInstance();
	String currDate = bis.getCurrentDate();
	%>
	
    <!-- Navigation-->
    <nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
        <div class="container">
            <a class="navbar-brand" href="index.jsp">Board</a>
            <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                Menu
                <i class="fas fa-bars"></i>
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
            </div>
        </div>
    </nav>
        
    <!-- Page Header-->
    <header class="masthead" style="background-image: url('https://images.unsplash.com/photo-1493225457124-a3eb161ffa5f?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80')">
        <div class="overlay"></div>
        <div class="container">
           <div class="row">
                <div class="col-lg-8 col-md-10 mx-auto">
                    <div class="site-heading">
                        <h1>write post</h1>
                    </div>
                </div>
            </div>
        </div>
    </header>
        
	<div class="container mt-4 mb-4">
	    <div class="row justify-content-md-center">
	        <div class="col-md-12 col-lg-8">
	            <h1 class="h2 mb-4">Write New Post</h1>
                <form class="writeForm" method="post" name="writeForm">
		            <div class="form-group">
		                <label for="name">Name</label>
		                <input type="text" class="form-control" name="author" placeholder="Your name" required>
		            </div>
		            
		            <div class="form-group">
		                <label for="board">Board ID</label>
		                <input type="text" class="form-control" name="boardId" placeholder="<%= boardid%>" value="<%= boardid%>" readonly>
		            </div>
		            
		            <div class="form-group">
		                <label for="date">Date</label>
		                <input type="text" class="form-control" name="date" placeholder="<%= currDate%>" readonly>
		            </div>
		
		            <div class="form-group">
		              <label>Title</label>
		              <input type="text" class="form-control" id="title" name="title" placeholder="Enter title" required>
		            </div>
		
		            <div class="form-group">
		              <label>Content</label>
		              <textarea id="editor" name="content" id="content" required></textarea>
		            </div>
		            
		            <input type="text" name="action" value="insert" class="d-none">
		
					<div class="d-flex justify-content-end">
		            	<input type="submit" class="btn btn-primary" value="submit" formaction="insertDB.jsp?post=<%= bis.newPostId()%>" >
		            	<button type="button" class="btn btn-secondary" onclick="location.href='postlist.jsp?board=<%= boardid %>'">cancel</button>
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
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
    <script src="https://cdn.tiny.cloud/1/vlve92w3vygehay7n5iv3bu1qo5zwup4vcom4g0zgbw8scpp/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
    <script src="./assets/js/scripts.js"></script>
    <script src="./assets/js/textEditor.js"></script>
    <script src="./assets/js/form.js"></script>
</body>
</html>