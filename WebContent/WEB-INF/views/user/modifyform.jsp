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
						<h3 class="blind" align="center"> ${SuserVo.email } 's </h3>
						<br>
						<div class="row gtr-uniform">
							<div class="col-12-large">
									<input id="name" name="name" type="text"  value="${SuserVo.name }" />
                            </div>
							<br>
                            <div class="col-12-large">         
                                    <input id="password" name="password" type="password" maxlength="16" placeholder="PASSWORD" value="" />
                            </div>
                      	</div>
                            <br>
                            <div class="row">
                				<div class="col-6 col-12-large">
                    				<ul class="actions stacked">
                       					<li>
                            				<input class="button fit" type="submit" value="Completed">
										</li>
										 <br>
										<li>
											 <h4 align="center" style="color: #ff6666; font-weight: bold"> Delete My Information </h4>
                            				<a href="/scribble/user?a=delete&user_id=${SuserVo.user_id }" class="button fit">DELETE</a>
                        				</li>
                    				</ul>
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
