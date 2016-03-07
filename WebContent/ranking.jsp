<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/HEW_Sample/dist/css/lightbox.min.css">
<!-- main_layout.jspにコンテンツとタイトルを渡す -->
<c:import url="./layout/main_layout.jsp">
	<c:param name="title" value=""/>
	<c:param name="content">
	
	
	<div>
	<h1>${trylist[0].comname}</h1>
	<c:forEach items="${trylist}" var="t">
			<a class="example-image-link" href="/HEW_Zidolympic/UploadImages/${t.img_pass}"
				data-lightbox="example-1"> <img class="example-image"
				src="/HEW_Zidolympic/UploadImages/${t.img_pass}" alt="image-1" /></a>
	</c:forEach>
	</div>
	
	<div>
	<h1>${synchrolist[0].comname}</h1>
	<c:forEach items="${synchrolist}" var="s">
			<a class="example-image-link" href="/HEW_Zidolympic/UploadImages/${s.img_pass}"
				data-lightbox="example-1"> <img class="example-image"
				src="/HEW_Zidolympic/UploadImages/${s.img_pass}" alt="image-1" /></a>
	</c:forEach>
	</div>
	
	<div>
	<h1>${splatorchlist[0].comname}</h1>
	<c:forEach items="${splatorchlist}" var="d">
			<a class="example-image-link" href="/HEW_Zidolympic/UploadImages/${d.img_pass}"
				data-lightbox="example-1"> <img class="example-image"
				src="/HEW_Zidolympic/UploadImages/${d.img_pass}" alt="image-1" /></a>
	</c:forEach>
	</div>
	
	
	
	
	
	
	
		<script src="/HEW_Zidolympic/dist/js/lightbox-plus-jquery.min.js"></script>
	
	
	
	
	</c:param>
</c:import>