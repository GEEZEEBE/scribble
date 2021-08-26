<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<title>Scribble : 회원가입 성공!</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" href="assets/css/main.css" />
</head>
<body>
	
	<div id="container">
	<c:import url="/WEB-INF/views/includes/header.jsp"></c:import><!-- /header -->	
		<div id="wrapper">
			<div id="content">
				<div id="user">
					<h3 class="jr-success" align="center">
						Welcome to Scribble!
						<br>
						회원가입을 축하합니다.
						<br>
						<br>
						<a href="/scribble/user?a=login">Login Now</a>
					</h3>
				</div><!-- /user -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
	</div> <!-- /container -->
<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import><!-- /footer -->
</body>
</html>