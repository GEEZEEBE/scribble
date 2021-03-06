<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<title>Scribble : 회원가입</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" type="text/css"/>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import><!-- /header -->	
		<div id="wrapper">
			<div id="content">
				<div id="user">
	
					<form id="join-form" name="joinForm" method="post" action="/scribble/user?a=join">
					  						  	
			  			<h2 class="blind" align="center">SignUp</h2>
			  			<h3 class="blind" align="center">회원가입</h3>
			  			<br> 
						<h4 class="blind" align="center">Create your New Account !</h4>
						<br> 
						<div class="row gtr-uniform">
							<div class="col-12">
                             	<input type="text" id="name" name="name" placeholder="NAME" value="">                     
                            </div>
                            <br>
							<div class="col-12">
								<label class="block-label" for="email"></label>
								<input type="text" id="email" name="email" placeholder="EMAIL" value="">

	                        </div>
              				<div class="col-6 col-12-large">
                  				<ul class="actions stacked">
                     				<li>
                     					<input class="button fit" type="button" id="CheckId" value="Email Check">
										<p id="notice"></p>
                        	 		</li> 
								</ul>
							</div>
                            <div class="col-12">         
                                  <input id="password" name="password" type="password" maxlength="16" placeholder="PASSWORD" value="">
                            </div>
                        </div>
                        <br>
						<fieldset class="blind" align="center">
							<legend style="color: #2ebaae; font-weight: bold">[ 약관동의 ]</legend>	
							<input id="agree-prov" type="checkbox" name="agreeProv" value="y" />
							<label for="agree-prov" style="color: #2ebaae">서비스 약관에 동의합니다.</label>
						</fieldset>	
							<h3 class="blind" align="center">
							<input type="submit" value="SignUp" id="submitBtn" disabled="true" />
							</h3>
					</form>
				</div><!-- /user -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import><!-- /footer -->
	</div> <!-- /container -->
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
	   					$("#notice").css("color","#ff6666").text("이미 사용 중인 아이디입니다.");
	   				} else if ($("#email").val() == '') {
	   					isValidId = false;
	   					$("#notice").css("color","#ff99ff").text("이메일을 입력하세요.");
	   				} else {
	   					isValidId = true;
	   					$("#notice").css("color","#2ebaae").text("사용 가능한 아이디입니다.");
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