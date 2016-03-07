<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!-- main_layout.jspにコンテンツとタイトルを渡す -->
<c:import url="./layout/main_layout.jsp">
	<c:param name="title" value="シンクロジドリング"/>
	<c:param name="content">
	<!-- 非ログイン時はログイン画面に遷移 -->
	<c:if test="${ sessionScope.user == null }">
		<%
			request.setAttribute("loginErr", "ログインが必要です");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		%>
	</c:if>
	${mes}

<!-- カメラ -->
<script type="text/javascript" src="js/camera.js"></script>

<!-- 下の保存押したらフォームが起動する -->
   
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<div id="contents">
	<div id="maincapt">
		 <form action="/HEW_Zidolympic/SynchroULServlet" method="post" name="x">
	     <input type="hidden" id="h" name="h">
	     <input type="hidden" id="titleid" name="titleid" value="${title_id}">
	     <font color="#ffffff">${titlename}</font>
	     
	     
	     
	     
	   	 <input type="text" name="title" id="title" placeholder="画像タイトルを入力してね">
	   	 <input type="text" name="tag" id="tag" placeholder="タグはこちら" size="8">
	   	 <textarea placeholder="コメントをつけたい方はこちら" rows="2" cols="20" name="textarea"></textarea>
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
<c:if test="${!empty rank}">
<p>${tag}  このタグの現在のrankingは${rank}位です</p>
<div id="try_img">
	<table>
		<tr><td>${title}</td></tr>
		<tr><td><img src="/HEW_Zidolympic/UploadImages/${pass}" width="320" height="240">
	</td></tr></table>
</div>
 </c:if>
	</c:param>
</c:import>

</body>
</html>