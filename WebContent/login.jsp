<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- main_layout.jspにコンテンツとタイトルを渡す -->
<c:import url="./layout/main_layout.jsp">
	<c:param name="title" value="ログイン"/>
	<c:param name="content">
	<div class="login">
		<h2 class="form-title">ログイン</h2>
		<form action="LoginServlet" method="post">
			<div class="form-group">
				<div class="row">
					<div class="col-xs-4">
						<label>ユーザID または メールアドレス</label>
					</div>
					<div class="col-xs-8">
						<input type="text" name="id"  class="form-control" required>
						${ idErr }
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
						${ passErr }
					</div>
				</div>
			</div>
			<button type="submit" class="btn btn-primary btn-w100">ログイン</button>
			${ mess }
		</form>
	</div>
	</c:param>
</c:import>