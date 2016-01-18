<!-- ページレイアウト -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf8"); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>${param.title}</title>
</head>
<body>
<!-- ヘッダのインクルード -->
<c:import url="./layout/header.jsp" />
<div id="main">
	<!-- コンテンツの展開 -->
	 ${param.content}
</div>
<!-- フッタのインクルード -->
<c:import url="./layout/footer.jsp" />
</body>
</html>