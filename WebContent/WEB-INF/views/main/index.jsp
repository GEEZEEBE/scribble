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
							<article class="post">
								<c:forEach items="${list}" var="vo">
								<header>
								
									<div class="title">
										<h2><a href="single.html">${vo.title}</a></h2>
										<p>부 제목</p>
									</div>
									<div class="meta">
										<time class="published" datetime="2015-11-01">>${vo.reg_date}</time>
										<a href="#" class="author"><span class="name">${vo.name}</span><img src="images/avatar.jpg" alt="" /></a>
									</div>
								</header>
								<a href="single.html" class="image featured"><img src="images/pic01.jpg" alt="" /></a>
								<p>${vo.title}</p>
								<footer>
									<ul class="actions">
										<li><a href="single.html" class="button large">Continue Reading(이거 살려야 하나?)</a></li>
									</ul>
									<ul class="stats">
										<li><a href="#">General(이거 있으면 private도 만들어야?)</a></li>
										<li><a href="#" class="icon solid fa-heart">${vo.hit}</a></li>
										<li><a href="#" class="icon solid fa-comment">128</a></li>
									</ul>
								</footer>
								</c:forEach>
							</article>

						<!-- Post -->
							<article class="post">
								<header>
									<div class="title">
										<h2><a href="single.html">주 제목 22</a></h2>
										<p>부 제목2222</p>
									</div>
									<div class="meta">
										<time class="published" datetime="2015-10-25">작성일222</time>
										<a href="#" class="author"><span class="name">작성자222</span><img src="images/avatar.jpg" alt="" /></a>
									</div>
								</header>
								<a href="single.html" class="image featured"><img src="images/pic02.jpg" alt="" /></a>
								<p>모든 것이 원점으로 돌아간 상황. 그렇다고 해서 변화가 없는 건 아니다. 케인의 대우가 달라졌다. 팀에 남겠다는 뜻을 밝히자, 토트넘 다니엘 레비 회장이 케인에게 큰 선물을 안겼다. 주급을 10만파운드(약 1억6000만원)나 확 인상해준 것이다. 이제 케인은 주급 33만파운드(약 5억3000만원)를 받게 된다.</p>
								<footer>
									<ul class="actions">
										<li><a href="single.html" class="button large">Continue Reading</a></li>
									</ul>
									<ul class="stats">
										<li><a href="#">General</a></li>
										<li><a href="#" class="icon solid fa-heart">28</a></li>
										<li><a href="#" class="icon solid fa-comment">128</a></li>
									</ul>
								</footer>
							</article>

						<!-- Post -->
							<article class="post">
								<header>
									<div class="title">
										<h2><a href="single.html">주 제목 33</a></h2>
										<p>부 제목3333</p>
									</div>
									<div class="meta">
										<time class="published" datetime="2015-10-22">작성일333</time>
										<a href="#" class="author"><span class="name">작성자333</span><img src="images/avatar.jpg" alt="" /></a>
									</div>
								</header>
								<a href="single.html" class="image featured"><img src="images/pic03.jpg" alt="" /></a>
								<p>333 여기에는 내용을 입력합니다. 수 개월간 떠들썩하게 진행됐던 해리 케인 '이적사가'가 대단원의 막을 내렸다. 소란의 크기에 비하면 허무한 결말, 케인이 결국 현 소속팀인 토트넘 홋스퍼에 남겠다고 직접 선언했다. 케인의 합류를 오매불망 기다리던 맨체스터시티는 허탈해졌다. 들끓었던 이적시장도 다시 잠잠해지게 됐다.</p>
								<footer>
									<ul class="actions">
										<li><a href="single.html" class="button large">Continue Reading</a></li>
									</ul>
									<ul class="stats">
										<li><a href="#">General</a></li>
										<li><a href="#" class="icon solid fa-heart">28</a></li>
										<li><a href="#" class="icon solid fa-comment">128</a></li>
									</ul>
								</footer>
							</article>

						<!-- Pagination -->
							<ul class="actions pagination">
								<li><a href="" class="disabled button large previous">Previous Page</a></li>
								<li><a href="#" class="button large next">Next Page</a></li>
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
										<article class="mini-post">
											<header>
												<h3><a href="single.html">Vitae sed condimentum</a></h3>
												<time class="published" datetime="2015-10-20">October 20, 2015</time>
												<a href="#" class="author"><img src="images/avatar.jpg" alt="" /></a>
											</header>
											<a href="single.html" class="image"><img src="images/pic04.jpg" alt="" /></a>
										</article>

									<!-- Mini Post -->
										<article class="mini-post">
											<header>
												<h3><a href="single.html">Rutrum neque accumsan</a></h3>
												<time class="published" datetime="2015-10-19">October 19, 2015</time>
												<a href="#" class="author"><img src="images/avatar.jpg" alt="" /></a>
											</header>
											<a href="single.html" class="image"><img src="images/pic05.jpg" alt="" /></a>
										</article>

									<!-- Mini Post -->
										<article class="mini-post">
											<header>
												<h3><a href="single.html">Odio congue mattis</a></h3>
												<time class="published" datetime="2015-10-18">October 18, 2015</time>
												<a href="#" class="author"><img src="images/avatar.jpg" alt="" /></a>
											</header>
											<a href="single.html" class="image"><img src="images/pic06.jpg" alt="" /></a>
										</article>

									<!-- Mini Post -->
										<article class="mini-post">
											<header>
												<h3><a href="single.html">Enim nisl veroeros</a></h3>
												<time class="published" datetime="2015-10-17">October 17, 2015</time>
												<a href="#" class="author"><img src="images/avatar.jpg" alt="" /></a>
											</header>
											<a href="single.html" class="image"><img src="images/pic07.jpg" alt="" /></a>
										</article>

								</div>
							</section>

						<!-- Posts List -->
							<section>
								<ul class="posts">
									<li>
										<article>
											<header>
												<h3><a href="single.html">Lorem ipsum fermentum ut nisl vitae</a></h3>
												<time class="published" datetime="2015-10-20">October 20, 2015</time>
											</header>
											<a href="single.html" class="image"><img src="images/pic08.jpg" alt="" /></a>
										</article>
									</li>
									<li>
										<article>
											<header>
												<h3><a href="single.html">Convallis maximus nisl mattis nunc id lorem</a></h3>
												<time class="published" datetime="2015-10-15">October 15, 2015</time>
											</header>
											<a href="single.html" class="image"><img src="images/pic09.jpg" alt="" /></a>
										</article>
									</li>
									<li>
										<article>
											<header>
												<h3><a href="single.html">Euismod amet placerat vivamus porttitor</a></h3>
												<time class="published" datetime="2015-10-10">October 10, 2015</time>
											</header>
											<a href="single.html" class="image"><img src="images/pic10.jpg" alt="" /></a>
										</article>
									</li>
									<li>
										<article>
											<header>
												<h3><a href="single.html">Magna enim accumsan tortor cursus ultricies</a></h3>
												<time class="published" datetime="2015-10-08">October 8, 2015</time>
											</header>
											<a href="single.html" class="image"><img src="images/pic11.jpg" alt="" /></a>
										</article>
									</li>
									<li>
										<article>
											<header>
												<h3><a href="single.html">Congue ullam corper lorem ipsum dolor</a></h3>
												<time class="published" datetime="2015-10-06">October 7, 2015</time>
											</header>
											<a href="single.html" class="image"><img src="images/pic12.jpg" alt="" /></a>
										</article>
									</li>
								</ul>
							</section>
							

<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>


	</body>
</html>