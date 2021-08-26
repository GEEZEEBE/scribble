<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<% pageContext.setAttribute( "newLine", "\n" ); %>

<%@ page import="com.scribble.dao.SuserDaoImpl"%>
<%@ page import="com.scribble.dao.SuserDao"%>
<%@ page import="com.scribble.vo.SuserVo"%>
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
						
						<h2 class="blind" align="center">Modify</h2>
			  			<h3 class="blind" align="center">회원정보수정</h3>
						<br>  
						<div align="right">
							<a href="/scribble/user?a=delete&user_id=${SuserVo.user_id }">회원탈퇴</a>
						</div>
						<br>
						<h3 class="blind" align="left"> ${SuserVo.email } 's </h3>
						<br>
						<div class="form col-md-6">
							<div class="form-row">
							<div class="fixAlignment">
									<input id="name" name="name" type="text"  value="${SuserVo.name }" /></div>
                            </div>
							<br>
                            <div class="form-row">
                            <div class="fixAlignment">
                                    <input id="password" name="password" type="password" maxlength="16" placeholder="PASSWORD" value="" />
                            </div>
                            </div>
                            <br>
                            <div align="center">
							<input type="submit" value="Completed">
							<div class="bottom">
							</div>
							</div>
                        </div>
	
						<!-- /주소 접근 방지용 정보 -->
						<input id="user_id" name="user_id" type="hidden" value="${SuserVo.user_id }" />
						
					</form>
				</div><!-- /user -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
	</div> <!-- /container -->
<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import><!-- /footer -->	
</body>
</html>
