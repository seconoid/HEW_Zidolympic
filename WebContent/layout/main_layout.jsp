<!-- ページレイアウト -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf8"); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
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