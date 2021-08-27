<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<% pageContext.setAttribute( "newLine", "\n" ); %>

<!DOCTYPE HTML>
<html>
	<head>
		<title>SINGLE - SCRIBBLE PAPERS</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
	</head>
	<body class="single is-preload">


<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>


				<!-- Main -->
					<div id="main">

						<!-- Post -->
						<input type = "hidden" name = "a" value="modify">
							
							<article class="post">
								<header>
									<div class="title">
										<h2><a href="#">SCRIBBLE SOMETHING</a></h2>
										<p>주절주절 써 봅니다</p>
									</div>
									<div class="meta">
										<time class="published" datetime="2015-11-01">"${vo.reg_date}"</time>
										<a href="#" class="author"><span class="name">"${vo.name}"</span><img src="images/avatar.jpg" alt="" /></a>
									</div>
								</header>
								<!-- <span class="image featured"><img src="images/pic01.jpg" alt="" /></span>  -->
									
								<form method="post" action="main?a=modify&no=${vo.board_id}" enctype="multipart/form-data">
									<div class="row gtr-uniform">
										<div class="col-12">
											<span class="image fit">											
												<img src="filestorage/${vo.img_name}" alt="" />
											</span>
										</div>
										<div class="col-6 col-12-xsmall">
											<input type="text" name="title" id="demo-name" value="${vo.title}" />
										</div>
										<div class="col-12">
											<textarea name="content" id="demo-message" placeholder="" rows="6">${vo.content}</textarea>
										</div>
										<div class="col-12">
											<div class="col-6 col-12-xsmall">
												<h1>image upload</h1><input type="file" name="img_name" id="demo-name" value="" />
											</div>
										</div>
										<br>
										<div class="col-12">
											<input type="hidden" name="page" value="${param.page}">
											<input type="hidden" name="keyword" value="${parma.keyword}">
											<ul class="actions">
												<li><input type="submit" value="Done" /></li>
												<li><input type="reset" value="Cancel" /></li>
											</ul>
										</div>
									</div>
								</form>											
							</article>
					</div>


<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>


	</body>
</html>