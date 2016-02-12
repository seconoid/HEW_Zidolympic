<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>




<!-- main_layout.jspにコンテンツとタイトルを渡す -->
<c:import url="./layout/main_layout.jsp">
	<c:param name="title" value="スプラトゥーチ"/>
	<c:param name="content">
	${mes}
<!-- アップロード -->	
<form action="UploadImage" method="post" enctype="multipart/form-data">

<a href="http://localhost/PHP/test.php">ここはアパッチ</a>

アップロードはこちら：
<br>
<input type="file" name="filename" size="50" required>
<br>
<br>
<input type="submit" value="upload">
</form>
<!-- アップロード終了 -->



<!-- カメラ -->
<script type="text/javascript" src="http://jsdo.it/Yukisuke/y9Jv/js"></script>

<script type="text/javascript" src="http://jsdo.it/Yukisuke/c1VD/js"></script>

<script type="text/javascript" src="http://jsdo.it/Yukisuke/6SNU/js"></script>



<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
ビデオ<br />
<video id="video" autoplay width="320" height="240"></video><br />
<div id="wrapper"> 
    <button id="capture">capture</button>
    <button id="stop">stop</button>
    <form action="Upload?blob=${blob}" method="get" onsubmit="screenshot() return">
	<button id="aaaaaa">保存</button>
    </form>
</div>
<br />
<div id="capture_images" style="visibility:hidden">
画像<br />
    
    <img id="img01"  width="600" height="480" /><br />
    
    Canvas<br />
    
    
    <canvas id="canvas" width="600" height="480" ></canvas><br />
    
    <script type="text/javascript" src="./js/js.js"></script>
    
</div>
<!-- カメラ 終了-->
		<a href="index.jsp">いんでっくす</a>
	</c:param>
</c:import>




</body>
</html>