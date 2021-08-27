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
							<p>Lorem ipsum dolor amet nullam consequat etiam feugiat</p>
						</div>
						<div class="meta">
							<time class="published" datetime="2015-11-01">${vo.reg_date}</time>
							<a href="#" class="author"><span class="name">${vo.name}</span><img src="images/avatar.jpg" alt="" /></a>
						</div>
					</header>
					<span class="image featured"><img src="images/pic01.jpg" alt="" /></span>
					<div class="view-content">
					 <p>${fn:replace(vo.content, newLine, "<br>")}</p> 
					</div>
					<footer>						
						<ul class="actions">
							<li><a href="main?a=modifyform&no=${vo.board_id}&page=${page}&keyword=${keyword}" class="button large">Modify</a></li>
						</ul>
						<ul class="stats">
							<li><a href="#">General</a></li>
							<li><a href="#" class="icon solid fa-heart">${vo.hit}</a></li>
						</ul>
					</footer>
				</article>
		</div>
	
			<!-- Reply -->
			<form method="post" action="#">
				<div class="row gtr-uniform">
					<div class="col-12">
						<textarea name="demo-message" id="demo-message" placeholder="Enter your reply" rows="6"></textarea>
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
			<table>
				<thead>
					<tr>
						<th>Name</th>
						<th>Description</th>
						<th>Price</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Item One</td>
						<td>Ante turpis integer aliquet porttitor.</td>
						<td>29.99</td>
					</tr>
					<tr>
						<td>Item Two</td>
						<td>Vis ac commodo adipiscing arcu aliquet.</td>
						<td>19.99</td>
					</tr>
					<tr>
						<td>Item Three</td>
						<td> Morbi faucibus arcu accumsan lorem.</td>
						<td>29.99</td>
					</tr>
					<tr>
						<td>Item Four</td>
						<td>Vitae integer tempus condimentum.</td>
						<td>19.99</td>
					</tr>
					<tr>
						<td>Item Five</td>
						<td>Ante turpis integer aliquet porttitor.</td>
						<td>29.99</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2"></td>
						<td>100.00</td>
					</tr>
				</tfoot>
			</table>
		</div>



<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>


	</body>
</html>