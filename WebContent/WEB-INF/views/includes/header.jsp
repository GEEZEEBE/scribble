<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Header -->
					<header id="header">
						<h1><a href="/scribble/index.jsp">SCRIBBLE PAPERS</a></h1>
						<nav class="links">
							<ul>
								<li><a href="#">SCRIBBLE</a></li>
								<li><a href="#">SIGNUP</a></li>
								<li><a href="#">LOGIN</a></li>
							</ul>
						</nav>
						<nav class="main">
							<ul>
								<li class="search">
									<a class="fa-search" href="#search">Search</a>
									<form id="search" method="get" action="#">
										<input type="text" name="query" placeholder="Search" />
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
								<form class="search" method="get" action="#">
									<input type="text" name="query" placeholder="Search" />
								</form>
							</section>

						<!-- Links -->
							<section>
								<ul class="links">
									<li>
										<a href="#">
											<h3>Scribble</h3>
											<p>아무거나 주절여봐요</p>
										</a>
									</li>
									<li>
										<a href="#">
											<h3>Signup</h3>
											<p>회원가입</p>
										</a>
									</li>
								</ul>
							</section>

						<!-- Actions -->
							<section>
								<ul class="actions stacked">
									<li><a href="#" class="button large fit">Log In</a></li>
								</ul>
							</section>

					</section>



		<!-- Header -->
		<header id="header">
			<h1><a href="/scribble/main">SCRIBBLE PAPERS</a></h1>
				<nav class="links">
					<ul>
					<c:choose>
					<c:when test="${authUser == null }">
						<!-- 로그인 전 -->
						<li><a href="/scribble/main">Scribble</a></li>
						<li><a href="/scribble/user?a=joinform">SignUp</a></li>
						<li><a href="/scribble/user?a=login">Login</a></li>
					</c:when>
					<c:otherwise>
						<!-- 로그인 후 -->
						<li><a href="/scribble/main">Scribble</a></li>
						<li><a href="/scribble/user?a=modifyform">Modify My Info</a></li>
						<li><a href="/scribble/user?a=logout">Logout</a></li>
					</c:otherwise>
					</c:choose>
					</ul>
				</nav>
				<nav class="main">
					<ul>
						<li class="search">
							<a class="fa-search" href="#search">Search</a>
							<form id="search" method="get" action="#">
								<input type="text" name="query" placeholder="Search" />
							</form>
						</li>
						<li class="menu"><a class="fa-bars" href="#menu">Menu</a></li>
					</ul>
				</nav>
		</header>
		
		<!-- Menu -->
			<section id="menu">

			<!-- Search -->
				<section>
					<form class="search" method="get" action="#">
						<input type="text" name="query" placeholder="Search" />
					</form>
				</section>

			<!-- Links -->
				<section>
					<ul class="links">
						<li>
							<a href="/scribble/main">
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
						<li><a href="/scribble/user?a=login" class="button large fit">LogIn</a></li>
						</c:when>
						<c:otherwise>
							<!-- 로그인 후 -->	
						<li><a href="/scribble/user?a=logout" class="button large fit">Logout</a></li>
						</c:otherwise>
					</c:choose>
					</ul>
				</section>
							
			</section>
