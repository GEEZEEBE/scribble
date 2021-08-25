<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<title>Scribble : 로그인</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" href="assets/css/main.css" />
</head>
<body>

	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import><!-- /header -->	
		<div id="wrapper">
			<div id="content">
				<div id="user">
					
					<form id="login-form" name="loginform" method="post" action="/scribble/user">
						<input type="hidden" name="a" value="login" /> 
						
						<label class="block-label" for="email">Email 이메일</label> 
						<input id="email" name="email" type="text" value=""> 
						<br>
						<label class="block-label">Password 비밀번호</label> 
						<input name="password" type="password" value="">
						
								<br>
								<c:if test="${param.result eq 'fail' }">
								<h3 align="center">로그인이 실패했습니다. 다시입력해주세요</h3>
								</c:if>
						<br>
						<h2 align="center">
							<input type="submit" value="로그인">
						</h2>
					</form>
					
				</div><!-- /user -->
			</div><!-- /content -->
		</div><!-- /wrapper -->`
	</div> <!-- /container -->
	<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/browser.min.js"></script>
			<script src="assets/js/breakpoints.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>
</body>
</html>