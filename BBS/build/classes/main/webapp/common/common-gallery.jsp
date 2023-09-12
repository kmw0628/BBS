<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="container">
	<div id="myCarousel" class="carousel slide" data-rid="carousel">
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to=0 class="active"></li>
			<li data-target="#myCarousel" data-slide-to=1></li>
			<li data-target="#myCarousel" data-slide-to=2></li>
		</ol>
		
		<div class="carousel-inner">
		 	<div class="item active">
		 		<img src="images/Ramjwi_Fire.jpg">
		 	</div>
		 	<div class="item">
		 		<img src="images/Ramjwi_Thunder.jpg">
		 	</div>
		 	<div class="item">
		 		<img src="images/Ramjwi_Water.jpg">
		 	</div>
		</div>
		 
		<a class="left carousel-control" href="#myCarousel" data-slide="prev">
			<span class="glyphicon glyphicon-chevron-left"></span>
		</a>
		<a class="right carousel-control" href="#myCarousel" data-slide="next">
			<span class="glyphicon glyphicon-chevron-right"></span>
		</a>
	</div>
</div>