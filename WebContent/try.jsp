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
<script type="text/javascript" src="http://jsdo.it/Yukisuke/y9Jv/js"></script>

<script type="text/javascript" src="http://jsdo.it/Yukisuke/c1VD/js"></script>

<script type="text/javascript" src="http://jsdo.it/Yukisuke/6SNU/js"></script>



<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
ビデオ<br />
<video id="video" autoplay width="320" height="240"></video><br />
<div id="wrapper"> 
    <button id="capture">capture</button>
    <!-- <button id="stop">stop</button> -->
    
    <!-- 下の保存押したらフォームが起動する -->
    <form action="UploadImage" method="post" name="x">
    <input type="hidden" id="h" name="h">
    </form>
    
    <!-- 保存押したら上のフォームが起動する -->
	<button  onClick="kakunin()">ほぞん</button>
    <p class="text_box">buttonを押すとテキストが変わる</p>
    <!-- 保存を押されたらアップロードできるようにしないといけない -->
    
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