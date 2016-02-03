<!-- ページレイアウト -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf8"); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<!-- bootstrap テンプレート -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="Content-Style-Type" content="text/javascript" />
<link href="./bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="./css/common.css" />
<!--[if lt IE 9]>
	<script src="js/html5shiv.js"></script>
	<script src="js/respond.min.js"></script>
<![endif]-->
<!-- bootstrap ここまで  -->
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

<!-- jsファイル -->
<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="./bootstrap/js/bootstrap.min.js"></script>
<!--  js ここまで -->
</body>
</html>