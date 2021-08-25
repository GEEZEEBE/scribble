<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<% pageContext.setAttribute( "newLine", "\n" ); %>

<%@ page import="com.scribble.dao.*"%>
<%@ page import="com.scribble.vo.*"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
	<title>Scribble : 회원정보수정</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" href="assets/css/main.css" />
</head>
<body>

	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import><!-- /header -->	
		<div id="wrapper">
			<div id="content">
				<div id="user">
					
					<form id="join-form" name="joinForm" method="post" action="/scribble/user">
						<input type="hidden" name="a" value="modify">
						
						<label class="block-label" for="name">Name 이름</label>

						<br>
						<label class="block-label" for="email">Email 이메일</label>
						<strong>${suserVo.email }</strong>
						<br>
						<br>
						<label class="block-label">Password 비밀번호</label>
						<input name="password" type="password" value="" />
						<br>
						<h2 align="center">
						<input type="submit" value="수정완료">
						</h2>

					</form>
				</div><!-- /user -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
	</div> <!-- /container -->
	<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/browser.min.js"></script>
			<script src="assets/js/breakpoints.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>
</body>
</html>
