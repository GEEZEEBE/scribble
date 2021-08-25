<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<title>Scribble : 회원가입</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
						<label class="block-label" for="email">Email (필수)</label>
						<input id="email" name="email" type="text" value="">
						<input type="button" id="CheckId" value="Email 중복체크">
						<p id="notice"></p>
						<br>
						<label class="block-label" for="name">Name (필수)</label>
						<input id="name" name="name" type="text" value="">
						<br>
						<label class="block-label">Password (필수)</label>
						<input name="password" type="password" value="">
						<br>
						<fieldset>
							<legend>약관동의</legend>
							<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
							<label>서비스 약관에 동의합니다.</label>
						</fieldset>
						
						<input type="submit" value="가입하기" id="submitBtn" disabled="true">
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
<script>

	var isValidId = false;
	$(document).ready(function (){   
	   $("#CheckId").on("click", function(event) {
	   var params = $("form").serialize(); 		// 폼태그의 모든 파라미터 저장
	   
	   		$.ajax({
	   			url:"/scribble/user?a=checkid", 	// request 할 servlet 주소
	   			dataType:"json",
	   			type:"post",
	   			data:params, 					// 전달할 파라미터 
	   			cache:false, 					// 같은 요청이 있으면 db에 있는 것 바로 뿌리겠다
	   			success:function(data) { 		// 서버에서 response 성공시 수행하는 함수
	   				// alert(data.count); 		// {"count":0} or {"count":1}
	   				if (data.count == 1) {
	   					isValidId = false;
	   					$("#notice").css("color","red").text("이미 사용 중인 아이디입니다.");
	   				} else if ($("#email").val() == '') {
	   					isValidId = false;
	   					$("#notice").css("color","violet").text("이메일을 입력하세요.");
	   				} else {
	   					isValidId = true;
	   					$("#notice").css("color","blue").text("사용 가능한 아이디입니다.");
	   				}
	   			}
	   		}); // ajax
		}); // click
	}); // ready

	$("#name, #email, #password").on("input change", function() {
		if ($("#name").val() == "" || $("#email").val() == "" || $("#password").val() == "") {
			$("#submitBtn").prop("disabled", true);
		} else {
			if (isValidId) {
				$("#submitBtn").prop("disabled", false);
			}
		}
	});
	
	$("#submitBtn").click(function() {
		var rv = true;
		if (!$("#agree-prov").is(":checked")) {
			alert("서비스 약관에 동의해주세요.")
			return rv = false;
		}
		return rv;
	});

</script>
</html>