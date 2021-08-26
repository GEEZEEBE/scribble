<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>SINGLE - SCRIBBLE PAPERS</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
	</head>
	<body class="single is-preload">


<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>


<!-- Main -->
	<div id="main">

		<!-- Post -->
		<article class="post">
			<header>
				<div class="title">
					<h2><a href="#">SCRIBBLE SOMETHING</a></h2>
					<p>주절주절 써 봅니다</p>
				</div>
				<div class="meta">
					<time class="published" datetime="2015-11-01"></time>
					<a href="#" class="author"><span class="name">${authUser.name}</span><img src="images/avatar.jpg" alt="" /></a>
				</div>
			</header>
			<!-- <span class="image featured"><img src="images/pic01.jpg" alt="" /></span>  -->
				
			<form method="post" action="main?a=write" enctype="multipart/form-data">
				<div class="row gtr-uniform">
					<div class="col-6 col-12-xsmall">
						<input type="text" name="title" id="demo-name" value="" placeholder="Title" />
					</div>
					<div class="col-12">
						<textarea name="content" id="demo-message" placeholder="Enter your message" rows="6"></textarea>
					</div>
					<div class="col-12">
						<div class="col-6 col-12-xsmall">
							<h1>image upload</h1><input type="file" name="img_name" id="demo-name" value="Image" />
						</div>
					</div>
					<span></span>
					<div class="col-12">
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
	
<script>
	$("time").text((new Date()).toLocaleString());
</script>
</html>