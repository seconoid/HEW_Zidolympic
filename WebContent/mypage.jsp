<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- main_layout.jspにコンテンツとタイトルを渡す -->
<c:import url="./layout/main_layout.jsp">
	<c:param name="title" value="マイページ"/>
	<c:param name="content">
	<!-- 非ログイン時はログイン画面に遷移 -->
	<c:if test="${ sessionScope.user == null }">
		<%
			request.setAttribute("loginErr", "ログインが必要です");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		%>
	</c:if>
	<!-- 投稿履歴 -->
	<h2>マイページ</h2>
	<div class="archive mypage-content">
		<div class="content-title">自分の投稿写真</div>
		<div class="mypage-pic">
			<!-- 写真リスト -->
			<c:if test="${!empty photolist }">
				<c:forEach var="p" items="${ photolist }"  begin="0" end="2">
					<div class="pic-area">
						<div class="pic">
							<img src="/HEW_Zidolympic/UploadImages/${p.img_pass }" width="200" height="auto">
						</div>
						<div class="pic-title">${p.img_title}</div>
					</div>
				</c:forEach>
			</c:if>
			<c:if test="${empty photolist }">
				<div class="pic-area">
					<a href="./compelist.jsp">競技に参加して写真を投稿しよう！</a>
				</div>
			</c:if>
		</div>
	</div>
	<!-- お気に入り -->
	<div class="favorite mypage-content">
		<div class="content-title">お気に入り写真</div>
		<div class="mypage-pic">
			<div class="pic">写真</div>
			<div class="pic-title">初投稿ども</div>
		</div>
	</div>
	<div class="setting mypage-content">
		<div class="content-title"><a href="./user_confilm.jsp">設定</a></div>
	</div>
	</c:param>
</c:import>