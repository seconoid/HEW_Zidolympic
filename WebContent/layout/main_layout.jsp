<!-- ページレイアウト -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf8"); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./css/common.css" />
<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
<title>ジドリンピック | ${param.title}</title>
</head>
<body>
<!-- ヘッダのインクルード -->
<c:import url="./layout/header.jsp" />
<div class="wrapper">
	<div class="contents">
		<!-- コンテンツの展開 -->
		 ${param.content}
	</div>
</div>
<!-- フッタのインクルード -->
<c:import url="./layout/footer.jsp" />
</body>
</html>