<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- main_layout.jspにコンテンツとタイトルを渡す -->
<c:import url="./layout/main_layout.jsp">
	<c:param name="title" value="本人確認"/>
	<c:param name="content">
	<div class="login">
		<h2 class="form-title">本人認証</h2>
		<c:if test="${!empty authErr }">
			<div class="error-area text-danger bg-danger">${ authErr }</div>
		</c:if>
		<form action="AuthServlet" method="post">
			<div class="form-group">
				<div class="row">
					<div class="col-xs-4">
						<label>ユーザID または メールアドレス</label>
					</div>
					<div class="col-xs-8">
						<input type="text" name="id"  class="form-control" required>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-xs-4">
						<label>パスワード</label>
					</div>
					<div class="col-xs-8">
						<input type="password" name="password" class="form-control" required>
					</div>
				</div>
			</div>
			<input type="hidden" name="url" value="${ url }">
			<button type="submit" class="btn btn-primary btn-w100">認証</button>
		</form>
	</div>
	</c:param>
</c:import>