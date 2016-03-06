<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- main_layout.jspにコンテンツとタイトルを渡す -->
<c:import url="./layout/main_layout.jsp">
	<c:param name="title" value="設定"/>
	<c:param name="content">
	<!-- 非ログイン時はログイン画面に遷移 -->
	<c:if test="${ sessionScope.user == null }">
		<%
			request.setAttribute("loginErr", "ログインが必要です");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		%>
	</c:if>
	<!-- 認証画面を通らなかった場合に認証画面へ -->
	<c:if test="${ sessionScope.user.getNo() != auth.getNo() }">
		<%
			request.setAttribute("url", "userinfo_update.jsp");
			request.getRequestDispatcher("user_confilm.jsp").forward(request, response);
		%>
	</c:if>
	<div class="join">
		<h2 class="form-title">ユーザ情報変更</h2>
		<form action="UpdateServlet" method="post">
			<div class="form-group">
				<div class="row">
					<div class="col-xs-3">
						<label>ユーザID</label>
					</div>
					<div class="col-xs-9">
						<input type="text" name="id"  class="form-control" value="${ auth.id}" required>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-xs-3">
						<label>ユーザ名</label>
					</div>
					<div class="col-xs-9">
						<input type="text" name="name"  class="form-control" value="${ auth.name }" required>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-xs-3">
						<label>メールアドレス</label>
					</div>
					<div class="col-xs-9">
						<input type="email" name="mail_adress" class="form-control" value="${ auth.mail_adress }" required>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-xs-3">
						<label>生年月日</label>
					</div>
					<div class="col-xs-9">
						<input type="date" name="birthday" class="form-control" value="${ auth.birthday }" >
					</div>
				</div>
			</div>
			<input type="hidden" name="password" value="${ auth.password }">
			<input type="hidden" name="sex" value="${ auth.sex }" >
			<button type="submit" class="btn btn-primary btn-w100">変更を保存</button>
			${ mess }
		</form>
	</div>
	</c:param>
</c:import>