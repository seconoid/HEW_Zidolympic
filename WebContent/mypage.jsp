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
	<c:if test="${!empty pointErr }">
		<div class="mes-area text-danger bg-danger">${ pointErr }</div>
	</c:if>
	<c:if test="${!empty updateErr }">
		<div class="mes-area text-danger bg-danger">${ updateErr }</div>
	</c:if>
	<c:if test="${!empty successMes }">
		<div class="mes-area text-success bg-success">${ successMes }</div>
	</c:if>
	<div class="row mypage">
		<div class="col-xs-3">
			<div class="profile-box">
				<!-- ここに写真 -->
				<img src="/HEW_Zidolympic/profimg/${profimg}" class="img-responsive profile-img"/>${imgmes }
				<div class="row mypage-user">
					<div class="col-xs-7">
						<div class="user-info">
							<div class="user-name">${ sessionScope.user.name }</div>
							<div class="user-id">@${ sessionScope.user.id }</div>
						</div>
					</div>
					<div class="col-xs-5">
						<form action="userinfo_update.jsp" method="get">
							<button type="submit" class="btn btn-default edit-btn">変更</button>
						</form>
					</div>
				</div>
				<div class="row user-point">
					<div class="col-xs-2">
						<i class="material-icons">credit_card</i>
					</div>
					<div class="col-xs-5">
						<div class="user-point-span">${ sessionScope.user.point }pt</div>
					</div>
					<div class="col-xs-5">
						<form action="./conypoint.jsp" method="get">
							<button type="submit" class="btn btn-default buy-btn">購入</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="col-xs-9">
			<div class="mypage-content">
				<div class="news">
					<h3>お知らせ</h3>
					<ul>
						<li class="topic"><a href="#">■「自撮リンピック」サイトオープン！</a><span class="date">3月7日</span></li>
						<li class="topic"><a href="#">■オープンキャンペーン実施中！新規登録で1000ポイントプレゼント！</a><span class="date">3月7日</span></li>
						<li class="topic"><a href="#">■シンクロジドリング開催中！テーマは「必殺技」！</a><span class="date">3月7日</span></li>
					</ul>
				</div>
			</div>
			<div class="archive mypage-content">
				<h2>投稿した写真</h2>
				<div class="mypage-pic row">
					<!-- 写真リスト -->
						<c:if test="${!empty photolist }">
							<c:forEach var="p" items="${ photolist }"  begin="0" end="2">
								<div class="col-xs-4">
									<div class="pic">
										<img src="/HEW_Zidolympic/UploadImages/${p.img_pass }" class="img-responsive">
									</div>
									<div class="pic-title">${p.img_title}</div>
								</div>
							</c:forEach>
						</c:if>
						<c:if test="${empty photolist }">
							<div class="col-xs-12">
								<div class="pic-area">
									<a href="./compelist.jsp">競技に参加して写真を投稿しよう！</a>
								</div>
							</div>
						</c:if>
				</div>
				<c:if test="${ photolist.size() > 3 }">
					<div class="more text-right">
						<a href="#">もっと見る>></a>
					</div>
				</c:if>
			</div>
			<!-- お気に入り -->
			<div class="favorite mypage-content">
				<div>
					<h2 class="bg-yellow">お気に入り写真</h2>
				</div>
				<div class="mypage-pic row">
					<!-- 写真リスト -->
					<c:if test="${!empty favList }">
						<c:forEach var="t" items="${ favList }"  begin="0" end="2">
							<div class="col-xs-4">
								<div class="pic">
								<a href="/HEW_Zidolympic/FovServlet?img_pass=${t.img_pass}&con_id=${t.contribution_id}">
									<img src="/HEW_Zidolympic/UploadImages/${t.img_pass }" class="img-responsive">
								</a>
								</div>
								<div class="pic-title">${t.img_title}</div>
							</div>
						</c:forEach>
					</c:if>
					<c:if test="${empty favList }">
						<div class="col-xs-12">
							<div class="pic-area">
								<a href="./compelist.jsp">競技に参加して写真を投稿しよう！</a>
							</div>
						</div>
					</c:if>
				</div>
				<c:if test="${ favList.size() > 3 }">
					<div class="more text-right">
						<a href="/HEW_Zidolympic/PhotoListServlet?check=1&fov=fov">もっと見る>></a>
					</div>
				</c:if>
			</div>
		</div>
	</div>
	</c:param>
</c:import>