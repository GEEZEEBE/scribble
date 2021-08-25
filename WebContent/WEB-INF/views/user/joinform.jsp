<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<title>Scribble : 회원가입</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
	</head>
</head>
<body>

	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import><!-- /header -->	
		<div id="wrapper">
			<div id="content">
				<div id="user">
	
					<form id="join-form" name="joinForm" method="post" action="/scribble/user">
					  	<input type="hidden" name="a" value="join">
					  						  	
			  			<h2 class="blind" align="center">Scribble 회원가입</h2>
						<br>
						<label class="block-label" for="email">Email 이메일 (필수)</label>
						<input id="email" name="email" type="text" value="">
						<br>
						<label class="block-label" for="name">Name 이름 (필수)</label>
						<input id="name" name="name" type="text" value="">
						<br>
						<label class="block-label">Password 비밀번호 (필수)</label>
						<input name="password" type="password" value="">
						<br>
						<ul class="terms_bx_list">
                            <li class="terms_bx">
									<span class="input_chk">
										<input type="checkbox" id="termsService" name="termsService" class="chk">
										<label for="termsService">이용약관 동의<span class="terms_necessary">(필수)</span></label>
									</span>
                             </li>
                        </ul>
						
						<h2 align="center">
							<input type="submit" value="가입하기">
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