<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>

<html>
	<head>
		<title>SCRIBBLE PAPERS</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
	</head>
	<body class="is-preload">

    
<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>

    
<!-- Main -->
<div id="main">

	<!-- Post -->
	<c:forEach items="${list}" var="vo">
		<article class="post">
			<header>
				<div class="title">
					<h2><a href="main?a=view&no=${vo.board_id}&page=${pc.paging.page}&keyword=${keyword}">${vo.title}</a></h2>
				</div>
				<div class="meta">
					<time class="published" datetime=${vo.reg_date}>${vo.reg_date}</time>
					<a href="#" class="author"><span class="name">${vo.name}</span><img src="images/avatar.jpg" alt="" /></a>
				</div>
			</header>
			<a href="/main.jsp?a=view" class="image featured"><img src="${vo.img_name}" alt="" /></a>
			<p>${vo.content}</p>
			<footer>
				<ul class="actions">
					<li><a href="main?a=view&no=${vo.board_id}&page=${pc.paging.page}&keyword=${keyword}" class="button large">Continue Reading</a></li>
				</ul>
				<ul class="stats">
					<li><a href="#">General</a></li>
					<li><a href="#" class="icon solid fa-heart">${vo.hit}</a></li>
				</ul>
			</footer>
		</article>
	</c:forEach>




<!-- Pagination -->
		<ul class="actions pagination">
			<c:choose>
				<c:when test="${pc.prev}">
					<li><a href="<c:url value='/main?a=list&no=&page=${pc.beginPage-1}&keyword=${keyword}'/>" class="button large previous">Previous Page</a></li>
				</c:when>
				<c:otherwise>
					<li>
						<a href="<c:url value='/main?a=list&no=&page=${pc.beginPage-1}&keyword=${keyword}'/>" class="disabled button large previous">Previous Page</a>
					</li>
				</c:otherwise>
			</c:choose>
			<c:forEach var="pageNum" begin="${pc.beginPage}" end="${pc.endPage}">
				<li>
			   		<a href="<c:url value='/main?a=list&no=&page=${pageNum}&keyword=${keyword}'/>" class="button large ${(pc.paging.page==pageNum) ? 'disabled' : ''}">${pageNum}</a>
				</li>		    
		    </c:forEach>
			<c:choose>
				<c:when test="${pc.next}">
					<li>
						<a href="<c:url value='/main?a=list&no=&page=${pc.endPage+1}&keyword=${keyword}'/>" class="button large next">Previous Page</a>
					</li>
				</c:when>
				<c:otherwise>
					<li><a href="<c:url value='/main?a=list&no=&page=${pc.endPage+1}&keyword=${keyword}'/>" class="disabled button large next">Previous Page</a></li>
				</c:otherwise>
			</c:choose>
		</ul>

</div>

<!-- Sidebar -->
<section id="sidebar">

	<!-- Intro -->
	<section id="intro">
		<a href="#" class="logo"><img src="images/logo.jpg" alt="" /></a>
		<header>
			<h2>SCRIBBLE PAPERS</h2>
			<p>YOU CAN SCRIBBLE ANYTHING PAPERS HERE</a></p>
		</header>
	</section>

<!-- Mini Posts -->
	<section>
		<div class="mini-posts">

			<!-- Mini Post -->
			<c:forEach items="${list4}" var="vo">
				<article class="mini-post">
					<header>
						<h3><a href="main?a=view&no=${vo.board_id}&page=${pc.paging.page}&keyword=${keyword}">${vo.title}</a></h3>
						<time class="published" datetime=${vo.reg_date}>${vo.reg_date}</time>
					</header>
					<a href="main?a=view&no=${vo.board_id}&page=${pc.paging.page}&keyword=${keyword}" class="image"><img src="${vo.img_name}" alt="" /></a>
				</article>
			</c:forEach>

			

		</div>
	</section>

<!-- Posts List -->
	<c:if test="${authUser!=null}">
		<section>
			<header>
				<h2>MY SCRIBBLES</h2>
			</header>
			<ul class="posts">
				<c:forEach items="${listMy}" var="vo">
					<li>
						<article>
							<header>
								<h3><a href="single.html">${vo.title}</a></h3>
								<time class="published" datetime="${vo.reg_date}">${vo.reg_date}</time>
							</header>
							<a href="single.html" class="image"><img src="${vo.img_name}" alt="" /></a>
						</article>
					</li>
				</c:forEach>
			</ul>
		</section>
	</c:if>						

<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>


	</body>

<script>
	function checkLogin() {
		if ("${authUser == null }") {
			alert("로그인을 해야 Scribble이 가능합니다.");
			location.href = "main?a=list";
		}
	}
</script>
</html>