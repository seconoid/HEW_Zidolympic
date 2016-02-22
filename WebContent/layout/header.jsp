<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- ヘッダ部 -->
<div class="header">
	<ul class="gnavi">
		<li class="nav-item"><h1><a href="./index.jsp">ロゴ</a></h1></li>
		<li class="nav-item"><a href="./compelist.jsp">競技一覧</a></li>
		<li class="nav-item"><a href="/HEW_Zidolympic/PhotoListServlet?check=1">写真一覧</a></li>
		<li class="nav-item"><a href="./ranking.jsp">ランキング</a></li>
		<!-- ログイン状態でない場合に新規登録とログインを表示 -->
		<c:if test="${ sessionScope.user == null }">
			<li class="nav-item join-btn"><a href="./join.jsp">新規登録</a></li>
			<li class="nav-item login-btn"><a href="./login.jsp">ログイン</a></li>
		</c:if>
		<!-- ログイン状態の時にユーザ名とログアウトを表示 -->
		<c:if test="${ sessionScope.user != null }">
			<li class="nav-item logout-btn"><a href="LogoutServlet">ログアウト</a></li>
			<li class="nav-item"><a href="MypageServlet">${ sessionScope.user.getName() }</a></li>
		</c:if>
	</ul>
</div>
<!-- ヘッダ部ここまで -->