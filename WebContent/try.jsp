<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>




<!-- main_layout.jspにコンテンツとタイトルを渡す -->
<c:import url="./layout/main_layout.jsp">
	<c:param name="title" value="トライジドリング"/>
	<c:param name="content">
	${mes}

<!-- カメラ -->
<script type="text/javascript" src="js/camera.js"></script>

<!-- 下の保存押したらフォームが起動する -->
   
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<div id="contents">
	<div id="maincapt">
		 <form	 action="UploadImage" method="post" name="x">
	     <input type="hidden" id="h" name="h">
	   	 <input type="text" name="title" id="title" placeholder="画像タイトルを入力してね">
	     </form>
			<video id="video" autoplay width="320" height="240"></video>
		    <button id="capture">capture</button>
		    <!-- 保存押したら上のフォームが起動する -->
		    <button id="hozon" onClick="kakunin()">ほぞん</button>
			<p class="text_box">buttonを押すとテキストが変わる</p>
		    <!-- 保存を押されたらアップロードできるようにしないといけない -->
	</div>
	
	<div id="capture_images" style="visibility:hidden">
			<p>いま撮った画像です。よければ「保存」を押して</p><!-- 表示用 -->
			<img id="img02" width="320" height="240"/>
		    <img id="img01" width="600" height="480" hidden="img01"/><!-- 画像化用 -->
		    <canvas id="canvas" width="600" height="480" hidden="canvas"></canvas><!-- 画像化用 -->
		    <script type="text/javascript" src="./js/js.js"></script>   
	</div>
</div>
<!-- カメラ 終了-->

<a href="index.jsp">いんでっくす</a>
</c:param>
</c:import>


</body>
</html>