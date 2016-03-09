<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="css/screen.css" media="all" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<link href="css/html5-doctor-reset-stylesheet.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="css/jquery.bxslider.css" rel="stylesheet" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="js/jquery.bxslider.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
$('.bxslider').bxSlider({
auto: true,
});
});
 </script>
 
 
 <script type="text/javascript">
$(function(){
	$('#loopslider').each(function(){
		var loopsliderWidth = $(this).width();
		var loopsliderHeight = $(this).height();
		$(this).children('ul').wrapAll('<div id="loopslider_wrap"></div>');

		var listWidth = $('#loopslider_wrap').children('ul').children('li').width();
		var listCount = $('#loopslider_wrap').children('ul').children('li').length;

		var loopWidth = (listWidth)*(listCount);

		$('#loopslider_wrap').css({
			top: '0',
			left: '0',
			width: ((loopWidth) * 2),
			height: (loopsliderHeight),
			overflow: 'hidden',
			position: 'absolute'
		});

		$('#loopslider_wrap ul').css({
			width: (loopWidth)
		});
		loopsliderPosition();

		function loopsliderPosition(){
			$('#loopslider_wrap').css({left:'0'});
			$('#loopslider_wrap').stop().animate({left:'-' + (loopWidth) + 'px'},25000,'linear');
			setTimeout(function(){
				loopsliderPosition();
			},25000);
		};

		$('#loopslider_wrap ul').clone().appendTo('#loopslider_wrap');
	});
});
</script>

<!-- main_layout.jspにコンテンツとタイトルを渡す -->
<c:import url="./layout/main_layout.jsp">
	<c:param name="title" value="ジドリンピック"/>
	<c:param name="content">
		<div class="mv-area">
			<img src="./images/material.svg"  class="up-frame" />
			<img src="./images/mv.png" alt="" class="mv" />
			<img src="./images/material.svg"  class="down-frame" />
			<img src="./images/material.svg"  class="up-frame2" />
		</div>
	</c:param>
</c:import>