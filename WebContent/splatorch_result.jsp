<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="/HEW_Sample/dist/css/lightbox.min.css">

<c:import url="./layout/main_layout.jsp">
	<c:param name="title" value="" />
	<c:param name="content">
	
	
	<h1>あなたの点数は${ grayCt }です</h1>
		<div>
				<a><img src="/HEW_Zidolympic/WebContent/UploadImages/${ filename }" alt="splatorch" /></a>
		</div>
	    <form action="SplatorchUpdata" method="post" name="splatorch">
	    <textarea placeholder="コメントをつけたい方はこちら" rows="2" cols="20" name="commnet"></textarea>
	    <input type="submit" value="投稿完了">
	    <p>${ filename }</p>
	    </form>
		<script src="/HEW_Zidolympic/dist/js/lightbox-plus-jquery.min.js"></script>

	</c:param>
</c:import>