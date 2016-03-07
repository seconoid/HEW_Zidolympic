<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- main_layout.jspにコンテンツとタイトルを渡す -->
<c:import url="./layout/main_layout.jsp">
	<c:param name="title" value="新規登録"/>
	<c:param name="content">
	<!-- 新規登録フォーム -->
	<div class="join">
		<h2 class="form-title">「ジドリンピック」に参加しよう！</h2>
		<form action="InsertServlet" method="post">
			<div class="form-group form-line">
				<div class="row">
					<div class="col-xs-4">
						<label>ユーザID</label>
					</div>
					<div class="col-xs-8">
						<input type="text" name="id"  class="form-control" required>
						${ idErr }
					</div>
				</div>
			</div>
			<div class="form-group form-line">
				<div class="row">
					<div class="col-xs-4">
						<label>ユーザ名</label>
					</div>
					<div class="col-xs-8">
						<input type="text" name="name"  class="form-control" required>
						${ nameErr }
					</div>
				</div>
			</div>
			<div class="form-group form-line">
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
			<div class="form-group form-line">
				<div class="row">
					<div class="col-xs-4">
						<label>メールアドレス</label>
					</div>
					<div class="col-xs-8">
						<input type="email" name="mail_adress" class="form-control" required>
					</div>
				</div>
			</div>
			<div class="form-group form-line">
				<div class="row">
					<div class="col-xs-4">
						<label>生年月日</label>
					</div>
					<div class="col-xs-8">
						<input type="date" name="birthday" class="form-control" required>
					</div>
				</div>
			</div>
			<div class="row form-line">
				<div class="col-xs-4">
					<b>性別</b>
				</div>
				<div class="col-xs-8">
					<div class="radio-inline">
						<label>
							<input type="radio" name="sex" value="0" checked><b>男</b>
						</label>
					</div>
					<div class="radio-inline">
						<label>
							<input type="radio" name="sex" value="1"><b>女</b>
						</label>
					</div>
				</div>
			</div>
			<button type="submit" class="btn btn-primary btn-w100">アカウントを作成</button>
			${ mess }
		</form>
	</div>
	</c:param>
</c:import>