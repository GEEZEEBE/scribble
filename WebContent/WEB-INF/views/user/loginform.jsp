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
						
						<h2 class="blind" align="center">Login</h2>
			  			<h3 class="blind" align="center">로그인</h3>
						<br> 
						<h4 class="blind" align="center">Input your information.</h4>
						<br> 
						<input id="email" name="email" placeholder="EMAIL" type="text" value=""> 
						<br>
						<input name="password" type="password" placeholder="PASSWORD" value="">
						
								<br>
								<c:if test="${param.result eq 'fail' }">
								<h3 align="center" style="color: #ff6666">로그인이 실패했습니다. 다시입력해주세요.</h3>
								</c:if>
						<br>
						<h2 align="center">
							<input type="submit" value="Login">
						</h2>
					</form>
					
				</div><!-- /user -->
			</div><!-- /content -->
		</div><!-- /wrapper -->`
	</div> <!-- /container -->
<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import><!-- /footer -->
</body>
</html>