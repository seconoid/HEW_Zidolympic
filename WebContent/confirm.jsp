<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="/HEW_Sample/dist/css/lightbox.min.css">
<!-- main_layout.jspにコンテンツとタイトルを渡す -->
<c:import url="./layout/main_layout.jsp">
	<c:param name="title" value="投稿完了"/>
	<c:param name="content">
	
	${ mes }
	
	<p class="confirm"><a href="/HEW_Zidolympic/WebContent/mypage.jsp">マイページへ移動</a></p>
	<p class="confirm"><a href="/HEW_Zidolympic/WebContent/compelist.jsp">別の競技へ参加してみる</a></p>
	
	</c:param>
</c:import>