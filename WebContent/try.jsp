<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" href="./css/take_photo.css">

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
	<c:if test="${page_out.size()==null || page_out.size()==0}" >
	<div class="count-box count1">
		お題①：${titlename}
	</div>
	</c:if>
	<c:if test="${page_out.size()==1}" >
	<div class="count-box count2">
		お題②：${titlename}
	</div>
	</c:if>
	<c:if test="${page_out.size()==2}" >
	<div class="count-box count3">
		お題③：${titlename}
	</div>
	</c:if>
	<c:if test="${page_out.size()>=3}" >
	<div class="count-box count4">
		競技終了！
	</div>
	</c:if>
	<c:if test="${page_out.size()<3 || page_out.size() == null}" >
	<div id="maincapt">
		 <form action="/HEW_Zidolympic/UploadImage" method="post" name="x">
	     <input type="hidden" id="h" name="h">
	     <input type="hidden" id="titleid" name="titleid" value="${title_id}">
	     <!-- iphon -->
	     <img src="./images/iphone.svg" width="580" height="580" class="iphone">
	   	  <input type="hidden" name="title" id="title">
	   	  
	     </form>
			<video id="video" autoplay width="320" height="240" class="video-area"></video>
			<div class="take-area">
			    <button id="capture" class="btn btn-info taking-btn">撮影！</button>
		    </div>
		    <!-- 保存押したら上のフォームが起動する -->
		    <!-- 保存を押されたらアップロードできるようにしないといけない -->
	</div>
	<div id="capture_images" style="visibility:hidden">
			<img id="img02" width="320" height="240" class="taked-pic"/>
		    <img id="img01" width="600" height="480" hidden="img01"/><!-- 画像化用 -->
		    <div class="taked">
			    <canvas id="canvas" width="600" height="480" hidden="canvas"></canvas><!-- 画像化用 -->
		    </div>
		    <c:if test="${save == null||page_out.size()<2}" >
			<button id="hozon" onClick="kakunin()" class="btn btn-success next-btn">次のお題</button>
			</c:if>
			<c:if test="${page_out.size()==2}" >
			<button id="hozon" onClick="kakunin()" class="btn btn-primary next-btn">FINISH!</button>
			</c:if>
		    <script type="text/javascript" src="./js/js.js"></script>   
	</div>
	</c:if>
	<!-- カメラ 終了-->
	<c:if test="${fn:length(page_out)==3}">
		<div class="result-score">スコア：${score}</div>
		<div class="try-result-photo">
			<div class="row">
			<c:forEach var="a" items="${ page_out }">
				<div class="col-xs-4">
					<p>${a.title_name}</p>
					<img src="/HEW_Zidolympic/UploadImages/${a.pass}" class="img-responsive">
				</div>
			</c:forEach>
			</div>
			<div class="row">
				<div class="col-xs-offset-3 col-xs-6">
					<div class="update-form">
						<form action="/HEW_Zidolympic/TryUpdateServlet" method="post">
							<div class="form-group">
								<input type="text" name="img_title" placeholder="タイトル" class="form-control">
							</div>
							<div class="form-group">
								<textarea cols="20"rows="4" name="comment" placeholder="コメント" class="form-control"></textarea>
							</div>
							<div class="use-point">
								消費ポイント： <span style="color:orange">150pt</span>
							</div>
							<div class="form-group">
								<div class="text-center">
									<button class="btn btn-primary text-right photo_btn">投稿</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	 </c:if>
 </div>
</c:param>
</c:import>


</body>
</html>