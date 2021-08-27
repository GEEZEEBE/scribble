<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<% pageContext.setAttribute( "newLine", "\n" ); %>

<!DOCTYPE HTML>
<html>
	<head>
		<title>Single - SCRIBBLE PAPERS</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
	</head>
	<body class="single is-preload">


<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>


	<!-- Main -->
		<div id="main">
	
			<!-- Post -->
				<article class="post">
					<header>
						<div class="title">
							<h2><a href="#">${vo.title}</a></h2>
						</div>
						<div class="meta">
							<time class="published" datetime="2015-11-01">${vo.reg_date}</time>
							<a href="#" class="author"><span class="name">${vo.name}</span><img src="images/avatar.jpg" alt="" /></a>
						</div>
					</header>
					<span class="image featured"><img src="filestorage/${vo.img_name}" alt="" /></span>
					<div class="view-content">
					 <p>${fn:replace(vo.content, newLine, "<br>")}</p> 
					</div>

					<footer>
						<c:if test="${authUser != null }">						
							<ul class="actions">
								<li><a href="main?a=modifyform&no=${vo.board_id}&page=${page}&keyword=${keyword}" class="button large">Modify</a></li>
								<li><a href="main?a=list" class="button large">Home</a></li>
							</ul>
						</c:if>

						<ul class="stats">
							<li><a href="#" class="icon solid fa-heart">${vo.hit}</a></li>
						<c:if test="${authUser != null }">	
							<li><a href="main?a=delete&no=${vo.board_id}&page=${page}&keyword=${keyword}"
									class="button small fit" onclick="return confirm('삭제하시겠습니까?')">DEL</a></li>
						</c:if>
						</ul>
					</footer>
				</article>
		</div>
	
			<!-- Reply -->
			<form method="post" action="main?a=reply&no=${vo.board_id}&page=${page}&keyword=${keyword}">
				<div class="row gtr-uniform">
					<div class="col-12">
						<textarea name="reply" id="demo-message" placeholder="Enter your reply" rows="6"></textarea>
					</div>
					<div class="col-12">
						<ul class="actions">
							<li><input type="submit" value="Done" /></li>
							<li><input type="reset" value="Cancel" /></li>
						</ul>
					</div>
				</div>
			</form>
		
		<div class="table-wrapper">
			<c:choose>
				<c:when test="${empty clist}">
					<ul class="alt">
						<li><h1>NO COMMENTS</h1></li>
						<li></li>
					</ul>
				</c:when>				
				<c:otherwise>
					<ul class="alt">
						<li><h1>COMMENTS</h1></li>
						<li></li>
					</ul>
					<table>
						<thead>
							<tr>
								<th>Name</th>
								<th>Comments</th>
								<th></th>
								<th>Date</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${clist}" var="cvo">
								<tr>
									<td>${cvo.name}</td>
									<td colspan="2">${fn:replace(cvo.content, newLine, "<br>")}</td>
									<td>${cvo.regDate}</td>
									<td>
										<c:if test="${authUser.user_id == cvo.userId}">
											<ul class="actions small">
												<li>
													<a href="main?a=cmtdelete&no=${boardNo}&page=${page}&keyword=${keyword}&cmtNo=${cvo.commentId}"
													class="button small" onclick="return confirm('삭제하시겠습니까?')">DEL</a>
												</li>
											</ul>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
		</div>



<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>


	</body>
	
<script>
	function confirmDel() {
		if (confirm("삭제하시겠습니까?")) {
			return true;
		} 
		return;
		
	}

</script>
	
	
	
	
</html>