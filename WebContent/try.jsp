<<<<<<< HEAD
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
    <canvas id="canvas" width="600" height="480" hidden="canvas"></canvas><br />    
    <script type="text/javascript" src="./js/js.js"></script>
    
</div>
<!-- カメラ 終了-->
		<a href="index.jsp">いんでっくす</a>
	</c:param>
</c:import>
</body>
=======
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
    <form action="UploadImage" method="post" name="x">
    <input type="hidden" id="h" name="h">
   <input type="text" name="title" id="title"placeholder="画像タイトルを入力してね">
    </form>

<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<table width="500" height="300" align="left">
<tr>
<th align="center">ここにお題いれる<th>
</tr>
<tr>
<th><video id="video" autoplay width="320" height="240"></video></th>
</tr>
<tr>
<th align="center"> 
    <button id="capture">capture</button>
    <!-- 保存押したら上のフォームが起動する -->
	<button  onClick="kakunin()">ほぞん</button>
 </th></tr><tr><th><p class="text_box">buttonを押すとテキストが変わる</p></th></tr>
    <!-- 保存を押されたらアップロードできるようにしないといけない -->
    
</table>

<div id="capture_images" style="visibility:hidden">
<table width="500" height="300"align="right">
<tr><th>いま撮った画像です。よければ「保存」を押して</th></tr><!-- 表示用 -->
<tr><th><img id="img02"  width="320" height="240" /></th></tr>
    </table>
    
    <img id="img01"  width="600" height="480" hidden="img01"/><br /><!-- 画像化用 -->
    <canvas id="canvas" width="600" height="480" hidden="canvas"></canvas><br /><!-- 画像化用 -->
    <script type="text/javascript" src="./js/js.js"></script>
    
</div>
<!-- カメラ 終了-->
		<a href="index.jsp">いんでっくす</a>
	</c:param>
</c:import>




</body>
>>>>>>> 1e38b4ff6c01ed75afb71175212c1023bb45e873
</html>