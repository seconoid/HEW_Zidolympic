<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/HEW_Zidolympic/dist/css/lightbox.min.css">
<link rel="stylesheet" href="./css/take_photo.css">
<!-- main_layout.jspにコンテンツとタイトルを渡す -->
<c:import url="./layout/main_layout.jsp">
	<c:param name="title" value="投稿完了"/>
	<c:param name="content">

<div class="comfirm-box">
	<div class="posted text-center">投稿が完了しました！</div>
	<div class="row">
		<div class="col-xs-6">
			<div class="confirm text-center"><a href="MyServlet">マイページへ移動</a></div>
		</div>
		<div class="col-xs-6">
			<div class="confirm text-center"><a href="/HEW_Zidolympic/WebContent/compelist.jsp">別の競技へ参加する</a></div>
		</div>
	</div>
</div>

	</c:param>
</c:import>