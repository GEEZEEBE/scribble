<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
							<h2><a href="#">Magna sed adipiscing</a></h2>
							<p>Lorem ipsum dolor amet nullam consequat etiam feugiat</p>
						</div>
						<div class="meta">
							<time class="published" datetime="2015-11-01">November 1, 2015</time>
							<a href="#" class="author"><span class="name">Jane Doe</span><img src="images/avatar.jpg" alt="" /></a>
						</div>
					</header>
					<span class="image featured"><img src="images/pic01.jpg" alt="" /></span>
					<p>Mauris neque quam, fermentum ut nisl vitae, convallis maximus nisl. Sed mattis nunc id lorem euismod placerat. Vivamus porttitor magna enim, ac accumsan tortor cursus at. Phasellus sed ultricies mi non congue ullam corper. Praesent tincidunt sed tellus ut rutrum. Sed vitae justo condimentum, porta lectus vitae, ultricies congue gravida diam non fringilla.</p>
					<p>Nunc quis dui scelerisque, scelerisque urna ut, dapibus orci. Sed vitae condimentum lectus, ut imperdiet quam. Maecenas in justo ut nulla aliquam sodales vel at ligula. Sed blandit diam odio, sed fringilla lectus molestie sit amet. Praesent eu tortor viverra lorem mattis pulvinar feugiat in turpis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce ullamcorper tellus sit amet mattis dignissim. Phasellus ut metus ligula. Curabitur nec leo turpis. Ut gravida purus quis erat pretium, sed pellentesque massa elementum. Fusce vestibulum porta augue, at mattis justo. Integer sed sapien fringilla, dapibus risus id, faucibus ante. Pellentesque mattis nunc sit amet tortor pellentesque, non placerat neque viverra. </p>
					<footer>
						<ul class="stats">
							<li><a href="#">General</a></li>
							<li><a href="#" class="icon solid fa-heart">28</a></li>
							<li><a href="#" class="icon solid fa-comment">128</a></li>
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