<%@ page import = "java.util.*, java.net.*, java.util.stream.Collectors" %>
<%@page import="kr.ac.kopo.domain.*, kr.ac.kopo.dao.*, kr.ac.kopo.service.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>post view</title>
	<!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v5.15.3/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="./assets/css/styles.css" rel="stylesheet" />
    <link href="./assets/css/viewComment.css" rel="stylesheet" />
    <link href="./assets/css/writeComment.css" rel="stylesheet" /> 
</head>
<body>

	<%
	String pagenumS = request.getParameter("page");
	String postidS = request.getParameter("post");
	String comAuthorS = request.getParameter("comAuthor");
	String comContentS = request.getParameter("comContent");
	String comLevelS = request.getParameter("comLevel");
	
	int pagenum = pagenumS == null ? 1 : Integer.parseInt(pagenumS);
	int postid = postidS == null ? 1 : Integer.parseInt(postidS);
	
	BoardService bs = BoardServiceImpl.getInstance();
	String[] boardTitles = bs.viewAllTitles();
	
	BoardItemService bis = BoardItemServiceImpl.getInstance();
	BoardItem item = bis.viewOne(postid);
	String currDate = bis.getCurrentDate();
	%>
	
	
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
        <header class="masthead" style="background-image: url('https://images.unsplash.com/photo-1483412033650-1015ddeb83d1?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1353&q=80')">
            <div class="overlay"></div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-md-10 mx-auto">
                        <div class="site-heading">
                            <h1><%= item.getTitle()%></h1>
                            <span class="meta">
                                Posted by
                                <a href="#!"><%= item.getAuthor()%></a>
                                on <%= item.getDate()%>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        
        <!-- Post Content-->
        <article>
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-md-10 mx-auto" style="white-space: pre-line">
                        <%= item.getContent()%>
                    </div>
                </div>
            </div>
        </article>
        <hr />
        
        <!-- Comment -->
	    <Section>
	        <div class="container">
	            <div class="row">
	                <div class="col-lg-8 col-md-10 mx-auto">
	                    <!--comment item-->

	                 <!--comment writer-->
					 <form class="form-block">
						<div class="row mt-5">
							<div class="col-xs-12 col-sm-6">
								<div class="form-group fl_icon">
									<div class="icon"><i class="fa fa-user"></i></div>
									<input class="form-input" type="text" placeholder="Your name" name="comAuthor" required>
								</div>
							</div>
							<div class="col-xs-12 col-sm-6 fl_icon">
								<div class="form-group fl_icon">
									<div class="icon"><i class="far fa-calendar-check"></i></div>
									<input class="form-input" type="text" placeholder="<%=currDate%>" readonly>
									<input type="text" name="comRelevel" value="1" class="d-none">
								</div>
							</div>
							<div class="col-lg-12 mx-auto">									
								<div class="form-group">
									<textarea class="form-input" required name="comContent" placeholder="Your comment"></textarea>
								</div>
							</div>
							<div class="container mb-3">
								<div class="d-flex justify-content-end">
									<div class="btn-group" role="group" aria-label="" >
									  <button type="button" class="btn btn-primary" 
									  		 value="submit" formaction="postview.jsp?post=<%= postid%>">
									  	write comment
									  </button>
									</div>
								</div>
							</div>
						</div>
					</form>
	                </div>
	            </div>
	        </div>
	    </Section>
	    <hr/>
        
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
    <script src="./assets/js/scripts.js"></script>
</body>
</html>