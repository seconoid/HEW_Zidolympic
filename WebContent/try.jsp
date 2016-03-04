<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 非ログイン時はログイン画面に遷移 -->
<c:if test="${ sessionScope.user == null }">
<%
	request.setAttribute("loginErr", "ログインが必要です");
	request.getRequestDispatcher("login.jsp").forward(request, response);
%>
</c:if>
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
		 <form action="/HEW_Zidolympic/UploadImage" method="post" name="x">
	     <input type="hidden" id="h" name="h">
	     <input type="hidden" id="titleid" name="titleid" value="${title_id}">
	     <font color="#ffffff">${titlename}</font>
	     
	     
	     
	     
	   	 <input type="text" name="title" id="title" placeholder="画像タイトルを入力してね">
	     </form>
			<video id="video" autoplay width="320" height="240"></video>
		    <button id="capture">capture</button>
		    <!-- 保存押したら上のフォームが起動する -->
		    
		    <c:if test="${save == null||page_out.size()<3}" >
			<button id="hozon" onClick="kakunin()">ほぞん</button>
		    <c:if test="${page_out.size()>=3}" >
			<button id="hozon">保存できません</button>
			</c:if>
			
			</c:if>${save}
		    
		    ${revenge}
		    <!-- 保存を押されたらアップロードできるようにしないといけない -->
	</div>
	
	<div id="capture_images" style="visibility:hidden">
			<p class="text_box">いま撮った画像です。よければ「保存」を押して</p><!-- 表示用 -->
			<img id="img02" width="320" height="240"/>
		    <img id="img01" width="600" height="480" hidden="img01"/><!-- 画像化用 -->
		    <canvas id="canvas" width="600" height="480" hidden="canvas"></canvas><!-- 画像化用 -->
		    <script type="text/javascript" src="./js/js.js"></script>   
	</div>
</div>
<!-- カメラ 終了-->
<c:if test="${fn:length(page_out)>0}">
<p>保存された画像たちです</p>
<div id="try_img">
	<c:forEach var="a" items="${ page_out }">
	<table>
		<tr><td>${a.title}</td></tr>
		<tr><td><img src="/HEW_Zidolympic/UploadImages/${a.pass}" width="320" height="240">
	</td></tr></table>
	</c:forEach>
</div>
 </c:if>
</c:param>
</c:import>


</body>
</html>