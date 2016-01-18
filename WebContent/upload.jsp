<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>

<html>
<head>


<meta http-equiv="content-style-type" content="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<html>

<!-- main_layout.jspにコンテンツとタイトルを渡す -->
<c:import url="./layout/main_layout.jsp">
	<c:param name="title" value="アップロード"/>
	<c:param name="content">

<form action="UploadImage" method="post" enctype="multipart/form-data">
画像ファイル：
<br>
<input type="file" name="filename" size="50" required>
<br>
<br>
<input type="submit" value="upload">
</form>



		<a href="index.jsp">いんでっくす</a>
	</c:param>
</c:import>
</body></html>
