<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!-- Wrapper -->
	<div id="wrapper">

		<!-- Header -->
		<header id="header">
			<h1><a href="/scribble/main">SCRIBBLE PAPERS</a></h1>
				<nav class="links">
					<ul>
					<c:choose>
						<c:when test="${authUser == null }">
							<!-- 로그인 전 -->
							<li><a href="main?a=writeform">Scribble</a></li>
							<li><a href="user?a=joinform">SignUp</a></li>
							<li><a href="user?a=loginform">Login</a></li>
						</c:when>
						<c:otherwise>
							<!-- 로그인 후 -->
							<li><a href="main?a=writeform">Scribble</a></li>
							<li><a href="user?a=modifyform">Modify My Info</a></li>
							<li><a href="user?a=logout">Logout</a></li>
						</c:otherwise>
					</c:choose>
					</ul>
				</nav>
				<nav class="main">
					<ul>
						<li class="search">
							<a class="fa-search" href="#search">Search</a>
							<form id="search" method="post" action="main?a=list">
								<input type="text" name="keyword" placeholder="Search" />
							</form>
						</li>
						<li class="menu">
							<a class="fa-bars" href="#menu">Menu</a>
						</li>
					</ul>
				</nav>
		</header>
		
		<!-- Menu -->
			<section id="menu">

			<!-- Search -->
				<section>
					<form class="search" method="post" action="main?a=list">
						<input type="text" name="keyword" placeholder="Search" />
					</form>
				</section>

			<!-- Links -->
				<section>
					<ul class="links">
						<li>
							<a href="/scribble/main?a=writeform">
								<h3>Scribble</h3>
								<p>아무거나 주절여봐요</p>
							</a>
						</li>
					<c:choose>
						<c:when test="${authUser == null }">
							<!-- 로그인 전 -->
							<li><a href="/scribble/user?a=joinform">
                            	<h3>SignUp</h3>
								<p>회원가입</p></a>
                        	</li>
						</c:when>
						<c:otherwise>
							<!-- 로그인 후 -->
							<li><a href="/scribble/user?a=modifyform">
                            	<h3>Modify My Info<h3>
                            	<p>회원정보수정</p></a>
                            </li>
						</c:otherwise>
					</c:choose>
					</ul>
				</section>

			<!-- Actions -->
				<section>
					<ul class="actions stacked">
					<c:choose>
						<c:when test="${authUser == null }">
							<!-- 로그인 전 -->
						<li><a href="/scribble/user?a=loginform" class="button large fit">LogIn</a></li>
						</c:when>
						<c:otherwise>
							<!-- 로그인 후 -->	
						<li><a href="/scribble/user?a=logout" class="button large fit">Logout</a></li>
						</c:otherwise>
					</c:choose>
					</ul>
				</section>
							
			</section>
